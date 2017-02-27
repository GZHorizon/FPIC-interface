package com.yqsh.ifmp.model;

public class DeductionRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**物理卡号*/
	private String physicalNo;
	/**交易时间*/
	private String consTime;
	/**机号*/
	private String posId;
	/**交易金额*/
	private String consFare;
	/**操作员号*/
	private String oper_id;
	public String getPhysicalNo() {
		return physicalNo;
	}
	public void setPhysicalNo(String physicalNo) {
		this.physicalNo = physicalNo;
	}
	public String getConsTime() {
		return consTime;
	}
	public void setConsTime(String consTime) {
		this.consTime = consTime;
	}
	public String getPosId() {
		return posId;
	}
	public void setPosId(String posId) {
		this.posId = posId;
	}
	public String getConsFare() {
		return consFare;
	}
	public void setConsFare(String consFare) {
		this.consFare = consFare;
	}
	public String getOper_id() {
		return oper_id;
	}
	public void setOper_id(String oper_id) {
		this.oper_id = oper_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
