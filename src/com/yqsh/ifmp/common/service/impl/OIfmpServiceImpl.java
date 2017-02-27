package com.yqsh.ifmp.common.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yqsh.ifmp.common.dao.IfmpDao;
import com.yqsh.ifmp.common.model.AccountRole;
import com.yqsh.ifmp.common.model.AmmeterInfo;

import com.yqsh.ifmp.common.model.BaseQuery;

import com.yqsh.ifmp.common.model.BigDishes;

import com.yqsh.ifmp.common.model.BuyQtyWater;
import com.yqsh.ifmp.common.model.CustPhoto;
import com.yqsh.ifmp.common.model.CustomerInfo;
import com.yqsh.ifmp.common.model.CustomerInfoQuery;

import com.yqsh.ifmp.common.model.Deduction;

import com.yqsh.ifmp.common.model.DetailDishes;

import com.yqsh.ifmp.common.model.Operator;
import com.yqsh.ifmp.common.model.RechargeRecord;
import com.yqsh.ifmp.common.model.SearchQuery;
import com.yqsh.ifmp.common.model.SmallDishes;
import com.yqsh.ifmp.common.model.Subsidizes;
import com.yqsh.ifmp.common.service.IfmpService;
import com.yqsh.ifmp.model.AttDetailBody;
import com.yqsh.ifmp.model.DealDetailBody;
import com.yqsh.ifmp.model.DishesFlowData;
import com.yqsh.ifmp.model.DoorDetailBody;
import com.yqsh.ifmp.model.RechargeRequest;
import com.yqsh.ifmp.model.SubsidyDetailBody;
import com.yqsh.ifmp.model.SynCustomerRequest;
import com.yqsh.ifmp.model.SynOrgRequest;
import com.yqsh.ifmp.model.VerifyBody;
import com.yqsh.ifmp.util.ByteUtils;
import com.yqsh.ifmp.util.DateUtil;
import com.yqsh.ifmp.util.Global;

/**
 * 旧卡结构 
 * @author chengchao
 *
 */
@Service("oifmpService")
@Transactional
public class OIfmpServiceImpl implements IfmpService {

	private static Logger log = Logger.getLogger(OIfmpServiceImpl.class);
	
	@Autowired
	private IfmpDao ifmpDao;
	
	@Override
	@Transactional(readOnly = true,propagation=Propagation.NOT_SUPPORTED)
	public String checkExist(String orderId) {
		return this.ifmpDao.checkExist(orderId);
	}

	@Override
	public Map<String,String> saveRechargeRecord(RechargeRequest recharge,CustomerInfo customer,String ver,String adminId) throws Exception {
		
		Map<String,String> map = new HashMap<String,String>();
		RechargeRecord record = this.buildRecord(recharge, customer, ver);
		Subsidizes sub = this.buildSubsidizes(recharge, customer, adminId);
		
		int result = 0;
		int count = this.ifmpDao.saveRechargeRecord(record);
		sub.setSourceId(record.getOrderId());
		int scount = this.ifmpDao.saveSubsidizes(sub);
		if(count > 0 && scount > 0){
			result = 1;
		}
		map.put("count", String.valueOf(result));
		map.put("orderSeq",record.getOrderSeq());
		return map;
	}
	
