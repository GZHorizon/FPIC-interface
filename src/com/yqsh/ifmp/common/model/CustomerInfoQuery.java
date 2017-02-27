package com.yqsh.ifmp.common.model;

public class CustomerInfoQuery {

	private String account;
	private String cardno;
	private String number;
	private String phone;
	private String bankno;
	private String idcard;
	private String fphysicalno;//物理卡号

	private String password;
	
	public String getFphysicalno() {
		return fphysicalno;
	}

	public void setFphysicalno(String fphysicalno) {
		this.fphysicalno = fphysicalno;
	}

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBankno() {
		return bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
