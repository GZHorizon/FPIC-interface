package com.yqsh.test;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.yqsh.ifmp.common.model.BaseQuery;
import com.yqsh.ifmp.common.service.IfmpService;
import com.yqsh.ifmp.model.BatchCustmerRequest;
import com.yqsh.ifmp.model.CardInfoRequest;
import com.yqsh.ifmp.model.DeductionRequest;
import com.yqsh.ifmp.model.OperatorRequest;
import com.yqsh.ifmp.model.SynOrgRequest;
import com.yqsh.ifmp.util.AnsiiX99MacUtil;
import com.yqsh.ifmp.util.DateUtil;
import com.yqsh.ifmp.util.DateUtil2;
import com.yqsh.ifmp.util.JSONUtils;
import com.yqsh.ifmp.util.ZipUtils;

public class Test {
	public static void main(String[] args) {
		//System.out.println(DateUtil2.getDateTime());
//		ApplicationContext context  = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
//		IfmpService service = (IfmpService) context.getBean("ifmpService");
//		BaseQuery query = new BaseQuery();
//		query.setPageSize(5);
//		
//		service.getIfmpService().getBatchCustomer(query);
//		Operator o = new Operator();
//		o.setOperaddress("test");
//		o.setOperage(new Date());
//		o.setOperdutydate(new Date());
//		o.setOperemail("test");
//		o.setOpername("系统管理员");
//		o.setOpersex("女");
//		o.setOpertelephone("test");
//		o.setOrganize_id("0005");
//		o.setTbasmaccount_id(5);
//		
//		AccountRole role = new AccountRole();
//		role.setAccount_id(5);
//		System.out.println(service.saveOperator(o, role));
		
//		Test4Obj a = new Test4Obj("7354B92D9A4E4794887B836A14B43A1C",
//		"2015-10-01", "2015-10-31", "0", "10", "1", "322", "515151");
		
		
		
		//System.out.println(service.getOrgByCode("0001"));
		//SynOrgRequest a = new SynOrgRequest("00010001", "test1011", "test", "0");
		//a.setLevel((a.getOrgcode().length() / 4)+"");
		//service.insertOrg(a);
		
		//service.updateOrg(a);
		
		//service.deleteCustomer("123456789854584569");
//		
//		System.out.println(service.getCustomerByIdcard("548562458785458754"));
//		System.out.println(service.getCustomerIsCreateCard("548562458785458754"));
		
		//CardInfoRequest a = new CardInfoRequest();
				//BatchCustmerRequest a = new BatchCustmerRequest();
//			    a.setIndex("1");
//				a.setPagesize("5");
//				a.setCard_no("00001");
		
		
		DeductionRequest a = new DeductionRequest();
		a.setConsTime(DateUtil2.getDateTime());
		a.setConsFare("11.55");
		a.setOper_id("1");
		a.setPhysicalNo("2159298466"); 
		a.setPosId("1");
		a.setThirdcode("7354B92D9A4E4794887B836A14B43A1C");
String json = JSONUtils.objToJsonStr(a);
String mac = "3132333435363738"; 
String pss = "0703"+mac+"001"+json;
System.out.println(ZipUtils.getBase64Encoder(pss.getBytes()));
	     //lodTxt(); 


		System.exit(0);
	}
	
	public static void lodTxt(){
		String str="http://localhost:8088/PFIC/services/thirdInterface";
		//String str = "http://118.112.183.197:1517/PFIC/services/thirdInterface";
        String filePath="D:/workspace/AIOCP_ZYHN/src/Test.txt";
        String fileName="Test.txt";
        try {
            URL url=new URL(str);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.addRequestProperty("FileName", fileName);
            connection.setRequestProperty("content-type", "text/html");
            BufferedOutputStream  out=new BufferedOutputStream(connection.getOutputStream());
            
            //读取文件上传到服务器
            File file=new File(filePath);
            FileInputStream fileInputStream=new FileInputStream(file);
            byte[]bytes=new byte[1024];
            
            int numReadByte=0;
            while((numReadByte=fileInputStream.read(bytes,0,1024))>0)
            {
                out.write(bytes, 0, numReadByte);
            }

            out.flush();
            fileInputStream.close();
            //读取URLConnection的响应
            DataInputStream in=new DataInputStream(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
