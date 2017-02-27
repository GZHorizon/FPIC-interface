package com.yqsh.ifmp.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RechargeBody implements Serializable  {

	private String thirdno;
	private String tradeno;
	
	public String getThirdno() {
		return thirdno;
	}
	public void setThirdno(String thirdno) {
		this.thirdno = thirdno;
	}
	public String getTradeno() {
		return tradeno;
	}
	public void setTradeno(String tradeno) {
		this.tradeno = tradeno;
	}
	
	
	
}
