package com.yqsh.ifmp.model;

import java.util.List;

@SuppressWarnings("serial")
public class RechargeResponse extends BaseResponse {

	private List<RechargeBody> body;

	public List<RechargeBody> getBody() {
		return body;
	}

	public void setBody(List<RechargeBody> body) {
		this.body = body;
	}
	
}
