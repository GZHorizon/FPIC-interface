package com.yqsh.ifmp.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VerifyBody implements Serializable {

	private String account;// 账户
	private String name;// 姓名
	private String sex;// 性别
	private String cardno;// 卡号
	private String cardstate;// ̬ 卡状态
	private String balance1 = "0.00";// 现金金额
	private String balance2 = "0.00";// 补助金额
	private String balance3 = "0.00";// 计次卡次数
	private String deptname;// 部门
	private String serialnumber;// 应用序列号
	private String custno;// 学工号
	private String mphone; // 手机号
	private String idcard; // 身份证号码

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getCardstate() {
		return cardstate;
	}

	public void setCardstate(String cardstate) {
		this.cardstate = cardstate;
	}

	public String getBalance1() {
		return balance1;
	}

	public void setBalance1(String balance1) {
		this.balance1 = balance1;
	}

	public String getBalance2() {
		return balance2;
	}

	public void setBalance2(String balance2) {
		this.balance2 = balance2;
	}

	public String getBalance3() {
		return balance3;
	}

	public void setBalance3(String balance3) {
		this.balance3 = balance3;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

}
