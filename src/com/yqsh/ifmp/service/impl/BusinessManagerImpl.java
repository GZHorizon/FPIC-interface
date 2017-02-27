package com.yqsh.ifmp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
import com.yqsh.ifmp.common.model.SearchQuery;
import com.yqsh.ifmp.common.model.SmallDishes;
import com.yqsh.ifmp.common.service.BaseIfmpService;
import com.yqsh.ifmp.common.service.impl.IfmpServiceImpl;
import com.yqsh.ifmp.model.AmmeterInfoResponse;
import com.yqsh.ifmp.model.AttDetailBody;
import com.yqsh.ifmp.model.AttDetailResponse;
import com.yqsh.ifmp.model.BaseResponse;
import com.yqsh.ifmp.model.BatchCustmerRequest;
import com.yqsh.ifmp.model.BatchCustmerResponse;
import com.yqsh.ifmp.model.CardInfoRequest;
import com.yqsh.ifmp.model.CardInfoResponse;
import com.yqsh.ifmp.model.DealDetailBody;
import com.yqsh.ifmp.model.DealDetailResponse;

import com.yqsh.ifmp.model.DeductionRequest;

import com.yqsh.ifmp.model.DishesFlowData;
import com.yqsh.ifmp.model.DishesFlowQueryRequest;
import com.yqsh.ifmp.model.DishesFlowResponse;

import com.yqsh.ifmp.model.DoorDetailBody;
import com.yqsh.ifmp.model.DoorDetailResponse;
import com.yqsh.ifmp.model.LostCardRequest;
import com.yqsh.ifmp.model.OperatorRequest;
import com.yqsh.ifmp.model.RechargeBody;
import com.yqsh.ifmp.model.RechargeRequest;
import com.yqsh.ifmp.model.RechargeResponse;
import com.yqsh.ifmp.model.SearchAWRequest;
import com.yqsh.ifmp.model.SearchDetailRequest;
import com.yqsh.ifmp.model.SearchRequest;
import com.yqsh.ifmp.model.SearchResponse;
import com.yqsh.ifmp.model.SubsidyDetailBody;
import com.yqsh.ifmp.model.SubsidyDetailResponse;
import com.yqsh.ifmp.model.SynCustomerRequest;
import com.yqsh.ifmp.model.SynDishesRequest;
import com.yqsh.ifmp.model.SynOrgRequest;
import com.yqsh.ifmp.model.VerifyBody;
import com.yqsh.ifmp.model.VerifyRequest;
import com.yqsh.ifmp.model.VerifyResponse;
import com.yqsh.ifmp.service.BusinessManager;
import com.yqsh.ifmp.util.DateUtil;
import com.yqsh.ifmp.util.DateUtil2;
import com.yqsh.ifmp.util.Global;
import com.yqsh.ifmp.util.IfmpException;
import com.yqsh.ifmp.util.JSONUtils;

@Service
public class BusinessManagerImpl implements BusinessManager {

	private static final Logger log = Logger.getLogger(BusinessManagerImpl.class);

	@Resource(name="ifmpService")
	private BaseIfmpService ifmpService;

	@Autowired
	@Qualifier("uuid")
	private String uuid;
	
	@Autowired
	@Qualifier("adminId")
	private String adminId;
	
