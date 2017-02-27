package com.yqsh.ifmp.common.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yqsh.ifmp.common.model.AccountRole;
import com.yqsh.ifmp.common.model.AmmeterInfo;
import com.yqsh.ifmp.common.model.BaseQuery;
import com.yqsh.ifmp.common.model.BuyQtyWater;
import com.yqsh.ifmp.common.model.CustomerInfo;
import com.yqsh.ifmp.common.model.CustomerInfoQuery;
import com.yqsh.ifmp.common.model.Deduction;
import com.yqsh.ifmp.common.model.Operator;
import com.yqsh.ifmp.common.model.RechargeRecord;
import com.yqsh.ifmp.common.model.SearchQuery;
import com.yqsh.ifmp.common.model.Subsidizes;

import com.yqsh.ifmp.common.model.AccountRole;
import com.yqsh.ifmp.common.model.AmmeterInfo;
import com.yqsh.ifmp.common.model.BuyQtyWater;
import com.yqsh.ifmp.common.model.CustomerInfo;
import com.yqsh.ifmp.common.model.CustomerInfoQuery;
import com.yqsh.ifmp.common.model.Operator;
import com.yqsh.ifmp.common.model.RechargeRecord;
import com.yqsh.ifmp.common.model.SearchQuery;
import com.yqsh.ifmp.common.model.Subsidizes;

import com.yqsh.ifmp.common.service.BaseIfmpService;

import com.yqsh.ifmp.common.service.IfmpService;

/**
 * 业务层控制器
 * @author chengchao
 *
 */
@Service("ifmpService")
@Transactional
public class IfmpServiceImpl implements BaseIfmpService {

	private static Logger log = Logger.getLogger(IfmpServiceImpl.class);
	
	/** 旧卡结构业务类 **/
	@Resource(name="oifmpService")
	private IfmpService oifmpService;
	/** 新卡结构业务类 **/
	@Resource(name="nifmpService")
	private IfmpService nifmpService;
	/** 当前系统的卡结构 **/
	@Autowired
	@Qualifier("isNewCard")
	private String isNewCard;

	/**
	 * 门面模式判断具体的实现类
	 * @return
	 */
	public IfmpService getIfmpService(){
		if("1".equals(isNewCard)){
			log.info("新卡结构");
			return nifmpService;
		}else{
			log.info("旧卡结构");
			return oifmpService;
		}
	}
}
