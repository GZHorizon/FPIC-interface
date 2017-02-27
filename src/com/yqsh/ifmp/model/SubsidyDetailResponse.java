package com.yqsh.ifmp.model;

import java.util.List;

@SuppressWarnings("serial")
public class SubsidyDetailResponse extends BaseResponse {

	private String total;//总记录数
	private String totalPage;//总页数
	private String page;//当前页数
	private List<SubsidyDetailBody> body;//返回的补助信息记录集合
	
	public String getTotal() {
		return total;
	}
	
	public void setTotal(String total) {
		this.total = total;
	}
	
	public String getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	
	public String getPage() {
		return page;
	}
	
	public void setPage(String page) {
		this.page = page;
	}

	public List<SubsidyDetailBody> getBody() {
		return body;
	}

	public void setBody(List<SubsidyDetailBody> body) {
		this.body = body;
	}
}
