package com.yqsh.ifmp.common.dao.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.yqsh.ifmp.common.dao.NIfmpDao;
import com.yqsh.ifmp.common.model.AmmeterInfo;
import com.yqsh.ifmp.common.model.BigDishes;
import com.yqsh.ifmp.common.model.BlackInfo;
import com.yqsh.ifmp.common.model.BuyQtyWater;
import com.yqsh.ifmp.common.model.CustomerInfo;
import com.yqsh.ifmp.common.model.CustomerInfoQuery;
import com.yqsh.ifmp.common.model.DetailDishes;
import com.yqsh.ifmp.common.model.RechargeRecord;
import com.yqsh.ifmp.common.model.SearchQuery;
import com.yqsh.ifmp.common.model.SmallDishes;
import com.yqsh.ifmp.common.model.TransferenceRecord;
import com.yqsh.ifmp.model.AttDetailBody;
import com.yqsh.ifmp.model.DealDetailBody;
import com.yqsh.ifmp.model.DishesFlowData;
import com.yqsh.ifmp.model.DoorDetailBody;
import com.yqsh.ifmp.model.SubsidyDetailBody;
import com.yqsh.ifmp.model.SynCustomerRequest;
import com.yqsh.ifmp.model.SynOrgRequest;

/**
 * 新卡结构实现类
 * @author chengchao
 *
 */
@Repository
public class NifmpDaoImpl extends SqlMapClientDaoSupport implements NIfmpDao {

	private static Logger log = Logger.getLogger(NifmpDaoImpl.class);
	
	@Resource(name = "sqlMapClient")
    private SqlMapClient sqlMapClient;
	@Resource(name = "aWSqlMapClient")
	private SqlMapClient aWSqlMapClient;
	
    @PostConstruct
    public void initSqlMapClient(){
         super.setSqlMapClient(sqlMapClient);
    }
    
	public void setaWSqlMapClient(SqlMapClient aWSqlMapClient) {
		this.aWSqlMapClient = aWSqlMapClient;
	}

	public SqlMapClient getaWSqlMapClient() {
		return aWSqlMapClient;
	}
    /**
     * 1、获取用户信息
     */
    @Override
    public CustomerInfo getCustomer(CustomerInfoQuery query) {
    	return (CustomerInfo)this.getSqlMapClientTemplate().queryForObject("getCustomer",query);
    }
    
    /**
     * 2、保存充值记录
     */
	@Override
	public int saveRechargeRecord(RechargeRecord record) throws Exception{
		int result = 0;
		try {
			this.getSqlMapClientTemplate().insert("saveRechargeRecord", record);
			result = 1;
		} catch (Exception e) {
			log.error("保存充值记录异常", e);
			throw new RuntimeException("保存充值记录异常");
		}
		return result;
	}
	
	/**
	 * 3、根据订单号判断是否存在，新卡结构统一使用圈存表（FIN_QC_DATA_INFO）
	 */
	@Override
	public String checkExist(String orderId) {
		String result = "";
		Map<String,String> map = new HashMap<String,String>();
		map.put("orderId",orderId);
		try{
			result = (String)this.getSqlMapClientTemplate().queryForObject("checkOrderExist",map);
		}catch(Exception e){
			log.error("新卡结构：查询圈存充值记录发生错误");
		}
		return result;
	}

	/**
	 * 4、根据逻辑卡号查找物理卡号
	 */
	@Override
	public String getPhyCardNo(String cardNo) {
		String result = "";
		Map<String, String> map = new HashMap<String, String>();
		map.put("cardNo", cardNo);
		try {
			result = (String) this.getSqlMapClientTemplate().queryForObject("getPhycardNo", map);
		} catch (Exception e) {
			log.error("查询物理卡号发生错误");
		}
		return result;
	}
	
