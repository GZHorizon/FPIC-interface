package com.yqsh.ifmp.model;

@SuppressWarnings("serial")
public class SearchResponse extends BaseResponse {

	private String thirdno;//������������
	private String tradeno;//һ��ͨ��ˮ��

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
