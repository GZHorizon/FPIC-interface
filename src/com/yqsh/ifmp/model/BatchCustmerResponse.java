package com.yqsh.ifmp.model;

import java.util.List;

import com.yqsh.ifmp.common.model.CustomerInfo;

public class BatchCustmerResponse extends BaseResponse{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String total;//总记录数
	private String totalpage;//总页数
	private String page;//当前页码
	
	List<CustomerInfo> body;

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

	public List<CustomerInfo> getBody() {
		return body;
	}

	public void setBody(List<CustomerInfo> body) {
		this.body = body;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
