package com.yqsh.ifmp.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DishesFlowData implements Serializable {

	private String selTime;
	private String selUser;
	private String cardNo;
	private String dishNo;
	private String dishName;
	private String dishPrice;
	private String selCount;
	private String selMoney;
	private String termNo;
	private String termName;

	public String getSelTime() {
		return selTime;
	}

	public void setSelTime(String selTime) {
		this.selTime = selTime;
	}

	public String getSelUser() {
		return selUser;
	}

	public void setSelUser(String selUser) {
		this.selUser = selUser;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getDishNo() {
		return dishNo;
	}

	public void setDishNo(String dishNo) {
		this.dishNo = dishNo;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDishPrice() {
		return dishPrice;
	}

	public void setDishPrice(String dishPrice) {
		this.dishPrice = dishPrice;
	}

	public String getSelCount() {
		return selCount;
	}

	public void setSelCount(String selCount) {
		this.selCount = selCount;
	}

	public String getSelMoney() {
		return selMoney;
	}

	public void setSelMoney(String selMoney) {
		this.selMoney = selMoney;
	}

	public String getTermNo() {
		return termNo;
	}

	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

}
