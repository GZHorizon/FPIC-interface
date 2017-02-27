package com.yqsh.ifmp.util;

import java.util.HashMap;
import java.util.Map;

public class PubMethod {

	private static Map<String, String> methods = new HashMap<String, String>();

	private static Map<String, String> synMethods = new HashMap<String, String>();
	
	static { 
		methods.put("0001", "verify"); //  方法用途：账户有效性验证
		methods.put("0002", "lostCard");// 方法用途：自助挂失
		methods.put("0003", "recharge");// 方法用途：第三方充值交易
		methods.put("0005", "addAdmin");// 方法用途：?
		methods.put("0080", "search");// 方法用途：第三方交易查询
		methods.put("0100", "getDealDetail");// 方法用途：查询阶段交易明细
		methods.put("0110", "queryDealDetail");// 方法用途：?
		methods.put("0200", "getDoorDetail");// 方法用途：查询阶段门禁明细
		methods.put("0300", "getAttDetail");// 方法用途：查询阶段考勤明细
		methods.put("0500", "getSubsidyDetail");// 方法用途：?
		methods.put("0600", "getAmmeterInfo");// 方法用途：?
		methods.put("0701", "getBatchCustmer");// 方法用途：批量查询人员信息
		methods.put("0702", "getCardInfo");// 方法用途：根据卡号查询人员信息
		methods.put("0703", "deduction");// 方法用途：扣费
		methods.put("0700", "getDishesFlow");//方法用途：查询菜品（服务）消费流水
		methods.put("0704", "getBatchFlow");//方法用途：批量查询流水信息
		synMethods.put("0400", "synOrg");// 方法用途：同步组织机构
		synMethods.put("0401", "synCustomer");// 方法用途：同步人员基础信息
		synMethods.put("0402", "synDishes");//方法用途：同步菜品信息
	}
	
	public static String getMethod(String key){
		return methods.get(key);
	}
	
	public static String getSynMethod(String key){
		return synMethods.get(key);
	}
	public static void main(String[] args) {
		PubMethod a = new PubMethod();
		System.out.println(a.getMethod("0702"));
	}
}