	@Override
	public int saveRechargeRecord(RechargeRecord record, Subsidizes sub) throws Exception {
		int result = 0;
		int count = this.ifmpDao.saveRechargeRecord(record);
		sub.setSourceId(record.getOrderId());
		int scount = this.ifmpDao.saveSubsidizes(sub);
		if(count > 0 && scount > 0){
			result = 1;
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true,propagation=Propagation.NOT_SUPPORTED)
	public int chenkOrderExist(String orderDate, String merchantId,
			String orderId, String amount, String custNo) {
		return this.ifmpDao.chenkOrderExist(orderDate, merchantId, orderId, amount, custNo);
	}

	@Override
	public void updateOrder(String orderId, String merchantId, String flag,
			String dealTime, String result) {
		this.ifmpDao.updateOrder(orderId, merchantId, flag, dealTime, result);
	}

	@Override
	@Transactional(readOnly = true,propagation=Propagation.NOT_SUPPORTED)
	public CustomerInfo getCustomer(CustomerInfoQuery query) {
		return this.ifmpDao.getCustomer(query);
	}

	@Override
	public void lostCard(String accountid, String cardno, String physicalNo) throws Exception {
		try {
			this.ifmpDao.lostCard(cardno);
			this.ifmpDao.saveBlackAndWrite(accountid, cardno, physicalNo, "A", "CB", "消费黑名单");
			if(Global.ISSQLSERVER){
			this.ifmpDao.saveBlackAndWrite(accountid, cardno, physicalNo, "D", "AW", "考勤白名单");
			this.ifmpDao.saveBlackAndWrite(accountid, cardno, physicalNo, "D", "EW", "门禁白名单");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = true,propagation=Propagation.NOT_SUPPORTED)
	public List<DealDetailBody> getDealDetail(SearchQuery query) {
		return this.ifmpDao.getDealDetail(query);
	}

	
	@Override
	@Transactional(readOnly = true,propagation=Propagation.NOT_SUPPORTED)
	public List<DealDetailBody> queryDealDetail(String tableName,
			Integer startno, Integer number,String beginTime,String endTime) {
		return this.ifmpDao.queryDealDetail(tableName, startno, number,beginTime,endTime);
	}

	@Override
	@Transactional(readOnly = true,propagation=Propagation.NOT_SUPPORTED)
	public List<DoorDetailBody> getDoorDetail(SearchQuery query) {
		return this.ifmpDao.getDoorDetail(query);
	}

	@Override
	@Transactional(readOnly = true,propagation=Propagation.NOT_SUPPORTED)
	public List<AttDetailBody> getAttDetail(SearchQuery query) {
		return this.ifmpDao.getAttDetail(query);
	}

	@Override
	public void insertOrg(SynOrgRequest request) {
		saveOrg(request);
	}

	@Override
	public void updateOrg(SynOrgRequest request) {
		saveOrg(request);
	}

	private void saveOrg(SynOrgRequest request) {
		int count = getOrgByCode(request.getOrgcode());
		if(count > 0){
			this.ifmpDao.updateOrg(request);
		}else{
			if(Global.ISORACLE){
				request.setLevel((request.getOrgcode().length() / 4)+"");
			}else if(Global.ISSQLSERVER){
				request.setLevel((request.getOrgcode().length() / 3)+"");
			}
			this.ifmpDao.insertOrg(request);
		}
	}
	
	@Override
	@Transactional(readOnly = true,propagation=Propagation.NOT_SUPPORTED)
	public int getOrgByCode(String code) {
		return this.ifmpDao.getOrgByCode(code);
	}

	@Override
	public void insertCustomer(SynCustomerRequest request) {
		saveCustomer(request);
	}

	@Override
	public void updateCustomer(SynCustomerRequest request) {
		saveCustomer(request);
	}
	
	private void saveCustomer(SynCustomerRequest request) {
		if(Global.ISORACLE){
			String fAccountID = this.getCustomerByIdcard(request.getIdcard());
			if(StringUtils.isNotBlank(fAccountID)){//修改
				if("0".equals(request.getSex())){
					request.setSex("男");//男
				}else if("1".equals(request.getSex())){
					request.setSex("女");//女
				}
				this.ifmpDao.updateCustomer(request);
			}else{
				this.ifmpDao.insertAccount(request);
				if("0".equals(request.getSex())){
					request.setSex("男");//男
				}else if("1".equals(request.getSex())){
					request.setSex("女");//女
				}
				if("0".equals(request.getCustomerType())){//老师
					request.setCustomerType("教工");
				}else if("1".equals(request.getCustomerType())){//学生
					request.setCustomerType("学生");
				}
				this.ifmpDao.insertCustomer(request);
			}
		}else if (Global.ISSQLSERVER){
			String fAccountID = this.getCustomerByIdcard(request.getIdcard());
			if(StringUtils.isNotBlank(fAccountID)){//修改
				this.ifmpDao.updateCustomer(request);
			}else{//添加
				String accountID = this.ifmpDao.getMaxAccountID();
				if(StringUtils.isBlank(accountID)){
					request.setfAccountID("00000001");
				}else{
					Integer nAccountID = Integer.parseInt(accountID)+1;
					request.setfAccountID(StringUtils.leftPad(nAccountID+"", 8, "0"));
				}
				this.ifmpDao.insertCustomer(request);
			}
			if(StringUtils.isNotBlank(request.getImg())){
				if(StringUtils.isNotBlank(fAccountID)){
					CustPhoto photo = new CustPhoto();
					photo.setfAccountID(fAccountID);
					//下载图片并转换成16进制字符串
					byte[] imgHex = downloadImgToHexStr(request.getImg());
					if(imgHex != null){
						photo.setfPhoto(imgHex);
						this.ifmpDao.saveCustomerPic(photo);
					}
					this.ifmpDao.updateCustomer(request);
				}else{
					CustPhoto photo = new CustPhoto();
					photo.setfAccountID(request.getfAccountID());
					//下载图片并转换成16进制字符串
					byte[] imgHex = downloadImgToHexStr(request.getImg());
					if(imgHex != null){
						photo.setfPhoto(imgHex);
						this.ifmpDao.saveCustomerPic(photo);
					}
				}
			}
		}
	}
	@Override
	@Transactional(readOnly = true,propagation=Propagation.NOT_SUPPORTED)
	public String getCustomerByIdcard(String idcard) {
		return this.ifmpDao.getCustomerByIdcard(idcard);
	}
	
	@Override
	public void deleteCustomer(String idcard) {
		this.ifmpDao.deleteCustomer(idcard);
	}

	@Override
	@Transactional(readOnly = true,propagation=Propagation.NOT_SUPPORTED)
	public int getCustomerIsCreateCard(String idcard) {
		return this.ifmpDao.getCustomerIsCreateCard(idcard);
	}

	private byte[] downloadImgToHexStr(String imgPath){
		//String imgPath = "http://avatar.csdn.net/F/8/4/1_itbiyu.jpg";
		log.info("照片位置：" + imgPath);
		byte[] imgBuf = null;
		try {
			URL url = new URL(imgPath);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(1000);
			InputStream inStream = conn.getInputStream();
			int size = inStream.available();
			log.info("照片大小：" + size);
			byte[] buffer = new byte[size];
			int len = -1;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			while((len = inStream.read(buffer)) != -1){
				os.write(buffer, 0, len);
			}
			imgBuf = os.toByteArray();
			String imgHex = ByteUtils.bytesToHexString(imgBuf);
			if(log.isDebugEnabled()){
				log.debug("照片转换成16进制字符串为：" + imgHex);
			}
			os.close();
			inStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgBuf;
	}

	@Override
	public int saveOperator(Operator o, AccountRole role) {
		int result = 0;
		int count = this.ifmpDao.saveOperator(o);
		int scount = this.ifmpDao.saveRole(role);
		if(count > 0 && scount > 0){
			result = 1;
		}
		return result;
	}

	@Override
	public List<SubsidyDetailBody> getSubsidyDetail(SearchQuery query) {
		return ifmpDao.getSubsidyDetail(query);
	}

	@Override
	public AmmeterInfo getAmmeterInfo(String consumerId) {
		return this.ifmpDao.getAmmeterInfo(consumerId);
	}

	@Override
	public List<BuyQtyWater> getBuyQtyWater(SearchQuery query) {
		return this.ifmpDao.getBuyQtyWater(query);
	}
	
	/**
	 * 旧卡结构返回数据，组装返回信息对象
	 * @param customer
	 * @return
	 */
	@Override
	public VerifyBody getVerifyBody(CustomerInfo customer) {
		VerifyBody body = new VerifyBody();
		body.setAccount(customer.getAccountId());// 账户
		body.setName(customer.getName());// 姓名
		body.setSex(customer.getSex());// 性别
		body.setCardno(customer.getCardNo());// 卡号
		body.setCardstate(customer.getCardState() == 40 ? "正常卡" : "非正常卡");// 卡状态
		body.setBalance1(customer.getBalance());// 余额
		body.setDeptname(customer.getDeptName());// 部门
		body.setSerialnumber(customer.getSerialnumber());// ?
		body.setCustno(customer.getCustNo());// 学工号
	    body.setMphone(customer.getMphone());// 手机号
	    body.setIdcard(customer.getIdcard());//身份证号
		return body;
	}
	
	/**
	 * 旧卡结构：构建充值记录
	 * @param recharge
	 * @param customer
	 * @param ver
	 * @return
	 */
	private RechargeRecord buildRecord(RechargeRequest recharge,CustomerInfo customer, String ver) {
		RechargeRecord record = new RechargeRecord();
		record.setOrderId(recharge.getThirdno());
		record.setOrderSeq(UUID.randomUUID().toString().replace("-", ""));
		record.setTranSeq(recharge.getThirdno());
		record.setMerchantId(recharge.getThirdcode());
		record.setIccid(customer.getCustNo());
		record.setName(customer.getName());
		record.setAmount(recharge.getTrademoney());
		record.setOrderDate(recharge.getTradetime());
		// record.setClientId(recharge.getClientID());
		record.setVersionId(ver);
		record.setCreateTime(DateUtil.getNow());
		// record.setJoin(Join.YZF.name());
		return record;
	}

	/**
	 * 旧卡结构：构建补助信息对象
	 * @param recharge
	 * @param customerInfo
	 * @param adminId
	 * @return
	 */
	private Subsidizes buildSubsidizes(RechargeRequest recharge,CustomerInfo customerInfo,String adminId) {
		Subsidizes sub = new Subsidizes();
		sub.setfAccountID(customerInfo.getAccountId());
		sub.setfSubID(recharge.getThirdno());
		sub.setfSubMoney(Double.parseDouble(recharge.getTrademoney()));
		sub.setfSubTime(DateUtil.strToDate(recharge.getTradetime(), "yyyy-MM-dd"));
		sub.setfStartReceiveDate(DateUtil.strToDate(recharge.getTradetime(),"yyyy-MM-dd"));
		sub.setfYear(DateUtil.dateToStr(DateUtil.strToDate(recharge.getTradetime(), "yyyy-MM-dd"), "yyyy"));
		sub.setfMonth(DateUtil.dateToStr(DateUtil.strToDate(recharge.getTradetime(), "yyyy-MM-dd"), "MM"));
		sub.setAppid(customerInfo.getAppid());
		sub.setCardtypeid(customerInfo.getCardtypeid());
		sub.setCardno(customerInfo.getCardNo());
		sub.setAdminID(StringUtils.isEmpty(adminId)?"1":adminId.trim());
		return sub;
	}
	public List<CustomerInfo> getBatchCustomer(BaseQuery query){
		return ifmpDao.getBatchCustomer(query);
	}
	
	@Override
	public String deduction(Deduction de) throws Exception {
		return ifmpDao.deduction(de);
	}
	@Override
	public BigDishes getBigDishesByCode(String code) {
		return null;
	}

	@Override
	public SmallDishes getSmallDishesByCode(String code) {
		return null;
	}

	@Override
	public DetailDishes getDetailDishesByCode(String code) {
		return null;
	}

	@Override
	public void insertBigDishes(BigDishes dish) {
		
	}

	@Override
	public void insertSmallDishes(SmallDishes dish) {
		
	}

	@Override
	public void insertDetailDishes(DetailDishes dish) {
		
	}

	@Override
	public void updateBigDishes(BigDishes dish) {
		
	}

	@Override
	public void updateSmallDishes(SmallDishes dish) {
		
	}

	@Override
	public void updateDetailDishes(DetailDishes dish) {
		
	}

	@Override
	public List<DishesFlowData> getDishesFlowData(SearchQuery query) {
		return null;
	}

}
