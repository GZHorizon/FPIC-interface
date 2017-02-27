package com.yqsh.ifmp.model;

public class OperatorRequest extends BaseRequest{
	private String organize_id;
	private String operemail;
	private String opertelephone;
	private String operaddress;
	private String opersex;
	private String operage;
	private String operdutydate;
	private String opername;
	private String tbasmaccount_id;
	public OperatorRequest(String organize_id, String operemail, String opertelephone, String operaddress,
			String opersex, String operage, String operdutydate, String opername, String tbasmaccount_id) {
		super();
		this.organize_id = organize_id;
		this.operemail = operemail;
		this.opertelephone = opertelephone;
		this.operaddress = operaddress;
		this.opersex = opersex;
		this.operage = operage;
		this.operdutydate = operdutydate;
		this.opername = opername;
		this.tbasmaccount_id = tbasmaccount_id;
	}
	@Override
	public String toString() {
		return "\n"
				+ "OperatorRequest [organize_id=" + organize_id + ", operemail=" + operemail + ", opertelephone="
				+ opertelephone + ", operaddress=" + operaddress + ", opersex=" + opersex + ", operage=" + operage
				+ ", operdutydate=" + operdutydate + ", opername=" + opername + ", tbasmaccount_id=" + tbasmaccount_id
				+ "]";
	}
	public OperatorRequest() {
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
	public String getOperage() {
		return operage;
	}
	public void setOperage(String operage) {
		this.operage = operage;
	}
	public String getOperdutydate() {
		return operdutydate;
	}
	public void setOperdutydate(String operdutydate) {
		this.operdutydate = operdutydate;
	}
	public String getOpername() {
		return opername;
	}
	public void setOpername(String opername) {
		this.opername = opername;
	}
	public String getTbasmaccount_id() {
		return tbasmaccount_id;
	}
	public void setTbasmaccount_id(String tbasmaccount_id) {
		this.tbasmaccount_id = tbasmaccount_id;
	}
}
