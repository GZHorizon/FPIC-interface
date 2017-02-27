package com.yqsh.ifmp.model;

@SuppressWarnings("serial")
public class SearchResponse extends BaseResponse {

	private String thirdno;//第三方订单号
	private String tradeno;//一卡通流水号

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
