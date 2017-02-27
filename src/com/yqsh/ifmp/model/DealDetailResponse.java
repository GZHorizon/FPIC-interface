package com.yqsh.ifmp.model;

import java.util.List;

@SuppressWarnings("serial")
public class DealDetailResponse extends BaseResponse {
	
	private String total;//总记录数
	private String totalpage;//总页数
	private String page;//当前页码
	
	List<DealDetailBody> body;

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

	public List<DealDetailBody> getBody() {
		return body;
	}

	public void setBody(List<DealDetailBody> body) {
		this.body = body;
	}
	
	
	
}
