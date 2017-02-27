package com.yqsh.ifmp.common.service.impl;

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

import com.yqsh.ifmp.common.dao.NIfmpDao;
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
import com.yqsh.ifmp.common.model.TransferenceRecord;
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
import com.yqsh.ifmp.util.DateUtil;
import com.yqsh.ifmp.util.Global;

/**
 * 新卡结构
 * @author chengchao
 *
 */
@Service("nifmpService")
@Transactional
public class NIfmpServiceImpl implements IfmpService {

	private static Logger log = Logger.getLogger(NIfmpServiceImpl.class);
	
	@Autowired
	private NIfmpDao nifmpDao;

	/**
	 * 1、查询用户信息
	 */
	@Override
	@Transactional(readOnly = true,propagation=Propagation.NOT_SUPPORTED)
	public CustomerInfo getCustomer(CustomerInfoQuery query) {
		return this.nifmpDao.getCustomer(query);
	}
	
    /**
     * 2、保存补助记录
     */
	@Override
	public Map<String,String> saveRechargeRecord(RechargeRequest recharge,CustomerInfo customer,String ver,String adminId) throws Exception {
		
		Map<String,String> map = new HashMap<String,String>();
		String phyCardNo = nifmpDao.getPhyCardNo(customer.getCardNo());
		phyCardNo = StringUtils.leftPad(phyCardNo, 8, "0");//先将物理卡号左补零，补齐4个字节
		/*phyCardNo = phyCardNo.substring(6) + phyCardNo.substring(4,6)+phyCardNo.substring(2,4)+phyCardNo.substring(0,2);*/
		RechargeRecord record = this.buildRecord(recharge, customer, ver);
		TransferenceRecord tfr = this.buildTransferenceRecord(recharge,customer,phyCardNo);
		tfr.setVersionNo(nifmpDao.getVersionNoByCardNo(customer.getCardNo()));
		int result = 0;
		int count = this.nifmpDao.saveRechargeRecord(record);
		int scount = this.nifmpDao.transferenceRecord(tfr);
		if(count > 0 && scount > 0){
			result = 1;
		}
		map.put("count",String.valueOf(result));
		return map;
	}

	@Override
	public int saveOperator(Operator o, AccountRole role) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 校验订单号是否存在
	 */
	@Override
	public String checkExist(String orderId) {
		return this.nifmpDao.checkExist(orderId);
	}

	@Override
	public int chenkOrderExist(String orderDate, String merchantId,
			String orderId, String amount, String custNo) {
		return this.nifmpDao.chenkOrderExist(orderDate, merchantId, orderId, amount, custNo);
	}

	@Override
	public void updateOrder(String orderId, String merchantId, String flag,
			String dealTime, String result) {
		this.nifmpDao.updateOrder(orderId, merchantId, flag, dealTime, result);
	}

	@Override
	public void lostCard(String accountid, String cardno, String physicalNo)
			throws Exception {
		this.nifmpDao.lostCard(cardno);
		this.nifmpDao.saveBlackAndWrite(accountid, cardno, physicalNo, "A", "CB", "消费黑名单");
	}

	@Override
	public List<DealDetailBody> getDealDetail(SearchQuery query) {
		return this.nifmpDao.getDealDetail(query);
	}

	@Override
	public List<DoorDetailBody> getDoorDetail(SearchQuery query) {
		return this.nifmpDao.getDoorDetail(query);
	}

	@Override
	public List<AttDetailBody> getAttDetail(SearchQuery query) {
		return this.nifmpDao.getAttDetail(query);
	}

	@Override
	public List<SubsidyDetailBody> getSubsidyDetail(SearchQuery query) {
		return nifmpDao.getSubsidyDetail(query);
	}

	@Override
	public List<DealDetailBody> queryDealDetail(String tableName,
			Integer startno, Integer number, String beginTime, String endTime) {
		return this.nifmpDao.queryDealDetail(tableName, startno, number,beginTime,endTime);
	}

	@Override
	public void insertOrg(SynOrgRequest request) {
		saveOrg(request);
	}

	@Override
	public void updateOrg(SynOrgRequest request) {
		saveOrg(request);
	}

	@Override
	public int getOrgByCode(String code) {
		return this.nifmpDao.getOrgByCode(code);
	}

	@Override
	public void insertCustomer(SynCustomerRequest request) {
		saveCustomer(request);
	}

	@Override
	public void updateCustomer(SynCustomerRequest request) {
		saveCustomer(request);
	}

	@Override
	public void deleteCustomer(String idcard) {
		this.nifmpDao.deleteCustomer(idcard);
	}

	@Override
	public String getCustomerByIdcard(String idcard) {
		return this.nifmpDao.getCustomerByIdcard(idcard);
	}

	@Override
	public int getCustomerIsCreateCard(String idcard) {
		return this.nifmpDao.getCustomerIsCreateCard(idcard);
	}

	@Override
	public AmmeterInfo getAmmeterInfo(String consumerId) {
		return this.nifmpDao.getAmmeterInfo(consumerId);
	}

