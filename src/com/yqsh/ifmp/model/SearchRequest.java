package com.yqsh.ifmp.model;

@SuppressWarnings("serial")
public class SearchRequest extends BaseRequest {

	private String thirdno;//第三方订单号

	public String getThirdno() {
		return thirdno;
	}

	public void setThirdno(String thirdno) {
		this.thirdno = thirdno;
	}
	
}