	/**
	 * 1、账户有效性验证方法
	 */
	@Override
	public String verify(String param) {
		log.debug("进入verify方法");
		VerifyResponse response = new VerifyResponse();
		try {
			String jsonStr = param.substring(3);
			VerifyRequest verifyRequest = (VerifyRequest) JSONUtils.jsonStrToObj(jsonStr, VerifyRequest.class);
			if (uuid.equals(verifyRequest.getThirdcode())) {
				CustomerInfo customerInfo = null;
				boolean verifyOk = false;
				CustomerInfoQuery query = new CustomerInfoQuery();
				if (Global.TYPE_ACCOUNT.equals(verifyRequest.getSerialnotype())) {
					query.setAccount(verifyRequest.getSerialno());
					customerInfo = ifmpService.getIfmpService().getCustomer(query);
					if (customerInfo != null && verifyRequest.getSerialno().equals(customerInfo.getAccountId())) {
						verifyOk = true;
					}
				} else if (Global.TYPE_CARDNO.equals(verifyRequest.getSerialnotype())) {
					query.setCardno(verifyRequest.getSerialno());
					customerInfo = ifmpService.getIfmpService().getCustomer(query);
					if (customerInfo != null && verifyRequest.getSerialno().equals(customerInfo.getCardNo())) {
						verifyOk = true;
					}
				} else if (Global.TYPE_NUMBER.equals(verifyRequest.getSerialnotype())) {
					query.setNumber(verifyRequest.getSerialno());
					customerInfo = ifmpService.getIfmpService().getCustomer(query);
					if (customerInfo != null && verifyRequest.getSerialno().equals(customerInfo.getCustNo())) {
						verifyOk = true;
					}
				} else if (Global.TYPE_IDCARD.equals(verifyRequest.getSerialnotype())) {
					query.setIdcard(verifyRequest.getSerialno());
					customerInfo = ifmpService.getIfmpService().getCustomer(query);
					if (customerInfo != null) {
						verifyOk = true;
					}
				} else if (Global.TYPE_PHONE.equals(verifyRequest.getSerialnotype())) {
					query.setPhone(verifyRequest.getSerialno());
					customerInfo = ifmpService.getIfmpService().getCustomer(query);
					if (customerInfo != null) {
						verifyOk = true;
					}
				} else if (Global.TYPE_BANKNO.equals(verifyRequest.getSerialnotype())) {
					query.setBankno(verifyRequest.getSerialno());
					customerInfo = ifmpService.getIfmpService().getCustomer(query);
					if (customerInfo != null) {
						verifyOk = true;
					}
				} else {
					query.setBankno("other");
				}

				if (verifyOk) {
					List<VerifyBody> list = new ArrayList<VerifyBody>();
					list.add(ifmpService.getIfmpService().getVerifyBody(customerInfo));
					response.setBody(list);
					response.setMsg("成功");
					response.setResult("0000");
					log.info("成功");
				} else {
					response.setMsg("账户不存在");
					response.setResult("0022");
					log.warn("账户不存在");
				}
			} else {
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error("处理账户有效性验证异常", e);
		} catch (Exception e) {
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理账户有效性验证异常", e);
		}

		log.debug("退出verify方法");
		return JSONUtils.objToJsonStr(response);
	}
		
	/**
	 * 2、创建操作员方法
	 */
	@Override
	public String addAdmin(String param) {
		log.debug("进入addAdmin方法");
		BaseResponse response = new BaseResponse();
		try {
			String jsonStr = param.substring(3);
			OperatorRequest operatorRequest = (OperatorRequest) JSONUtils
					.jsonStrToObj(jsonStr, RechargeRequest.class);
			if (uuid.equals(operatorRequest.getThirdcode())) {
				Operator o = new Operator();
				o.setOperaddress(operatorRequest.getOperaddress());
				o.setOperage(DateUtil.strToDate(operatorRequest.getOperage()));
				o.setOperdutydate(DateUtil.strToDate(operatorRequest.getOperdutydate()));
				o.setOperemail(operatorRequest.getOperemail());
				o.setOpername(operatorRequest.getOpername());
				o.setOpersex(operatorRequest.getOpersex());
				o.setOpertelephone(operatorRequest.getOpertelephone());
				o.setOrganize_id(operatorRequest.getOrganize_id());
				o.setTbasmaccount_id(Integer.parseInt(operatorRequest.getTbasmaccount_id()));
				AccountRole role = new AccountRole();
				role.setAccount_id(Integer.parseInt(operatorRequest.getTbasmaccount_id()));
				try {
					int scount = ifmpService.getIfmpService().saveOperator(o, role);
					if (scount > 0) {
						response.setMsg("成功");
						response.setResult("0000");
						log.info("成功");
					}else{
						response.setMsg("创建操作员失败");
						response.setResult("0025");
						log.info("创建操作员失败");
					}
				} catch (Exception e) {
					response.setMsg("未知错误");
					response.setResult("9999");
					log.error("保存操作员出错", e);
				}
			} else {
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		}catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error("处理账户有效性验证异常", e);
		} catch (Exception e) {
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理账户有效性验证异常", e);
		}
		log.debug("退出addAdmin方法");
		return JSONUtils.objToJsonStr(response);
	}
	
	/**
	 * 第三方充值
	 */
	@Override
	public String recharge(String param) {
		log.debug("进入recharge方法");
		RechargeResponse response = new RechargeResponse();
		try {
			String ver = param.substring(0, 3);
			String jsonStr = param.substring(3);
			RechargeRequest rechargeRequest = (RechargeRequest) JSONUtils.jsonStrToObj(jsonStr, RechargeRequest.class);
			if (uuid.equals(rechargeRequest.getThirdcode())) {
				String orderSeq = ifmpService.getIfmpService().checkExist(rechargeRequest.getThirdno());
				if (StringUtils.isBlank(orderSeq)) {
					CustomerInfoQuery query = new CustomerInfoQuery();
					query.setAccount(rechargeRequest.getAccount());// 账户id
					query.setCardno(rechargeRequest.getCardno()); // 卡号
					CustomerInfo customerInfo = ifmpService.getIfmpService().getCustomer(query);
					if (customerInfo != null) {
						if (Global.CARD_STATE_NORMAL == customerInfo.getCardState()) {
							try {
								Map<String,String> map = ifmpService.getIfmpService().saveRechargeRecord(rechargeRequest,customerInfo,ver,adminId);
								if (Integer.parseInt(map.get("count")) > 0) {
									RechargeBody body = new RechargeBody();
									body.setThirdno(rechargeRequest.getThirdno());
									body.setTradeno(map.get("orderSeq"));
									List<RechargeBody> list = new ArrayList<RechargeBody>();
									list.add(body);
									response.setBody(list);
									response.setMsg("成功");
									response.setResult("0000");
									log.info("成功");
								} else {
									response.setMsg("交易失败");
									response.setResult("0040");
									log.info("充值失败");
								}
							} catch (Exception e) {
								response.setMsg("未知错误");
								response.setResult("9999");
								log.error("存储充值记录异常", e);
							}

						} else {
							response.setMsg("账户处于非正常状态");
							response.setResult("0023");
							log.warn("账户处于非正常状态");
						}
					} else {
						response.setMsg("账户不存在");
						response.setResult("0022");
						log.warn("账户不存在");
					}
				} else {
					response.setMsg("订单重复提交");
					response.setResult("0020");
					log.warn("订单重复提交");
				}
			} else {
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		} catch (Exception e) {
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理账户有效性验证异常", e);
		} catch (IfmpException e) {
            response.setMsg(e.getMessage());
            response.setResult("003");
            log.error("处理账户有效性验证异常",e);
		}
		log.debug("退出recharge方法");
		return JSONUtils.objToJsonStr(response);
	}

	/**
	 * 3、第三方交易查询
	 */
	@Override
	public String search(String param) {
		log.debug("进入search方法");
		SearchResponse response = new SearchResponse();
		try {
			String jsonStr = param.substring(3);
			SearchRequest searchRequest = (SearchRequest) JSONUtils
					.jsonStrToObj(jsonStr, SearchRequest.class);
			if (uuid.equals(searchRequest.getThirdcode())) {
				String orderSeq = ifmpService.getIfmpService().checkExist(searchRequest
						.getThirdno());
				if (StringUtils.isNotBlank(orderSeq)) {
					response.setThirdno(searchRequest.getThirdno());
					response.setTradeno(orderSeq);
					response.setMsg("成功");
					response.setResult("0000");
					log.info("成功");
				} else {
					response.setMsg("订单不存在");
					response.setResult("0021");
					log.warn("订单不存在");
				}
			} else {
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error("处理账户有效性验证异常", e);
		} catch (Exception e) {
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理账户有效性验证异常", e);
		}
		log.debug("退出search方法");
		return JSONUtils.objToJsonStr(response);
	}

	/**
	 * 4、自助挂失
	 */
	@Override
	public String lostCard(String param) {
		log.debug("进入lostCard方法");
		BaseResponse response = new BaseResponse();
		try {
			String jsonStr = param.substring(3);
			LostCardRequest lostCardRequest = (LostCardRequest) JSONUtils
					.jsonStrToObj(jsonStr, LostCardRequest.class);
			if (uuid.equals(lostCardRequest.getThirdcode())) {
				CustomerInfoQuery query = new CustomerInfoQuery();
				if (Global.TYPE_CARDNO.equals(lostCardRequest.getSerialnotype())) {
					query.setCardno(lostCardRequest.getSerialno());
					if(StringUtils.isNotBlank(lostCardRequest.getPassword())){
						query.setPassword(lostCardRequest.getPassword());
					}
				} else {
					query.setBankno("other");
				}
				CustomerInfo customerInfo = ifmpService.getIfmpService().getCustomer(query);
				if (customerInfo != null) {
					if (Global.CARD_STATE_NORMAL == customerInfo.getCardState()) {
						ifmpService.getIfmpService().lostCard(customerInfo.getAccountId(),
								customerInfo.getCardNo(),
								customerInfo.getSerialnumber());
						response.setMsg("成功");
						response.setResult("0000");
						log.info("成功");
					} else {
						response.setMsg("账户处于非正常状态");
						response.setResult("0023");
						log.warn("账户处于非正常状态");
					}
				} else {
					response.setMsg("账户不存在");
					response.setResult("0022");
					log.warn("账户不存在");
				}
			} else {
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error("处理挂失操作异常", e);
		} catch (Exception e) {
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理挂失操作异常", e);
		}
		log.debug("退出lostCard方法");
		return JSONUtils.objToJsonStr(response);
	}

	/**
	 * 5、查询阶段交易明细
	 */
	@Override
	public String getDealDetail(String param) {
		log.debug("进入getDealDetailSingle方法");
		DealDetailResponse response = new DealDetailResponse();
		try {
			String jsonStr = param.substring(3);
			SearchDetailRequest dealDetailRequest = (SearchDetailRequest) JSONUtils
					.jsonStrToObj(jsonStr, SearchDetailRequest.class);
			if (uuid.equals(dealDetailRequest.getThirdcode())) {
				String result = validation(dealDetailRequest,true);
				if(StringUtils.isBlank(result)){
					SearchQuery query = createQuery(dealDetailRequest,Global.FLOW_FIN);
					List<DealDetailBody> dealDetails = ifmpService.getIfmpService().getDealDetail(query);
					response.setBody(dealDetails);
					response.setTotal(query.getTotal()+"");
					response.setPage(dealDetailRequest.getIndex());
					response.setTotalpage(getTotalpage(query.getTotal(), Integer.parseInt(dealDetailRequest.getPagesize()))+"");
					response.setMsg("成功");
					response.setResult("0000");
					log.info("成功");
				}else{
					response.setMsg(result);
					response.setResult("0005");
					log.warn(result);
				}
			} else {
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error("处理查询交易记录操作异常", e);
		} catch (Exception e) {
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理查询交易记录操作异常", e);
		}
		log.debug("退出getDealDetailSingle方法");
		return JSONUtils.objToJsonStr(response);
	}
	
	@Override
	public String queryDealDetail(String param) {
		log.debug("进入getDealDetailSingle方法");
		DealDetailResponse response = new DealDetailResponse();
		try {
			String jsonStr = param.substring(3);
			SearchDetailRequest dealDetailRequest = (SearchDetailRequest) JSONUtils
					.jsonStrToObj(jsonStr, SearchDetailRequest.class);
			if (uuid.equals(dealDetailRequest.getThirdcode())) {
				String result = validation(dealDetailRequest,false);
				if(StringUtils.isBlank(result)){
					SearchQuery query = createQuery(dealDetailRequest,Global.FLOW_FIN);
					List<DealDetailBody> dealDetails = ifmpService.getIfmpService().queryDealDetail(query.getTableName(), Integer.parseInt(dealDetailRequest.getStartno()), Integer.parseInt(dealDetailRequest.getNumber()),dealDetailRequest.getBegintime(),dealDetailRequest.getEndtime());
					response.setBody(dealDetails);
					response.setMsg("成功");
					response.setResult("0000");
					log.info("成功");
				}else{
					response.setMsg(result);
					response.setResult("0005");
					log.warn(result);
				}
			} else {
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error("处理查询交易记录操作异常", e);
		} catch (Exception e) {
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理查询交易记录操作异常", e);
		}
		log.debug("退出getDealDetailSingle方法");
		return JSONUtils.objToJsonStr(response);
	}

	/*
	 * 参数验证
	 */
	private String validation(SearchDetailRequest ddr,boolean flag) {
		String result = "";
		if(flag){
			log.debug("起始位置->" + ddr.getIndex() + "每页大小->" + ddr.getPagesize()
					+ "起始时间->" + ddr.getBegintime() + "结束时间->" + ddr.getEndtime() + "编号类型->"
					+ ddr.getSerialnotype() + "编号->" + ddr.getSerialno());
			if (StringUtils.isBlank(ddr.getIndex())) {
				result = "起始位置为空";
			} else if (StringUtils.isBlank(ddr.getPagesize())) {
				result = "每页大小为空";
			} else if (StringUtils.isBlank(ddr.getBegintime())) {
				result = "起始时间为空";
			} else if (StringUtils.isBlank(ddr.getEndtime())) {
				result = "结束时间为空";
			} else if (!StringUtils.isNumericSpace(ddr.getIndex())) {
				result = "起始位置非整数";
			} else if (!StringUtils.isNumericSpace(ddr.getPagesize())) {
				result = "每页大小非整数";
			} else {
				try {
					DateUtil.stringToDate(ddr.getBegintime(), "yyyy-MM-dd");
					DateUtil.stringToDate(ddr.getEndtime(), "yyyy-MM-dd");
					boolean sameMonth = DateUtil.isSameMonth(ddr.getBegintime(), ddr.getEndtime());
					if (!sameMonth) {
						result = "查询时间不允许跨月";
					}
				} catch (Exception e) {
					log.error("日期转换错误", e);
					result = "日期转换错误";
				}
			}
		}else{
			log.debug("起始流水号->" + ddr.getStartno() + "起始时间->" + ddr.getBegintime() + "结束时间->" + ddr.getEndtime() + "获取条数->" + ddr.getNumber());
			if (StringUtils.isBlank(ddr.getStartno())) {
				result = "起始流水号为空";
			}else if (StringUtils.isBlank(ddr.getBegintime())) {
				result = "起始时间为空";
			} else if (StringUtils.isBlank(ddr.getEndtime())) {
				result = "结束时间为空";
			} else {
				try {
					DateUtil.stringToDate(ddr.getBegintime(), "yyyy-MM-dd");
					DateUtil.stringToDate(ddr.getEndtime(), "yyyy-MM-dd");
					boolean sameMonth = DateUtil.isSameMonth(ddr.getBegintime(), ddr.getEndtime());
					if (!sameMonth) {
						result = "查询时间不允许跨月";
					}
				} catch (Exception e) {
					log.error("日期转换错误", e);
					result = "日期转换错误";
				}
			}
		}
		return result;
	}
	
	private SearchQuery createQuery(SearchDetailRequest ddr,String type){
		SearchQuery query = new SearchQuery();
		String baseTable = "";
		if (StringUtils.isNotBlank(ddr.getIndex()) && StringUtils.isNotBlank(ddr.getPagesize())) {
			query.setStart(Integer.parseInt(ddr.getIndex()) * Integer.parseInt(ddr.getPagesize()));
			query.setPageSize(Integer.parseInt(ddr.getPagesize()));
		}
		
		if(Global.ISSQLSERVER){
			if(Global.FLOW_FIN.equals(type)){
				baseTable = Global.CS_FIN_BASE_TABLE;
			}else if(Global.FLOW_ATT.equals(type)){
				baseTable = Global.CS_ATT_BASE_TABLE;
			}else if(Global.FLOW_DOOR.equals(type)){
				baseTable = Global.CS_DOOR_BASE_TABLE;
			}
			if(StringUtils.isNotBlank(ddr.getBegintime())){
				query.setTableName(baseTable +"_"+ ddr.getBegintime().substring(0, 4)+ "" + ddr.getBegintime().substring(5, 7));
				query.setBegintime(ddr.getBegintime());
			}
		}else{
			if(Global.FLOW_FIN.equals(type)){
				baseTable = Global.BS_FIN_BASE_TABLE;
			}else if(Global.FLOW_ATT.equals(type)){
				baseTable = Global.BS_ATT_BASE_TABLE;
			}else if(Global.FLOW_DOOR.equals(type)){
				baseTable = Global.BS_DOOR_BASE_TABLE;
			}else if (Global.FLOW_SUB.equals(type)) {
				baseTable = Global.BS_SUB_BASE_TABLE;
			}
			if(StringUtils.isNotBlank(ddr.getBegintime())){
				query.setTableName(baseTable +"_"+ ddr.getBegintime().substring(0, 4)+ "_" + ddr.getBegintime().substring(5, 7));
				query.setBegintime(ddr.getBegintime());
			}
		}
		
		if(Global.TYPE_ACCOUNT.equals(ddr.getSerialnotype())){
			query.setAccount(ddr.getSerialno());
		}
		
		if(Global.TYPE_CARDNO.equals(ddr.getSerialnotype())){
			query.setCardno(ddr.getSerialno());
		}
		
		if(Global.TYPE_NUMBER.equals(ddr.getSerialnotype())){
			query.setCustomerno(ddr.getSerialno());
		}
		
		if(Global.TYPE_IDCARD.equals(ddr.getSerialnotype())){
			query.setIdcardno(ddr.getSerialno());
		}
		
		if(StringUtils.isNotBlank(ddr.getEndtime())){
			query.setEndtime(ddr.getEndtime());
		}
		
		return query;
	}

	/**
	 * 6、查询阶段门禁明细
	 */
	@Override
	public String getDoorDetail(String param) {
		log.debug("进入getDoorDetail方法");
		DoorDetailResponse response = new DoorDetailResponse();
		try {
			String jsonStr = param.substring(3);
			SearchDetailRequest dealDetailRequest = (SearchDetailRequest) JSONUtils
					.jsonStrToObj(jsonStr, SearchDetailRequest.class);
			if (uuid.equals(dealDetailRequest.getThirdcode())) {
				String result = validation(dealDetailRequest,true);
				if(StringUtils.isBlank(result)){
					SearchQuery query = createQuery(dealDetailRequest,Global.FLOW_DOOR);
					List<DoorDetailBody> doorDetails = ifmpService.getIfmpService().getDoorDetail(query);
					response.setBody(doorDetails);
					response.setTotal(query.getTotal()+"");
					response.setPage(dealDetailRequest.getIndex());
					response.setTotalpage(getTotalpage(query.getTotal(), Integer.parseInt(dealDetailRequest.getPagesize()))+"");
					response.setMsg("成功");
					response.setResult("0000");
					log.info("成功");
				}else{
					response.setMsg(result);
					response.setResult("0005");
					log.warn(result);
				}
			} else {
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error("处理查询门禁记录操作异常", e);
		} catch (Exception e) {
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理查询门禁记录操作异常", e);
		}
		log.debug("退出getDoorDetail方法");
		return JSONUtils.objToJsonStr(response);
	}

	/**
	 * 7、查询阶段考勤明细
	 */
	@Override
	public String getAttDetail(String param) {
		log.debug("进入getAttDetail方法");
		AttDetailResponse response = new AttDetailResponse();
		try {
			String jsonStr = param.substring(3);
			SearchDetailRequest dealDetailRequest = (SearchDetailRequest) JSONUtils
					.jsonStrToObj(jsonStr, SearchDetailRequest.class);
			if (uuid.equals(dealDetailRequest.getThirdcode())) {
				String result = validation(dealDetailRequest,true);
				if(StringUtils.isBlank(result)){
					SearchQuery query = createQuery(dealDetailRequest,Global.FLOW_ATT);
					List<AttDetailBody> attDetails = ifmpService.getIfmpService().getAttDetail(query);
					response.setBody(attDetails);
					response.setTotal(query.getTotal()+"");
					response.setPage(dealDetailRequest.getIndex());
					response.setTotalpage(getTotalpage(query.getTotal(), Integer.parseInt(dealDetailRequest.getPagesize()))+"");
					response.setMsg("成功");
					response.setResult("0000");
					log.info("成功");
				}else{
					response.setMsg(result);
					response.setResult("0005");
					log.warn(result);
				}
			} else {
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error("处理查询考勤记录操作异常", e);
		} catch (Exception e) {
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理查询考勤记录操作异常", e);
		}
		log.debug("退出getAttDetail方法");
		return JSONUtils.objToJsonStr(response);
	}

	private long getTotalpage(int total,int pageSize){
		long totalpage = 0;
		if(total > 0){
			int index = total % pageSize;
			if(index == 0){
				totalpage = total / pageSize;
			}else{
				totalpage = total / pageSize + 1;
			}
		}
		return totalpage;
	}

	/**
	 * 8、同步组织机构
	 */
	@Override
	public String synOrg(String param) {
		log.debug("进入synOrg方法");
		BaseResponse response = new BaseResponse();
		try{
			String jsonStr = param.substring(3);
			SynOrgRequest synOrgRequest = (SynOrgRequest) JSONUtils.jsonStrToObj(jsonStr, SynOrgRequest.class);
			//验证第三方代码UUID
			if(uuid.equals(synOrgRequest.getThirdcode())){
				dealSynOrg(synOrgRequest, response);
			}else{
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		}catch(Exception e){
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理同步组织操作异常", e);
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error(e.getMessage(), e);
		}
		log.debug("退出synOrg方法");
		return JSONUtils.objToJsonStr(response);
	}

	/**
	 * 处理同步组织信息
	 * @param synOrgRequest
	 * @param response
	 */
	private void dealSynOrg(SynOrgRequest synOrgRequest,BaseResponse response){
		//验证组织编码是否为空
		if(StringUtils.isBlank(synOrgRequest.getOrgcode())){
			response.setMsg("组织编码为空");
			response.setResult("9999");
			log.warn("组织编码为空");
			return;
		}
		
		//新增
		if(Global.SYN_CREATE.equals(synOrgRequest.getState())){
			ifmpService.getIfmpService().insertOrg(synOrgRequest);
			response.setResult("0000");
		}else if(Global.SYN_UPDATE.equals(synOrgRequest.getState())){//修改
			ifmpService.getIfmpService().updateOrg(synOrgRequest);
			response.setResult("0000");
		} else {//状态未知
			response.setMsg("同步状态未知");
			response.setResult("9999");
			log.warn("同步状态未知");
		}
	}
	
	/**
	 * 9、同步人员基础信息
	 */
	@Override
	public String synCustomer(String param) {
		log.debug("进入synCustomer方法");
		BaseResponse response = new BaseResponse();
		try{
			String jsonStr = param.substring(3);
			SynCustomerRequest synCustomerRequest = (SynCustomerRequest) JSONUtils.jsonStrToObj(jsonStr, SynCustomerRequest.class);
			//验证第三方代码
			if(uuid.equals(synCustomerRequest.getThirdcode())){
				dealSynCustomer(synCustomerRequest, response);
			}else{
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		}catch(Exception e){
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理同步人员操作异常", e);
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error(e.getMessage(), e);
		}
		log.debug("退出synCustomer方法");
		return JSONUtils.objToJsonStr(response);
	}
	
	/**
	 * 处理同步人员信息
	 * @param synCustomerRequest
	 * @param response
	 */
	private void dealSynCustomer(SynCustomerRequest synCustomerRequest,BaseResponse response){
		//验证身份证号是否为空
		if(StringUtils.isBlank(synCustomerRequest.getIdcard())){
			response.setMsg("身份证号为空");
			response.setResult("9999");
			log.warn("身份证号为空");
			return;
		}
		if(synCustomerRequest.getIdcard().length() > 18){
			response.setMsg("身份证号长度超过18位");
			response.setResult("9999");
			log.warn("身份证号长度超过18位");
			return;
		}
		//验证学工号是否为空
		if(StringUtils.isBlank(synCustomerRequest.getCustno())){
			response.setMsg("学工号为空");
			response.setResult("9999");
			log.warn("学工号为空");
			return;
		}
		//验证人员类型
		if(StringUtils.isBlank(synCustomerRequest.getCustomerType())){
			response.setMsg("人员类型为空");
			response.setResult("9999");
			log.warn("人员类型为空");
			return;
		}
		//新增
		if(Global.SYN_CREATE.equals(synCustomerRequest.getState())){
			ifmpService.getIfmpService().insertCustomer(synCustomerRequest);
			response.setResult("0000");
		}else if(Global.SYN_UPDATE.equals(synCustomerRequest.getState())){//修改
			ifmpService.getIfmpService().updateCustomer(synCustomerRequest);
			response.setResult("0000");
		}else if(Global.SYN_DELETE.equals(synCustomerRequest.getState())){//删除
			int count = ifmpService.getIfmpService().getCustomerIsCreateCard(synCustomerRequest.getIdcard());
			if(count > 0){
				response.setMsg("已开卡不允许删除");
				response.setResult("9999");
				log.warn("已开卡不允许删除");
			}else{
				ifmpService.getIfmpService().deleteCustomer(synCustomerRequest.getIdcard());
				response.setResult("0000");
			}
		}else{//状态未知
			response.setMsg("同步状态未知");
			response.setResult("9999");
			log.warn("同步状态未知");
		}
	}

	@Override
	public String getSubsidyDetail(String param) {
		log.debug("进入getSubsidyDetail方法");
		SubsidyDetailResponse response = new SubsidyDetailResponse();
		try {
			String jsonStr = param.substring(3);
			SearchDetailRequest subsidyDetailRequest = (SearchDetailRequest) JSONUtils
					.jsonStrToObj(jsonStr, SearchDetailRequest.class);
			if (uuid.equals(subsidyDetailRequest.getThirdcode())) {
				String result = validation(subsidyDetailRequest,true);
				if(StringUtils.isBlank(result)){
					SearchQuery query = createQuery(subsidyDetailRequest,Global.FLOW_SUB);
					List<SubsidyDetailBody> subsidyDetails = ifmpService.getIfmpService().getSubsidyDetail(query);
					response.setBody(subsidyDetails);
					response.setTotal(query.getTotal()+"");
					response.setPage(subsidyDetailRequest.getIndex());
					response.setTotalPage(getTotalpage(query.getTotal(), Integer.parseInt(subsidyDetailRequest.getPagesize()))+"");
					response.setMsg("成功");
					response.setResult("0000");
					log.info("成功");
				}else{
					response.setMsg(result);
					response.setResult("0005");
					log.warn(result);
				}
			} else {
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error("处理查询交易记录操作异常", e);
		} catch (Exception e) {
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理查询交易记录操作异常", e);
		}
		log.debug("退出getSubsidyDetail方法");
		return JSONUtils.objToJsonStr(response);
	}
	
	@Override
	public String getAmmeterInfo(String param) {
		log.debug("进入getAmmeterInfo方法");
		AmmeterInfoResponse response = new AmmeterInfoResponse();
		try {
			String jsonStr = param.substring(3);
			SearchAWRequest dealDetailRequest = (SearchAWRequest) JSONUtils
					.jsonStrToObj(jsonStr, SearchAWRequest.class);
			if (uuid.equals(dealDetailRequest.getThirdcode())) {
				String result = validationAW(dealDetailRequest,true);
				if(StringUtils.isBlank(result)){
					SearchQuery query = new SearchQuery();
					if (StringUtils.isNotBlank(dealDetailRequest.getIndex()) && StringUtils.isNotBlank(dealDetailRequest.getPagesize())) {
						query.setAwStart(Integer.parseInt(dealDetailRequest.getIndex()) * Integer.parseInt(dealDetailRequest.getPagesize())-Integer.parseInt(dealDetailRequest.getPagesize())+1);
						query.setPageSize(Integer.parseInt(dealDetailRequest.getPagesize()));
						query.setAwEnd(Integer.parseInt(dealDetailRequest.getIndex()) * Integer.parseInt(dealDetailRequest.getPagesize()));
					}
					if (StringUtils.isNotBlank(dealDetailRequest.getConsumerId())) {
						query.setConsumerId(dealDetailRequest.getConsumerId());;
					}
					AmmeterInfo ai = ifmpService.getIfmpService().getAmmeterInfo(query.getConsumerId());
					List<BuyQtyWater> dealDetails = ifmpService.getIfmpService().getBuyQtyWater(query);
					if(ai == null){
						response.setCurDegree("");
						response.setSurQty("");
					} else{
						response.setCurDegree(ai.getCurDegree());
						response.setSurQty(ai.getSurQty());
					}
						response.setBody(dealDetails);
					response.setTotal(query.getTotal()+"");
					response.setPage(dealDetailRequest.getIndex());
					response.setTotalpage(getTotalpage(query.getTotal(), Integer.parseInt(dealDetailRequest.getPagesize()))+"");
					response.setMsg("成功");
					response.setResult("0000");
					log.info("成功");
				}else{
					response.setMsg(result);
					response.setResult("0005");
					log.warn(result);
				}
			} else {
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error("处理充值交易记录操作异常", e);
		} catch (Exception e) {
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理充值交易记录操作异常", e);
		}
		log.debug("退出getAmmeterInfo方法");
		return JSONUtils.objToJsonStr(response);
	}
	
	@Override
	public String getCardInfo(String param) {
		log.debug("进入getCardInfo方法");
		CardInfoResponse response = new CardInfoResponse();
		String result = "";
		try {
			String jsonStr = param.substring(3);
			CardInfoRequest cardInfoRequest = (CardInfoRequest) JSONUtils
					.jsonStrToObj(jsonStr, CardInfoRequest.class);
			
			if (uuid.equals(cardInfoRequest.getThirdcode())) {
				//查询卡信息 判断是否为空
				CustomerInfoQuery query = new CustomerInfoQuery();
				query.setCardno(cardInfoRequest.getCard_no());
				CustomerInfo info = ifmpService.getIfmpService().getCustomer(query);
				if(info==null){
					result = "该卡号未查询到用户信息";
				}
				if(StringUtils.isBlank(result)){
					response.setAccountId(info.getAccountId());
					response.setCustNo(info.getCustNo());
					response.setDeptName(info.getDeptName());
					response.setIdcard(info.getIdcard());
					response.setName(info.getName());
					response.setSex(info.getSex());
					response.setMsg("成功");
					response.setResult("0000");
					log.info("成功");
				}else{
					response.setMsg(result);
					response.setResult("0005");
					log.warn(result);
				}
			} else {
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error("处理查询卡信息操作异常", e);
		} catch (Exception e) {
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理查询卡信息操作异常", e);
		}
		log.debug("退出getCardInfo方法");
		return JSONUtils.objToJsonStr(response);
	}
	
	/*
	 * 参数验证
	 */
	private String validationAW(SearchAWRequest ddr,boolean flag) {
		String result = "";
		if(flag){
			log.debug("起始位置->" + ddr.getIndex() + "每页大小->" + ddr.getPagesize()
					+ "用户编号->" + ddr.getConsumerId() );
			if (StringUtils.isBlank(ddr.getIndex())) {
				result = "起始位置为空";
			} else if (StringUtils.isBlank(ddr.getPagesize())) {
				result = "每页大小为空";
			} else if (StringUtils.isBlank(ddr.getConsumerId())) {
				result = "用户编号为空";
			} 
		}
		return result;
	}
	//SerialNo物理卡号
	//AppID默认0
	@Override
	public String deduction(String param) {
		log.debug("进入deduction方法");
		BaseResponse response = new BaseResponse();
		String result = "";
		try {
			String jsonStr = param.substring(3);
			DeductionRequest deductionRequest = (DeductionRequest) JSONUtils
					.jsonStrToObj(jsonStr, DeductionRequest.class);
			if (uuid.equals(deductionRequest.getThirdcode())) {
				//根据物理卡号查询卡信息
				//查询卡信息 判断是否为空
				CustomerInfoQuery query = new CustomerInfoQuery();
				query.setFphysicalno(deductionRequest.getPhysicalNo());
				CustomerInfo info = ifmpService.getIfmpService().getCustomer(query);
				if(info== null){
					result = "未查询到卡片信息";
				}else{
					if(info.getCardState() != 40){
						result = "错误的卡状态，请检查卡状态是否正常";
					}
//					if(Double.parseDouble(deductionRequest.getConsFare())>Double.parseDouble(info.getBalance())){
//						result = "卡余额不足，当前卡余额："+info.getBalance();
//					}
				}
				if(StringUtils.isBlank(result)){
					Deduction de = new Deduction();
					de.setAccountID(info.getAccountId());
					de.setDpBh(info.getDeptId());
					de.setSerialNo(deductionRequest.getPhysicalNo());
					de.setPosId(deductionRequest.getPosId());
					de.setConsTime(DateUtil2.toDateTime(deductionRequest.getConsTime()));
					de.setConsFare(Double.parseDouble(deductionRequest.getConsFare()));
					de.setOper_id(deductionRequest.getOper_id());
					
					String sr = ifmpService.getIfmpService().deduction(de);
					if(sr.equals("0")){
						response.setMsg("扣费成功");
						response.setResult("0000");
						log.info("成功");
					}else{
						response.setMsg("扣费失败；返回数字："+sr);
						response.setResult("0040");
						log.info("成功");
					}
				}else{
					response.setMsg(result);
					response.setResult("0040");
					log.warn(result);
				}
			} else {
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0040");
			log.error("扣费操作异常", e);
		} catch (Exception e) {
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("扣费操作异常", e);
		}
		log.debug("退出deduction方法");
		return JSONUtils.objToJsonStr(response);
	}

	@Override
	public String getBatchCustmer(String param) {
		log.debug("进入getBatchCustmer方法");
		BatchCustmerResponse response = new BatchCustmerResponse();
		try {
			String jsonStr = param.substring(3);
			BatchCustmerRequest batchCustmerRequest = (BatchCustmerRequest) JSONUtils
					.jsonStrToObj(jsonStr, BatchCustmerRequest.class);
			if (uuid.equals(batchCustmerRequest.getThirdcode())) {
				String result = "";
				if (StringUtils.isBlank(batchCustmerRequest.getIndex()) || batchCustmerRequest.getIndex().equals("0")) {
					result = "起始位置为空";
				} else if (StringUtils.isBlank(batchCustmerRequest.getPagesize())) {
					result = "每页大小为空"; 
				}
				if(StringUtils.isBlank(result)){
					BaseQuery query = new BaseQuery();
					
					query.setAwStart(Integer.parseInt(batchCustmerRequest.getIndex()) * Integer.parseInt(batchCustmerRequest.getPagesize())-Integer.parseInt(batchCustmerRequest.getPagesize())+1);
					//query.setPageSize(Integer.parseInt(batchCustmerRequest.getPagesize()));
					query.setAwEnd(Integer.parseInt(batchCustmerRequest.getIndex()) * Integer.parseInt(batchCustmerRequest.getPagesize()));
					
					List<CustomerInfo> customerList = ifmpService.getIfmpService().getBatchCustomer(query);
					
					response.setBody(customerList);
					response.setTotal(query.getTotal()+"");
					response.setPage(batchCustmerRequest.getIndex());
					response.setTotalpage(getTotalpage(query.getTotal(), Integer.parseInt(batchCustmerRequest.getPagesize()))+"");
					response.setMsg("成功");
					response.setResult("0000");
					log.info("成功");
				}else{
					response.setMsg(result);
					response.setResult("0005");
					log.warn(result);
				}
			} else {
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error("处理查询交易记录操作异常", e);
		} catch (Exception e) {
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理查询交易记录操作异常", e);
		}
		log.debug("退出getBatchCustmer方法");
		return JSONUtils.objToJsonStr(response);
	}


	@Override
	public String synDishes(String param) {
		log.debug("进入synDishes方法");
		BaseResponse response = new BaseResponse();
		try{
			String jsonStr = param.substring(3);
			SynDishesRequest synDishesRequest = (SynDishesRequest) JSONUtils.jsonStrToObj(jsonStr, SynDishesRequest.class);
			//验证第三方代码UUID
			if(uuid.equals(synDishesRequest.getThirdcode())){
				if(StringUtils.isNotBlank(synDishesRequest.getDishUnit())){
					dealSynDishes(synDishesRequest, response);
				}else{
					response.setMsg("菜品金额不能为空");
					response.setResult("0005");
				}
			}else{
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		}catch(Exception e){
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理同步菜品操作异常", e);
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error(e.getMessage(), e);
		}
		log.debug("退出synDishes方法");
		return JSONUtils.objToJsonStr(response);
	}
	
	private void dealSynDishes(SynDishesRequest req,BaseResponse response){
		if(Global.BIG_DISH.equals(req.getDishType())){
			BigDishes bigDishes = ifmpService.getIfmpService().getBigDishesByCode(req.getDishNo());
			if(bigDishes != null){
				bigDishes.setName(req.getDishName());
				ifmpService.getIfmpService().updateBigDishes(bigDishes);
			}else{
				BigDishes dish = new BigDishes();
				dish.setCode(req.getDishNo());
				dish.setName(req.getDishName());
				ifmpService.getIfmpService().insertBigDishes(dish);
			}
		}else if(Global.SMALL_DISH.equals(req.getDishType())){
			SmallDishes smallDishes = ifmpService.getIfmpService().getSmallDishesByCode(req.getDishNo());
			if(smallDishes != null){
				smallDishes.setName(req.getDishName());
				smallDishes.setBigCode(req.getParentNo());
				ifmpService.getIfmpService().updateSmallDishes(smallDishes);
			}else{
				SmallDishes dish = new SmallDishes();
				dish.setCode(req.getDishNo());
				dish.setName(req.getDishName());
				dish.setBigCode(req.getParentNo());
				ifmpService.getIfmpService().insertSmallDishes(dish);
			}
			
		}else if(Global.DETAIL_DISH.equals(req.getDishType())){
			DetailDishes detailDishes = ifmpService.getIfmpService().getDetailDishesByCode(req.getDishNo());
			if(detailDishes != null){
				detailDishes.setName(req.getDishName());
				detailDishes.setSmallCode(req.getParentNo());
				detailDishes.setUnitPrice(new BigDecimal(req.getDishUnit()));
				if(StringUtils.isNotBlank(req.getDishZk())){
					detailDishes.setDiscount(Float.parseFloat(req.getDishZk()));
				}else{
					detailDishes.setDiscount(0f);
				}
				ifmpService.getIfmpService().updateDetailDishes(detailDishes);
			}else{
				DetailDishes dish = new DetailDishes();
				dish.setCode(req.getDishNo());
				dish.setName(req.getDishName());
				dish.setSmallCode(req.getParentNo());
				dish.setUnitPrice(new BigDecimal(req.getDishUnit()));
				if(StringUtils.isNotBlank(req.getDishZk())){
					dish.setDiscount(Float.parseFloat(req.getDishZk()));
				}else{
					dish.setDiscount(0f);
				}
				ifmpService.getIfmpService().insertDetailDishes(dish);
			}
		}
		response.setResult("0000");
	}

	@Override
	public String getDishesFlow(String param) {
		log.debug("进入getDishesFlow方法");
		DishesFlowResponse response = new DishesFlowResponse();
		try{
			String jsonStr = param.substring(3);
			DishesFlowQueryRequest synDishesRequest = (DishesFlowQueryRequest) JSONUtils.jsonStrToObj(jsonStr, DishesFlowQueryRequest.class);
			//验证第三方代码UUID
			if(uuid.equals(synDishesRequest.getThirdcode())){
				String result = validationDish(synDishesRequest);
				if(StringUtils.isBlank(result)){
					SearchQuery query = new SearchQuery();
					if (StringUtils.isNotBlank(synDishesRequest.getIndex()) && StringUtils.isNotBlank(synDishesRequest.getPagesize())) {
						query.setStart(Integer.parseInt(synDishesRequest.getIndex()) * Integer.parseInt(synDishesRequest.getPagesize()));
						query.setPageSize(Integer.parseInt(synDishesRequest.getPagesize()));
					}
					if(StringUtils.isNotBlank(synDishesRequest.getDishNo())){
						query.setDishNo(synDishesRequest.getDishNo());
					}
					List<DishesFlowData> dishesFlows = ifmpService.getIfmpService().getDishesFlowData(query);
					response.setBody(dishesFlows);
					response.setTotal(query.getTotal()+"");
					response.setPage(synDishesRequest.getIndex());
					response.setTotalpage(getTotalpage(query.getTotal(), Integer.parseInt(synDishesRequest.getPagesize()))+"");
					response.setMsg("成功");
					response.setResult("0000");
					log.info("成功");
				}else{
					response.setMsg(result);
					response.setResult("0005");
					log.warn(result);
				}
			}else{
				response.setMsg("第三方UUID不存在");
				response.setResult("0005");
				log.warn("第三方UUID不存在");
			}
		}catch(Exception e){
			response.setMsg("未知错误");
			response.setResult("9999");
			log.error("处理同步菜品操作异常", e);
		} catch (IfmpException e) {
			response.setMsg(e.getMessage());
			response.setResult("0003");
			log.error(e.getMessage(), e);
		}
		log.debug("退出getDishesFlow方法");
		return JSONUtils.objToJsonStr(response);
	}
	
	private String validationDish(DishesFlowQueryRequest ddr){
		String result = "";
		if (StringUtils.isBlank(ddr.getIndex())) {
			result = "起始位置为空";
		} else if (StringUtils.isBlank(ddr.getPagesize())) {
			result = "每页大小为空";
		} else if (StringUtils.isBlank(ddr.getBegintime())) {
			result = "起始时间为空";
		} else if (StringUtils.isBlank(ddr.getEndtime())) {
			result = "结束时间为空";
		} else if (!StringUtils.isNumericSpace(ddr.getIndex())) {
			result = "起始位置非整数";
		} else if (!StringUtils.isNumericSpace(ddr.getPagesize())) {
			result = "每页大小非整数";
		} else {
			try {
				DateUtil.stringToDate(ddr.getBegintime(), "yyyy-MM-dd");
				DateUtil.stringToDate(ddr.getEndtime(), "yyyy-MM-dd");
				boolean sameMonth = DateUtil.isSameMonth(ddr.getBegintime(), ddr.getEndtime());
				if (!sameMonth) {
					result = "查询时间不允许跨月";
				}
			} catch (Exception e) {
				log.error("日期转换错误", e);
				result = "日期转换错误";
			}
		}
		return result;
	}

}