	@Override
	public List<BuyQtyWater> getBuyQtyWater(SearchQuery query) {
		return this.nifmpDao.getBuyQtyWater(query);
	}

	private void saveOrg(SynOrgRequest request) {
		int count = getOrgByCode(request.getOrgcode());
		if(count > 0){
			this.nifmpDao.updateOrg(request);
		}else{
			if(Global.ISORACLE){
				request.setLevel((request.getOrgcode().length() / 4)+"");
			}else if(Global.ISSQLSERVER){
				request.setLevel((request.getOrgcode().length() / 3)+"");
			}
			this.nifmpDao.insertOrg(request);
		}
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
				this.nifmpDao.updateCustomer(request);
			}else{
				this.nifmpDao.insertAccount(request);
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
				this.nifmpDao.insertCustomer(request);
			}
		}else if (Global.ISSQLSERVER){
			String fAccountID = this.getCustomerByIdcard(request.getIdcard());
			if(StringUtils.isNotBlank(fAccountID)){//修改
				this.nifmpDao.updateCustomer(request);
			}else{//添加
				String accountID = this.nifmpDao.getMaxAccountID();
				if(StringUtils.isBlank(accountID)){
					request.setfAccountID("00000001");
				}else{
					Integer nAccountID = Integer.parseInt(accountID)+1;
					request.setfAccountID(StringUtils.leftPad(nAccountID+"", 8, "0"));
				}
				this.nifmpDao.insertCustomer(request);
			}
			/*if(StringUtils.isNotBlank(fAccountID)){
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
			}*/
		}
	}
	/**
	 * 新卡结构返回数据，组装返回信息对象
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
		body.setBalance1(customer.getBalance1());// 现金余额
		body.setBalance2(customer.getBalance2());// 补助金额
		body.setBalance3(customer.getBalance3());// 计次卡的次数
		body.setDeptname(customer.getDeptName());// 部门
		body.setSerialnumber(customer.getSerialnumber());// 应用序列号
		body.setCustno(customer.getCustNo());// 学工号
	    body.setMphone(customer.getMphone());// 手机号
	    body.setIdcard(customer.getIdcard());//身份证号
		return body;
	}

	/**
	 * 新卡结构：圈存充值记录
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
	 * 新卡结构：圈存记录
	 * @param recharge
	 * @param customerInfo
	 * @param phycardNo
	 * @return
	 */
	private TransferenceRecord buildTransferenceRecord(RechargeRequest recharge,CustomerInfo customerInfo,String phycardNo) {
		TransferenceRecord tfr = new TransferenceRecord();
		tfr.setId(0);
		tfr.setCustomerId(Integer.parseInt(customerInfo.getAccountId()));
		tfr.setAllowTime(DateUtil.getNow());
		tfr.setStopTime(DateUtil.getNow());
		tfr.setIsDown(0);
		tfr.setMoney(Double.parseDouble(recharge.getTrademoney()));
		tfr.setCardNo(customerInfo.getCardNo());
		tfr.setFeeDate("2099-12-31");
		tfr.setCreateTime(DateUtil.getNow());
		tfr.setVersionNo(0);
		tfr.setClcardNo("9999999");// 出纳卡号
		tfr.setPhycardNo(phycardNo);
		tfr.setJoinDesc("ZZCZ");// 固定值
		tfr.setSourceId("");
		return tfr;
	}

	@Override
	public int saveRechargeRecord(RechargeRecord record, Subsidizes sub)
			throws Exception {
		int result = 0;
		return result;
	}

	@Override
	public BigDishes getBigDishesByCode(String code) {
		return this.nifmpDao.getBigDishesByCode(code);
	}

	@Override
	public SmallDishes getSmallDishesByCode(String code) {
		return this.nifmpDao.getSmallDishesByCode(code);
	}

	@Override
	public DetailDishes getDetailDishesByCode(String code) {
		return this.nifmpDao.getDetailDishesByCode(code);
	}

	@Override
	public void insertBigDishes(BigDishes dish) {
		this.nifmpDao.insertBigDishes(dish);
	}

	@Override
	public void insertSmallDishes(SmallDishes dish) {
		this.nifmpDao.insertSmallDishes(dish);
	}

	@Override
	public void insertDetailDishes(DetailDishes dish) {
		this.nifmpDao.insertDetailDishes(dish);
	}

	@Override
	public void updateBigDishes(BigDishes dish) {
		this.nifmpDao.updateBigDishes(dish);
	}

	@Override
	public void updateSmallDishes(SmallDishes dish) {
		this.nifmpDao.updateSmallDishes(dish);
	}

	@Override
	public void updateDetailDishes(DetailDishes dish) {
		this.nifmpDao.updateDetailDishes(dish);
	}

	@Override
	public List<DishesFlowData> getDishesFlowData(SearchQuery query) {
		return this.nifmpDao.getDishesFlowData(query);
	}

	@Override
	public List<CustomerInfo> getBatchCustomer(BaseQuery query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deduction(Deduction de) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
