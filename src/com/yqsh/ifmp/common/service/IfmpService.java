package com.yqsh.ifmp.common.service;

import java.util.List;
import java.util.Map;

import com.yqsh.ifmp.common.model.AccountRole;
import com.yqsh.ifmp.common.model.AmmeterInfo;
import com.yqsh.ifmp.common.model.BaseQuery;
import com.yqsh.ifmp.common.model.BigDishes;
import com.yqsh.ifmp.common.model.BuyQtyWater;
import com.yqsh.ifmp.common.model.CustomerInfo;
import com.yqsh.ifmp.common.model.CustomerInfoQuery;
import com.yqsh.ifmp.common.model.Deduction;

import com.yqsh.ifmp.common.model.DetailDishes;

import com.yqsh.ifmp.common.model.Operator;
import com.yqsh.ifmp.common.model.RechargeRecord;
import com.yqsh.ifmp.common.model.SearchQuery;
import com.yqsh.ifmp.common.model.SmallDishes;
import com.yqsh.ifmp.common.model.Subsidizes;
import com.yqsh.ifmp.model.AttDetailBody;
import com.yqsh.ifmp.model.DealDetailBody;
import com.yqsh.ifmp.model.DishesFlowData;
import com.yqsh.ifmp.model.DoorDetailBody;
import com.yqsh.ifmp.model.RechargeRequest;
import com.yqsh.ifmp.model.SubsidyDetailBody;
import com.yqsh.ifmp.model.SynCustomerRequest;
import com.yqsh.ifmp.model.SynOrgRequest;
import com.yqsh.ifmp.model.VerifyBody;

/**
 * 旧卡结构接口
 * @author chengchao
 *
 */
public interface IfmpService {
	
	public int saveOperator(Operator o,AccountRole role);
	
	/** 1、查询用户信息**/
	public CustomerInfo getCustomer(CustomerInfoQuery query);
	/** 2、根据订单号查询是否存在重复提交订单**/
	public String checkExist(String orderId);
	/** 3、保存翼支付交易记录 **/
	public Map<String,String> saveRechargeRecord(RechargeRequest recharge,CustomerInfo customer,String ver,String adminId) throws Exception;
	/** 4、根据订单号、金额和学工号查询订单是否存在 **/
	public int chenkOrderExist(String orderDate,String merchantId,String orderId,String amount,String custNo);
	/** 5、更新对账状态 **/
	public void updateOrder(String orderId,String merchantId,String flag,String dealTime,String result);
	/** 6、自主挂失 **/
	public void lostCard(String accountid,String cardno,String physicalNo) throws Exception;
	/** 7、查询交易明细记录 **/
	public List<DealDetailBody> getDealDetail(SearchQuery query);
	/** 8、查询门禁明细记录 **/
	public List<DoorDetailBody> getDoorDetail(SearchQuery query);
	/** 9、查询考勤明细记录 **/
	public List<AttDetailBody> getAttDetail(SearchQuery query);
	/** 10、查询补助信息明细记录 **/
	public List<SubsidyDetailBody> getSubsidyDetail(SearchQuery query);
	/** 11、? **/
	public List<DealDetailBody> queryDealDetail(String tableName,Integer startno,Integer number,String beginTime,String endTime);
	/** 12、存储组织信息 **/
	public void insertOrg(SynOrgRequest request);
	/** 13、修改组织信息 **/
	public void updateOrg(SynOrgRequest request);
	/** 14、获取组织信息-根据组织CODE**/
	public int getOrgByCode(String code);
	/** 15、存储用户信息 **/
	public void insertCustomer(SynCustomerRequest request);
	/** 16、修改用户信息 **/
	public void updateCustomer(SynCustomerRequest request);
	/** 17、删除用户信息 **/
	public void deleteCustomer(String idcard);
	/** 18、获取用户信息-根据身份证号**/
	public String getCustomerByIdcard(String idcard);
	/** 19、判断是否开卡-根据身份证号**/
	public int getCustomerIsCreateCard(String idcard);
	/** 20、? **/
	public AmmeterInfo getAmmeterInfo(String consumerId);
	/** 21、? **/
	public List<BuyQtyWater> getBuyQtyWater(SearchQuery query);
	/** 22、组装返回的数据**/
	public VerifyBody getVerifyBody(CustomerInfo customer);
	/** 24、充值**/
	public int saveRechargeRecord(RechargeRecord record,Subsidizes sub)throws Exception;
	/** 25  批量查询用户信息*/
	public List<CustomerInfo> getBatchCustomer(BaseQuery query);
	/** 26  扣费*/
	public String deduction(Deduction de)throws Exception;
	public BigDishes getBigDishesByCode(String code);
	public SmallDishes getSmallDishesByCode(String code);
	public DetailDishes getDetailDishesByCode(String code);
	
	public void insertBigDishes(BigDishes dish);
	public void insertSmallDishes(SmallDishes dish);
	public void insertDetailDishes(DetailDishes dish);
	
	public void updateBigDishes(BigDishes dish);
	public void updateSmallDishes(SmallDishes dish);
	public void updateDetailDishes(DetailDishes dish);
	
	public List<DishesFlowData> getDishesFlowData(SearchQuery query);
}

