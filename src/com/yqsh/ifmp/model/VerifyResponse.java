package com.yqsh.ifmp.model;

import java.util.List;

@SuppressWarnings("serial")
public class VerifyResponse extends BaseResponse {

	private List<VerifyBody> body;

	public List<VerifyBody> getBody() {
		return body;
	}

	public void setBody(List<VerifyBody> body) {
		this.body = body;
	}

	
	
}
