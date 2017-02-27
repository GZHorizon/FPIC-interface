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
import com.yqsh.ifmp.common.dao.IfmpDao;
import com.yqsh.ifmp.common.model.AccountRole;
import com.yqsh.ifmp.common.model.AmmeterInfo;
import com.yqsh.ifmp.common.model.BaseQuery;
import com.yqsh.ifmp.common.model.BlackInfo;
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

@Repository
public class IfmpDaoImpl extends SqlMapClientDaoSupport implements IfmpDao {

	private static Logger log = Logger.getLogger(IfmpDaoImpl.class);
	
	@Resource(name = "sqlMapClient")
    private SqlMapClient sqlMapClient;
	
	//@Resource(name = "aWSqlMapClient")
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
	
	@Override
	public String checkExist(String orderId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		return (String) this.getSqlMapClientTemplate().queryForObject("checkOrderExist", map);
	}

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

	@Override
	public int saveSubsidizes(Subsidizes subsidizes) throws Exception {
		int result = 0;
		try {
			this.getSqlMapClientTemplate().insert("saveSubsidizes", subsidizes);
			result = 1;
		} catch (Exception e) {
			log.error("保存补助记录异常", e);
			throw new RuntimeException("保存补助记录异常");
		}
		return result;
	}

	@Override
	public int chenkOrderExist(String orderDate,String merchantId,String orderId, String amount, String custNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("orderDate", orderDate);
		map.put("merchantId", merchantId);
		map.put("amount", amount);
		map.put("custNo", custNo);
		return (Integer) this.getSqlMapClientTemplate().queryForObject("checkOrderExist", map);
	}

	@Override
	public void updateOrder(String orderId,String merchantId, String flag, String dealTime,
			String result) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderId", orderId);
		map.put("merchantId", merchantId);
		map.put("flag", flag);
		map.put("dealTime", dealTime);
		map.put("result", result);
		this.getSqlMapClientTemplate().update("updateOrder", map);
	}

	@Override
	public CustomerInfo getCustomer(CustomerInfoQuery query) {
		return (CustomerInfo)this.getSqlMapClientTemplate().queryForObject("getCustomer", query);
	}
	@Override
	public List<CustomerInfo> getBatchCustomer(BaseQuery query) {
		try {
			List<CustomerInfo> custList = null;
			Integer count;
				count = (Integer) this.getSqlMapClientTemplate().queryForObject("countBatchCustomer", query);
			if (count != null && count > 0) {
				query.setTotal(count);
				int awEnd = query.getAwEnd();
				if(awEnd>count){
					query.setAwEnd(count);
				}
				
				custList = this.getSqlMapClientTemplate().queryForList("BatchCustomer", query);
			}else {
				query.setTotal(0);
				custList = Collections.EMPTY_LIST;
			}
			return custList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void lostCard(String cardno) {
		this.getSqlMapClientTemplate().update("lostCard", cardno);
	}

	@Override
	public void saveBlackAndWrite(String accountid, String cardno,
			String physicalNo, String operType, String listType,String remark) {
		BlackInfo info = new BlackInfo();
		info.setAccountId(Integer.parseInt(accountid));
		info.setCardNo(cardno);
		info.setCreateTime(new Date());
		info.setPhysicscardno(physicalNo);
		info.setTermType("ALL");
		info.setReason("卡片挂失-微信");
		this.getSqlMapClientTemplate().insert("insertBlackInfo",info);
		
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("accountid", accountid);
//		map.put("cardno", cardno);
//		map.put("physicalNo", physicalNo);
//		map.put("operType", operType);
//		map.put("listType", listType);
//		map.put("remark", remark);
//		this.getSqlMapClientTemplate().update("saveBlackAndWrite", map);
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
			Integer startno, Integer number,String beginTime,String endTime) {
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
	public String getCustomerByIdcard(String idcard) {
		return (String) this.getSqlMapClientTemplate().queryForObject("getCustomerByIdcard", idcard);
	}

	@Override
	public String getMaxAccountID() {
		return (String) this.getSqlMapClientTemplate().queryForObject("getMaxAccountID");
	}

	@Override
	public void deleteCustomer(String idcard) {
		this.getSqlMapClientTemplate().delete("deleteCustomer", idcard);
	}

	@Override
	public int getCustomerIsCreateCard(String idcard) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("getCustomerIsCreateCard", idcard);
	}

	@Override
	public void saveCustomerPic(CustPhoto photo) {
		Integer count = (Integer)this.getSqlMapClientTemplate().queryForObject("getCustomerPicCount", photo);
		if(count != null && count.intValue() > 0){
			this.getSqlMapClientTemplate().update("updateCustomerPic", photo);
		}else{
			this.getSqlMapClientTemplate().insert("insertCustomerPic", photo);
		}
	}

	@Override
	public int saveOperator(Operator o) {
		int result = 0;
		try {
			this.getSqlMapClientTemplate().insert("saveOperator", o);
			result = 1;
		} catch (Exception e) {
			log.error("保存操作员异常", e);
			throw new RuntimeException("保存操作员异常");
		}
		return result;
	}

	@Override
	public int saveRole(AccountRole role) {
		int result = 0;
		try {
			this.getSqlMapClientTemplate().insert("saveAccountRole", role);
			result = 1;
		} catch (Exception e) {
			log.error("保存角色异常", e);
			throw new RuntimeException("保存角色异常");
		}
		return result;
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
	public AmmeterInfo getAmmeterInfo(String consumerId) {
		try {
			return (AmmeterInfo) aWSqlMapClient.queryForObject("getAmmeterInfo", consumerId);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
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
	public String deduction(Deduction de) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("RecState", de.getRecState());
		map.put("AccountID", de.getAccountID());
		map.put("SerialNo", de.getSerialNo());
		map.put("NewSerialNo", "");
		map.put("PosId", de.getPosId());
		map.put("DpBh", de.getDpBh());
		map.put("AppID", de.getAppID());
		map.put("RecType", de.getRecType());
		map.put("ConsTime", de.getConsTime());
		map.put("GatherTime", de.getGatherTime());
		map.put("ConsFare", de.getConsFare());
		map.put("ConsO_1", de.getConsO_1());
		map.put("ConsO_2", de.getConsO_2());
		map.put("ConsO_3", de.getConsO_3());
		map.put("ConsO_4", de.getConsO_4());
		map.put("Oper_id", de.getOper_id());
		map.put("Meal_id", de.getMeal_id());
		map.put("Station_id", de.getStation_id());
		map.put("GoodsID", de.getGoodsID());
		map.put("GoodsCount", de.getGoodsCount());
		map.put("fRecKey", de.getfRecKey());
		map.put("Ret", ""); 
		this.getSqlMapClientTemplate().queryForObject("deduction", map);
		return (String) map.get("Ret");
	}

	

	
}
