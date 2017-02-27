package com.yqsh.ifmp.pub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.log4j.Logger;
import org.springframework.web.context.ServletContextAware;

import com.yqsh.ifmp.common.model.CustomerInfo;
import com.yqsh.ifmp.common.model.CustomerInfoQuery;
import com.yqsh.ifmp.common.model.RechargeRecord;
import com.yqsh.ifmp.common.model.Subsidizes;
import com.yqsh.ifmp.common.service.BaseIfmpService;
import com.yqsh.ifmp.util.DateUtil;

public class ReconciliationTask implements ServletContextAware {

	private static Logger log = Logger.getLogger(ReconciliationTask.class);
	private String merchantId;

	private String hostname;
	private int port;
	private String username;
	private String password;
	private String remoteDir;
	private String srcDir;
	
	@Resource(name="ifmpService")
	private BaseIfmpService ifmpService;

	private LinkedList<String> list = null;

	public void execute() {
		try {
			log.info("----开始对账----------");
			String basepath = servletContext.getRealPath("/");
			File srcFile = new File(basepath+srcDir);
			downSingleFile(hostname, port, username, password, remoteDir, srcFile);
			
			if(srcFile.isDirectory()){
				File[] files = srcFile.listFiles(new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						return name.endsWith(".txt");
					}
				});
				for(File f : files){
					readFile(f);
					check();
					renameFile(f);
				}
			}
			log.info("----对账结束----------");
		} catch (Exception e) {
			log.error("处理对账文件异常" + srcDir, e);
		}
	}

	/**
	 * 从FTP服务器下载对账文件到本机服务器
	 * @param hostname
	 * @param port
	 * @param username
	 * @param password
	 * @param remoteDir
	 * @param fileName
	 * @param srcFile
	 * @return
	 */
	public void downSingleFile(String hostname,int port,String username,String password,String remoteDir,File srcFile){
		log.info("----开始从FTP服务器下载对账文件到本机服务器----------");
		FTPClient client = new FTPClient();
		FileOutputStream fos = null;
		try {
			client.connect(hostname,port);
			client.login(username, password);
			client.changeWorkingDirectory(remoteDir);
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			if(!srcFile.exists()){
				srcFile.mkdirs();
			}
			FTPFile[] ftpFiles = client.listFiles("",new FTPFileFilter() {
				
				@Override
				public boolean accept(FTPFile file) {
					return file.getName().endsWith(merchantId+".txt");
				}
			});
			for(FTPFile file : ftpFiles){
				log.info("----下载【"+file.getName()+"】对账文件到本机服务器----------");
				fos = new FileOutputStream(srcFile+"/"+file.getName());
				boolean success = client.retrieveFile(file.getName(), fos);
				if(success){
					client.rename(file.getName(), file.getName()+".bak");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(fos != null){
					fos.close();
				}
				client.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.info("----结束从FTP服务器下载对账文件到本机服务器----------");
	}
	
	/**
	 * 读取文件内容
	 */
	@SuppressWarnings("resource")
	private void readFile(File file) {
		list = new LinkedList<String>();
		FileInputStream fis = null;
		InputStreamReader isr = null;
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			BufferedReader reader = new BufferedReader(isr);
			String line = null;
			while ((line = reader.readLine()) != null) {
				if(StringUtils.isNotBlank(line)){
					list.add(line);
				}
			}
		} catch (FileNotFoundException e) {
			log.warn("没有找到对账文件" + file.getAbsolutePath());
		} catch (IOException e) {
			log.error("读取对账文件异常", e);
		}finally{
			try {
				if(isr != null){
					isr.close();
				}
				if(fis != null){
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 对账处理
	 * 
	 * @throws Exception
	 */
	private void check() throws Exception {
		if (list != null && list.size() > 0) {
			normal();
		}
	}
	
	private void normal() throws Exception{
		String fileTotal = list.pollFirst();
		String[] totalRecord = fileTotal.split("\\|");
		String merchantId = totalRecord[0];
		String orderDate = totalRecord[1];
		for (String record : list) {
			String[] details = record.split("\\|");
			String orderId = details[0];
			String tranSeq = details[1];
			String amount = details[2];
			String result = details[3];
			String account = details[4];
			String custNo = details[5];
			String dealTime = details[6];
			
			int count = ifmpService.getIfmpService().chenkOrderExist(orderDate, merchantId,
					tranSeq, amount, custNo);
			if (count > 0) {
				ifmpService.getIfmpService().updateOrder(tranSeq, merchantId, "1", dealTime,
						result);
			} else {
				if ("0000".equals(result)) {
					CustomerInfoQuery query = new CustomerInfoQuery();
					query.setCardno(custNo);
					CustomerInfo cust = ifmpService.getIfmpService().getCustomer(query);
					RechargeRecord yrecord = buildYzfRecrod(tranSeq, orderId,
							amount, cust.getName(), custNo, orderDate,orderId, dealTime, result);
					Subsidizes sub = buildSubsidizes(orderId, amount,orderDate, cust.getAccountId(),cust.getAppid(),cust.getCardtypeid(),cust.getCardNo());
					ifmpService.getIfmpService().saveRechargeRecord(yrecord, sub);
				}
			}
		}
	}
	

	private RechargeRecord buildYzfRecrod(String orderId, String orderSeq,
			String amount, String name, String custNo, String orderDate,
			String tranSeq, String dealTime, String result) {
		RechargeRecord record = new RechargeRecord();
		record.setOrderId(orderId);
		record.setOrderSeq(orderSeq);
		record.setTranSeq(tranSeq);
		record.setAmount(amount);
		record.setIccid(custNo);
		record.setName(name);
		record.setMerchantId(merchantId);
		record.setCreateTime(DateUtil.getNow());
		record.setOrderDate(orderDate);
		record.setFlag(1);
		record.setJoin("DSF");
		record.setResult(result);
		record.setDealTime(dealTime);
		record.setClientId("00");
		record.setVersionId("000");
		return record;
	}

	private Subsidizes buildSubsidizes(String orderId, String amount,
			String orderDate, String accountId,Integer appid,Integer cardtypeid,String cardno) {
		Subsidizes sub = new Subsidizes();
		sub.setfAccountID(accountId);
		sub.setfSubID(orderId);
		sub.setfSubMoney(Double.parseDouble(amount));
		sub.setfSubTime(DateUtil.strToDate(orderDate, "yyyyMMdd"));
		sub.setfStartReceiveDate(DateUtil.strToDate(orderDate, "yyyyMMdd"));
		sub.setfYear(DateUtil.dateToStr(
				DateUtil.strToDate(orderDate, "yyyyMMdd"), "yyyy"));
		sub.setfMonth(DateUtil.dateToStr(
				DateUtil.strToDate(orderDate, "yyyyMMdd"), "MM"));
		sub.setAppid(appid==null?0:appid);
		sub.setCardtypeid(cardtypeid==null?0:cardtypeid);
		sub.setCardno(cardno);
		return sub;
	}

	/**
	 * 重命名对账文件
	 */
	private void renameFile(File file) {
		file.renameTo(new File(file.getAbsolutePath()+".bak"));
	}

	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemoteDir() {
		return remoteDir;
	}

	public void setRemoteDir(String remoteDir) {
		this.remoteDir = remoteDir;
	}

	public String getSrcDir() {
		return srcDir;
	}

	public void setSrcDir(String srcDir) {
		this.srcDir = srcDir;
	}

}
