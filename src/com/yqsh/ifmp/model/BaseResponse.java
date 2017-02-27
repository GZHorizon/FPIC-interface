package com.yqsh.ifmp.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaseResponse implements Serializable{

	private String result;
	private String msg;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
