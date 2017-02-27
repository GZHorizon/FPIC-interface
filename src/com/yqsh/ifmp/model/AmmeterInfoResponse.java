package com.yqsh.ifmp.model;

import java.util.List;

import com.yqsh.ifmp.common.model.BuyQtyWater;

@SuppressWarnings("serial")
public class AmmeterInfoResponse extends BaseResponse {
	private String total;//总记录数
	private String totalpage;//总页数
	private String page;//当前页码
	
	private String curDegree;	// 累计用电量
	private String surQty;		// 剩余电量
	List<BuyQtyWater> body;
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(String totalpage) {
		this.totalpage = totalpage;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
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
	public List<BuyQtyWater> getBody() {
		return body;
	}
	public void setBody(List<BuyQtyWater> body) {
		this.body = body;
	}
	
	
}
