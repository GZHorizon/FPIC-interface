package com.yqsh.ifmp.common.model;

public class AmmeterInfo {
	private String curDegree;	// 累计用电量
	private String surQty;		// 剩余电量
	
	public String getCurDegree() {
		return curDegree;
	}
	public void setCurDegree(String curDegree) {
		this.curDegree = curDegree;
	}
	public String getSurQty() {
		return surQty;
	}
	public void setSurQty(String surQty) {
		this.surQty = surQty;
	}
}
