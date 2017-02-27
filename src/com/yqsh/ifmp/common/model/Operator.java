package com.yqsh.ifmp.common.model;

import java.util.Date;

public class Operator {
	private String organize_id;
	private String operemail;
	private String opertelephone;
	private String operaddress;
	private String opersex;
	private Date operage;
	private Date operdutydate;
	private String opername;
	private int tbasmaccount_id;
	@Override
	public String toString() {
		return "\n"
				+ "Operator [organize_id=" + organize_id + ", operemail=" + operemail + ", opertelephone=" + opertelephone
				+ ", operaddress=" + operaddress + ", opersex=" + opersex + ", operage=" + operage + ", operdutydate="
				+ operdutydate + ", opername=" + opername + ", tbasmaccount_id=" + tbasmaccount_id + "]";
	}
	public Operator() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getOrganize_id() {
		return organize_id;
	}
	public void setOrganize_id(String organize_id) {
		this.organize_id = organize_id;
	}
	public String getOperemail() {
		return operemail;
	}
	public void setOperemail(String operemail) {
		this.operemail = operemail;
	}
	public String getOpertelephone() {
		return opertelephone;
	}
	public void setOpertelephone(String opertelephone) {
		this.opertelephone = opertelephone;
	}
	public String getOperaddress() {
		return operaddress;
	}
	public void setOperaddress(String operaddress) {
		this.operaddress = operaddress;
	}
	public String getOpersex() {
		return opersex;
	}
	public void setOpersex(String opersex) {
		this.opersex = opersex;
	}
	public Date getOperage() {
		return operage;
	}
	public void setOperage(Date operage) {
		this.operage = operage;
	}
	public Date getOperdutydate() {
		return operdutydate;
	}
	public void setOperdutydate(Date operdutydate) {
		this.operdutydate = operdutydate;
	}
	public String getOpername() {
		return opername;
	}
	public void setOpername(String opername) {
		this.opername = opername;
	}
	public int getTbasmaccount_id() {
		return tbasmaccount_id;
	}
	public void setTbasmaccount_id(int tbasmaccount_id) {
		this.tbasmaccount_id = tbasmaccount_id;
	}
	
}
