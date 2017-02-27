package com.yqsh.ifmp.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SubsidyDetailBody implements Serializable {
	private String name;//接受补助用户姓名
	private String iccid;//学号or工号
	private String money;//补助金额
	private String orderdate;//补助时间
	private String orderid;//订单号
	private String cardid;
	private String isdown;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getIsdown() {
		return isdown;
	}
	public void setIsdown(String isdown) {
		this.isdown = isdown;
	}
	
}
