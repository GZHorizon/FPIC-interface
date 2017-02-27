package com.yqsh.ifmp.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Global {

	/**
	 * 帐号
	 */
	public static final String TYPE_ACCOUNT = "1";
	/**
	 * 卡号
	 */
	public static final String TYPE_CARDNO = "2";
	/**
	 * 学工号
	 */
	public static final String TYPE_NUMBER = "3";
	/**
	 * 身份证号
	 */
	public static final String TYPE_IDCARD = "4";
	/**
	 * 手机号码
	 */
	public static final String TYPE_PHONE = "5";
	/**
	 * 银行卡号
	 */
	public static final String TYPE_BANKNO = "6";
	
	/**
	 * 卡状态-正常卡
	 */
	public static final int CARD_STATE_NORMAL = 40;
	
	public static final String FLOW_ATT = "ATT";
	public static final String FLOW_DOOR = "DOOR";
	public static final String FLOW_FIN = "FIN";
	public static final String FLOW_SUB = "SUB";
	
	//消费
	public static final String BS_FIN_BASE_TABLE = "fin_cus_deal_his";
	public static final String CS_FIN_BASE_TABLE = "T_C_RecS";
	//考勤
	public static final String BS_ATT_BASE_TABLE = "att_water_info";
	public static final String CS_ATT_BASE_TABLE = "T_A_CardRecord";
	//门禁
	public static final String BS_DOOR_BASE_TABLE = "door_record_info";
	public static final String CS_DOOR_BASE_TABLE = "T_E_CardRecord";
	//补助
	public static final String BS_SUB_BASE_TABLE = "tb_yzf_record";
	
	/**
	 * 新增
	 */
	public static final String SYN_CREATE = "0";
	/**
	 * 修改
	 */
	public static final String SYN_UPDATE = "1";
	/**
	 * 删除
	 */
	public static final String SYN_DELETE = "2";
	
	public static final String BIG_DISH = "B";
	public static final String SMALL_DISH = "S";
	public static final String DETAIL_DISH = "M";
	
	
	public static boolean ISORACLE=false;
	public static boolean ISMYSQL=false;
	public static boolean ISSQLSERVER = false;
	
	public static String getProperty(String properName) {
		InputStream in = Global.class
				.getResourceAsStream("/config.properties");
		Properties props = new Properties();
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
		String properValue = props.getProperty(properName);
		return properValue;
	}
	
	static{
		String jdbcDriver=getProperty("jdbc.url");
		String jdbcDriverLowcase=jdbcDriver.toLowerCase();
		if(jdbcDriverLowcase.indexOf("oracle")!=-1){
			ISORACLE=true;
		}
		if(jdbcDriverLowcase.indexOf("mysql")!=-1){
			ISMYSQL=true;
		}
		if(jdbcDriverLowcase.indexOf("sqlserver")!=-1){
			ISSQLSERVER=true;
		}
	}
}
