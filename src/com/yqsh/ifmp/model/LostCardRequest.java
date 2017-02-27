package com.yqsh.ifmp.model;

@SuppressWarnings("serial")
public class LostCardRequest extends BaseRequest {

	private String serialnotype;
	private String serialno;
	private String password;
	
	public String getSerialnotype() {
		return serialnotype;
	}
	public void setSerialnotype(String serialnotype) {
		this.serialnotype = serialnotype;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
