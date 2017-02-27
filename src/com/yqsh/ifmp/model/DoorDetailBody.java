package com.yqsh.ifmp.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DoorDetailBody implements Serializable {

	private String account;// 帐号
	private String cardno;// 卡号
	private String name;// 姓名
	private String termname;// 终端名称
	private String doorname;//门名称
	private String rectime;//操作时间

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTermname() {
		return termname;
	}

	public void setTermname(String termname) {
		this.termname = termname;
	}

	public String getDoorname() {
		return doorname;
	}

	public void setDoorname(String doorname) {
		this.doorname = doorname;
	}

	public String getRectime() {
		return rectime;
	}

	public void setRectime(String rectime) {
		this.rectime = rectime;
	}

}
