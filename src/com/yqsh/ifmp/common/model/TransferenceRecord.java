package com.yqsh.ifmp.common.model;

import java.util.Date;

/**
 * 圈存
 * 
 * @author chengchao
 *
 */
public class TransferenceRecord {

	private int id;// 序号
	private int customerId;// 人员id
	private Date allowTime;// 开始领取时间
	private Date stopTime;// 截止领取时间
	private int isDown;// 状态：0：未分发，1：已分发，2：已下载未领取，3：已领取
	private double money;// 金额
	private String cardNo;// 卡号
	private String feeDate;// 有效期
	private Date createTime;// 创建时间
	private int versionNo;// 联机版本号
	private String clcardNo;//出纳卡号
	private String phycardNo;// 物理卡号
	private String joinDesc;// 接入描述
	private String sourceId;// 来源

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Date getAllowTime() {
		return allowTime;
	}

	public void setAllowTime(Date allowTime) {
		this.allowTime = allowTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public int getIsDown() {
		return isDown;
	}

	public void setIsDown(int isDown) {
		this.isDown = isDown;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getFeeDate() {
		return feeDate;
	}

	public void setFeeDate(String feeDate) {
		this.feeDate = feeDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(int versionNo) {
		this.versionNo = versionNo;
	}

	public String getClcardNo() {
		return clcardNo;
	}

	public void setClcardNo(String clcardNo) {
		this.clcardNo = clcardNo;
	}

	public String getPhycardNo() {
		return phycardNo;
	}

	public void setPhycardNo(String phycardNo) {
		this.phycardNo = phycardNo;
	}

	public String getJoinDesc() {
		return joinDesc;
	}

	public void setJoinDesc(String joinDesc) {
		this.joinDesc = joinDesc;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

}
