package com.yqsh.ifmp.common.dao;

import java.util.List;

import com.yqsh.ifmp.common.model.AccountRole;
import com.yqsh.ifmp.common.model.AmmeterInfo;
import com.yqsh.ifmp.common.model.BaseQuery;
import com.yqsh.ifmp.common.model.BuyQtyWater;
import com.yqsh.ifmp.common.model.CustPhoto;
import com.yqsh.ifmp.common.model.CustomerInfo;
import com.yqsh.ifmp.common.model.CustomerInfoQuery;
import com.yqsh.ifmp.common.model.Deduction;
import com.yqsh.ifmp.common.model.Operator;
import com.yqsh.ifmp.common.model.RechargeRecord;
import com.yqsh.ifmp.common.model.SearchQuery;
import com.yqsh.ifmp.common.model.Subsidizes;
import com.yqsh.ifmp.model.AttDetailBody;
import com.yqsh.ifmp.model.DealDetailBody;
import com.yqsh.ifmp.model.DoorDetailBody;
import com.yqsh.ifmp.model.SubsidyDetailBody;
import com.yqsh.ifmp.model.SynCustomerRequest;
import com.yqsh.ifmp.model.SynOrgRequest;

public interface IfmpDao {
	/**
	 * 增加操作员
	 * @param o
	 * @return
	 */
	public int saveOperator(Operator o);
	/**
	 * 增加角色
	 * @param role
	 * @return
	 */
	public int saveRole(AccountRole role);
	
	/**
	 * 查询用户信息
	 * @param query
	 * @return
	 */
	public CustomerInfo getCustomer(CustomerInfoQuery query);
	
	public List<CustomerInfo> getBatchCustomer(BaseQuery query);
	/**
	 * 根据订单号查询是否存在重复提交订单
	 * @param orderId
	 * @return
	 */
	public String checkExist(String orderId);
	
	/**
	 * 保存充值交易记录
	 * @return
	 */
	public int saveRechargeRecord(RechargeRecord record) throws Exception;
	
	/**
	 * 保存补助信息
	 * @param subsidizes
	 * @return
	 */
	public int saveSubsidizes(Subsidizes subsidizes) throws Exception;
	
	/**
	 * 根据订单号、金额和学工号查询订单是否存在
	 * @param orderId
	 * @param amount
	 * @param custNo
	 * @return
	 */
	public int chenkOrderExist(String orderDate,String merchantId,String orderId,String amount,String custNo);
	
	/**
	 * 更新对账状态
	 * @param orderId
	 * @param flag
	 * @param dealTime
	 * @param result
	 */
	public void updateOrder(String orderId,String merchantId,String flag,String dealTime,String result);
	
	/**
	 * 挂失
	 * @param cardno
	 */
	public void lostCard(String cardno);
	
	/**
	 * 保存黑白名单
	 * @param accountid
	 * @param cardno
	 * @param physicalNo
	 * @param operType
	 * @param listType
	 */
	public void saveBlackAndWrite(String accountid,String cardno,String physicalNo,String operType,String listType,String remark);
	
	/**
	 * 查询交易明细记录
	 * @return
	 */
	public List<DealDetailBody> getDealDetail(SearchQuery query);
	
	
	public List<DealDetailBody> queryDealDetail(String tableName,Integer startno,Integer number,String beginTime,String endTime);
	
	/**
	 * 查询门禁明细记录
	 * @param query
	 * @return
	 */
	public List<DoorDetailBody> getDoorDetail(SearchQuery query);
	
	/**
	 * 查询考勤明细记录
	 * @param query
	 * @return
	 */
	public List<AttDetailBody> getAttDetail(SearchQuery query);
	
	/**
	 * 查询补助信息明细记录
	 * @param query
	 * @return
	 */
	public List<SubsidyDetailBody> getSubsidyDetail(SearchQuery query);
	
	/**
	 * 存储组织信息
	 * @param request
	 */
	public void insertOrg(SynOrgRequest request);
	
	/**
	 * 修改组织信息
	 * @param request
	 */
	public void updateOrg(SynOrgRequest request);
	/**
	 * 获取组织信息-根据组织CODE
	 * @param code
	 * @return
	 */
	public int getOrgByCode(String code);
	
	/**
	 * 创建账户信息
	 * @param request
	 */
	public void insertAccount(SynCustomerRequest request);
	
	/**
	 * 存储用户信息
	 * @param request
	 */
	public void insertCustomer(SynCustomerRequest request);
	
	/**
	 * 修改用户信息
	 * @param request
	 */
	public void updateCustomer(SynCustomerRequest request);
	
	/**
	 * 删除用户信息
	 * @param idcard
	 */
	public void deleteCustomer(String idcard);
	
	/**
	 * 获取用户信息-根据身份证号
	 * @param idcard
	 * @return
	 */
	public String getCustomerByIdcard(String idcard);
	
	/**
	 * 判断是否开卡-根据身份证号
	 * @param idcard
	 * @return
	 */
	public int getCustomerIsCreateCard(String idcard);
	
	/**
	 * 获取最大的账户信息
	 * @return
	 */
	public String getMaxAccountID();
	/**
	 * 存储照片信息
	 * @param map
	 */
	public void saveCustomerPic(CustPhoto photo);
	/**
	 * 查询电表电量信息
	 * @param map
	 */
	public AmmeterInfo getAmmeterInfo(String consumerId);
	/**
	 * 查询购电流水信息
	 * @param map
	 */
	public List<BuyQtyWater> getBuyQtyWater(SearchQuery query);
	/**
	 * 扣费
	 * @param de
	 * @return
	 */
	public String deduction(Deduction de);

}
