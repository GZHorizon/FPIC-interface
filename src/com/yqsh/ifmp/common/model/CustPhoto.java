package com.yqsh.ifmp.common.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CustPhoto implements Serializable {

	private String fAccountID;
	private byte[] fPhoto;
	
	public String getfAccountID() {
		return fAccountID;
	}
	public void setfAccountID(String fAccountID) {
		this.fAccountID = fAccountID;
	}
	public byte[] getfPhoto() {
		return fPhoto;
	}
	public void setfPhoto(byte[] fPhoto) {
		this.fPhoto = fPhoto;
	}
	
}
