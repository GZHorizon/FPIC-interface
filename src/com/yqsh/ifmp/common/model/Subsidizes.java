package com.yqsh.ifmp.common.model;

import java.util.Date;

public class Subsidizes {

	private String fAccountID;
	private Double fSubMoney;
	private Date fSubTime;
	private String fOperID = "99999";
	private String fReceived = "0";
	private Date fStartReceiveDate;
	private String fEndReceiveDate = "2050-12-31";
	private String fYear;
	private String fMonth;
	private String fClearSub = "0";
	private String fSubID;
	private String fRequested = "0";
	private String cardno;
	private int appid;
	private int cardtypeid;
	private String subsidyItem = "21_000006"; // 补助编号，这里暂时固定为21_000006，学校只有一个补助项
	private String adminID;     // 超级管理员userid

	private String sourceId;

	public String getfAccountID() {
		return fAccountID;
	}

	public void setfAccountID(String fAccountID) {
		this.fAccountID = fAccountID;
	}

	public Double getfSubMoney() {
		return fSubMoney;
	}

	public void setfSubMoney(Double fSubMoney) {
		this.fSubMoney = fSubMoney;
	}

	public Date getfSubTime() {
		return fSubTime;
	}

	public void setfSubTime(Date fSubTime) {
		this.fSubTime = fSubTime;
	}

	public String getfOperID() {
		return fOperID;
	}

	public void setfOperID(String fOperID) {
		this.fOperID = fOperID;
	}

	public String getfReceived() {
		return fReceived;
	}

	public void setfReceived(String fReceived) {
		this.fReceived = fReceived;
	}

	public Date getfStartReceiveDate() {
		return fStartReceiveDate;
	}

	public void setfStartReceiveDate(Date fStartReceiveDate) {
		this.fStartReceiveDate = fStartReceiveDate;
	}

	public String getfEndReceiveDate() {
		return fEndReceiveDate;
	}

	public void setfEndReceiveDate(String fEndReceiveDate) {
		this.fEndReceiveDate = fEndReceiveDate;
	}

	public String getfYear() {
		return fYear;
	}

	public void setfYear(String fYear) {
		this.fYear = fYear;
	}

	public String getfMonth() {
		return fMonth;
	}

	public void setfMonth(String fMonth) {
		this.fMonth = fMonth;
	}

	public String getfClearSub() {
		return fClearSub;
	}

	public void setfClearSub(String fClearSub) {
		this.fClearSub = fClearSub;
	}

	public String getfSubID() {
		return fSubID;
	}

	public void setfSubID(String fSubID) {
		this.fSubID = fSubID;
	}

	public String getfRequested() {
		return fRequested;
	}

	public void setfRequested(String fRequested) {
		this.fRequested = fRequested;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public int getCardtypeid() {
		return cardtypeid;
	}

	public void setCardtypeid(int cardtypeid) {
		this.cardtypeid = cardtypeid;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSubsidyItem() {
		return subsidyItem;
	}

	public void setSubsidyItem(String subsidyItem) {
		this.subsidyItem = subsidyItem;
	}

	public String getAdminID() {
		return adminID;
	}

	public void setAdminID(String adminID) {
		this.adminID = adminID;
	}

}
