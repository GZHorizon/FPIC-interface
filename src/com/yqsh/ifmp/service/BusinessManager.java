package com.yqsh.ifmp.service;


public interface BusinessManager {
	/**
	 * 增加操作员
	 * @param param
	 * @return
	 */
	public String addAdmin(String param);
	
	/**
	 * 账户有效性验证
	 * @param param
	 * @return
	 */
	public String verify(String param);
	
	/**
	 * 充值
	 * @param param
	 * @return
	 */
	public String recharge(String param);
	
	/**
	 * 交易查询
	 * @param param
	 * @return
	 */
	public String search(String param);
	
	/**
	 * 挂失
	 * @param param
	 * @return
	 */
	public String lostCard(String param);
	
	/**
	 * 查询阶段交易明细
	 * @param param
	 * @return
	 */
	public String getDealDetail(String param);
	/**
	 * 获取交易明细
	 * @param param
	 * @return
	 */
	public String queryDealDetail(String param);
	
	/**
	 * 查询门禁记录
	 * @param param
	 * @return
	 */
	public String getDoorDetail(String param);
	
	/**
	 * 查询考勤记录
	 * @param param
	 * @return
	 */
	public String getAttDetail(String param);
	
	/**
	 * 查询补助信息
	 * @param param
	 * @return
	 */
	public String getSubsidyDetail(String param);
	
	/**
	 * 同步组织信息
	 * @param param
	 * @return
	 */
	public String synOrg(String param);
	
	public String synCustomer(String param);
	/**
	 * 同步组织信息
	 * @param param
	 * @return
	 */
	public String getAmmeterInfo(String param);
	
	/**
	 * 根据卡号获得卡片人员基本信息
	 * @param param
	 * @return
	 */
	public String getCardInfo(String param);
	/**
	 * 扣费
	 * @param param
	 * @return
	 */
	public String deduction(String param);
	/**
	 * 批量查询用户信息
	 * @param param
	 * @return
	 */
	public String getBatchCustmer(String param);
	
	/**
	 * 同步菜品基础信息
	 * @param param
	 * @return
	 */
	public String synDishes(String param);
	
	/**
	 * 查询菜品消费记录
	 * @param param
	 * @return
	 */
	public String getDishesFlow(String param);
}
