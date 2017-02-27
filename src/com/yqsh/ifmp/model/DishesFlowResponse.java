package com.yqsh.ifmp.model;

import java.util.List;

@SuppressWarnings("serial")
public class DishesFlowResponse extends BaseResponse {

	private String total;
	private String totalpage;
	private String page;

	private List<DishesFlowData> body;

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

	public List<DishesFlowData> getBody() {
		return body;
	}

	public void setBody(List<DishesFlowData> body) {
		this.body = body;
	}

}