	/**
	 * 5、保存圈存充值记录
	 * @param tfr
	 */
	public int transferenceRecord(TransferenceRecord tfr){
		int result = 0;
		try {
			this.getSqlMapClientTemplate().insert("saveSubsidizes",tfr);
			result = 1;
		} catch (Exception e) {
			log.error("保存圈存记录异常", e);
			throw new RuntimeException("保存圈存记录异常");
		}
		return result;
	}

	/**
	 * 6、根据卡号获取版本号
	 */
	@Override
	public int getVersionNoByCardNo(String cardNo) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("cardNo",cardNo);
		return (Integer)this.getSqlMapClientTemplate().queryForObject("getVersionNo",map);
	}

	@Override
	public int chenkOrderExist(String orderDate, String merchantId,
			String orderId, String amount, String custNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("orderDate", orderDate);
		map.put("merchantId", merchantId);
		map.put("amount", amount);
		map.put("custNo", custNo);
		return (Integer) this.getSqlMapClientTemplate().queryForObject("checkOrderExist", map);
	}

	@Override
	public void updateOrder(String orderId, String merchantId, String flag,
			String dealTime, String result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("merchantId", merchantId);
		map.put("flag", flag);
		map.put("dealTime", dealTime);
		map.put("result", result);
		this.getSqlMapClientTemplate().update("updateOrder", map);
	}

	@Override
	public void lostCard(String cardno) {
		this.getSqlMapClientTemplate().update("lostCard", cardno);
	}

	@Override
	public void saveBlackAndWrite(String accountid, String cardno,
			String physicalNo, String operType, String listType, String remark) {
		BlackInfo info = new BlackInfo();
		info.setAccountId(Integer.parseInt(accountid));
		info.setCardNo(cardno);
		info.setCreateTime(new Date());
		info.setPhysicscardno(physicalNo);
		info.setTermType("ALL");
		info.setReason("卡片挂失-第三方");
		this.getSqlMapClientTemplate().insert("insertBlackInfo",info);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DealDetailBody> getDealDetail(SearchQuery query) {
		List<DealDetailBody> list = null;
		Integer count = (Integer)this.getSqlMapClientTemplate().queryForObject("countDealDetail", query);
		if(count != null && count.intValue() > 0){
			query.setTotal(count);
			list = this.getSqlMapClientTemplate().queryForList("queryDealDetailRecord", query);
		}else{
			list = Collections.EMPTY_LIST;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DealDetailBody> queryDealDetail(String tableName,
			Integer startno, Integer number, String beginTime, String endTime) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", tableName);
		map.put("startno", startno);
		map.put("number", number);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		return this.getSqlMapClientTemplate().queryForList("queryDealDetail", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DoorDetailBody> getDoorDetail(SearchQuery query) {
		List<DoorDetailBody> list = null;
		Integer count = (Integer)this.getSqlMapClientTemplate().queryForObject("countDoorRecord", query);
		if(count != null && count.intValue() > 0){
			query.setTotal(count);
			list = this.getSqlMapClientTemplate().queryForList("queryDoorRecord", query);
		}else{
			list = Collections.EMPTY_LIST;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AttDetailBody> getAttDetail(SearchQuery query) {
		List<AttDetailBody> list = null;
		Integer count = (Integer)this.getSqlMapClientTemplate().queryForObject("countAttDetail", query);
		if(count != null && count.intValue() > 0){
			query.setTotal(count);
			list = this.getSqlMapClientTemplate().queryForList("queryAttDetailRecord", query);
		}else{
			list = Collections.EMPTY_LIST;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubsidyDetailBody> getSubsidyDetail(SearchQuery query) {
		List<SubsidyDetailBody> details = null;
		Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("countSubsidyDetail", query);
		if (count != null && count > 0) {
			query.setTotal(count);
			details = this.getSqlMapClientTemplate().queryForList("resultSubsidyDetail", query);
		}else {
			details = Collections.EMPTY_LIST;
		}
		return details;
	}

	@Override
	public void insertOrg(SynOrgRequest request) {
		this.getSqlMapClientTemplate().insert("insertOrg", request);
	}

	@Override
	public void updateOrg(SynOrgRequest request) {
		this.getSqlMapClientTemplate().update("updateOrg", request);
	}

	@Override
	public int getOrgByCode(String code) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("getOrgByCode", code);
	}

	@Override
	public void insertAccount(SynCustomerRequest request) {
		this.getSqlMapClientTemplate().insert("insertAccount", request);
	}

	@Override
	public void insertCustomer(SynCustomerRequest request) {
		this.getSqlMapClientTemplate().insert("insertCustomer", request);
	}

	@Override
	public void updateCustomer(SynCustomerRequest request) {
		this.getSqlMapClientTemplate().update("updateCustomer", request);
	}

	@Override
	public void deleteCustomer(String idcard) {
		this.getSqlMapClientTemplate().delete("deleteCustomer", idcard);
	}

	@Override
	public String getCustomerByIdcard(String idcard) {
		return (String) this.getSqlMapClientTemplate().queryForObject("getCustomerByIdcard", idcard);
	}

	@Override
	public int getCustomerIsCreateCard(String idcard) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("getCustomerIsCreateCard", idcard);
	}

	@Override
	public String getMaxAccountID() {
		return (String) this.getSqlMapClientTemplate().queryForObject("getMaxAccountID");
	}

	@Override
	public AmmeterInfo getAmmeterInfo(String consumerId) {
		try {
			return (AmmeterInfo) aWSqlMapClient.queryForObject("getAmmeterInfo", consumerId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BuyQtyWater> getBuyQtyWater(SearchQuery query) {
		try {
			List<BuyQtyWater> details = null;
			Integer count;
				count = (Integer) aWSqlMapClient.queryForObject("countBuyQtyWater", query);
			if (count != null && count > 0) {
				query.setTotal(count);
				int awEnd = query.getAwEnd();
				if(awEnd>count){
					query.setAwEnd(count);
				}
				
				details = aWSqlMapClient.queryForList("resultBuyQtyWater", query);
			}else {
				query.setTotal(0);
				details = Collections.EMPTY_LIST;
			}
			return details;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BigDishes getBigDishesByCode(String code) {
		return (BigDishes) this.getSqlMapClientTemplate().queryForObject("getBigDishesByCode", code);
	}

	@Override
	public SmallDishes getSmallDishesByCode(String code) {
		return (SmallDishes) this.getSqlMapClientTemplate().queryForObject("getSmallDishesByCode", code);
	}

	@Override
	public DetailDishes getDetailDishesByCode(String code) {
		return (DetailDishes) this.getSqlMapClientTemplate().queryForObject("getDetailDishesByCode", code);
	}

	@Override
	public void insertBigDishes(BigDishes dish) {
		this.getSqlMapClientTemplate().insert("insertBigDishes", dish);
	}

	@Override
	public void insertSmallDishes(SmallDishes dish) {
		this.getSqlMapClientTemplate().insert("insertSmallDishes", dish);
	}

	@Override
	public void insertDetailDishes(DetailDishes dish) {
		this.getSqlMapClientTemplate().insert("insertDetailDishes", dish);
	}

	@Override
	public void updateBigDishes(BigDishes dish) {
		this.getSqlMapClientTemplate().update("updateBigDishes", dish);
	}

	@Override
	public void updateSmallDishes(SmallDishes dish) {
		this.getSqlMapClientTemplate().update("updateSmallDishes", dish);
	}

	@Override
	public void updateDetailDishes(DetailDishes dish) {
		this.getSqlMapClientTemplate().update("updateDetailDishes", dish);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DishesFlowData> getDishesFlowData(SearchQuery query) {
		List<DishesFlowData> list = null;
		Integer count = (Integer)this.getSqlMapClientTemplate().queryForObject("countDishesFlowData", query);
		if(count != null && count.intValue() > 0){
			query.setTotal(count);
			list = this.getSqlMapClientTemplate().queryForList("getDishesFlowData", query);
		}else{
			list = Collections.EMPTY_LIST;
		}
		return list;
	}
	
}
