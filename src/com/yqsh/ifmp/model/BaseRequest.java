package com.yqsh.ifmp.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaseRequest implements Serializable {

	private String thirdcode;// ����������-��ϵͳ����UUID

	public String getThirdcode() {
		return thirdcode;
	}

	public void setThirdcode(String thirdcode) {
		this.thirdcode = thirdcode;
	}

}
