package com.yqsh.ifmp.model;

@SuppressWarnings("serial")
public class RechargeRequest extends BaseRequest {

	private String account;//帐号
	private String cardno;//卡号
	private String tradetype;//交易类型（1：现金充值；2：现金补助-补贴；3：计次补助-补贴；）
	private String trademoney;//交易金额
	private String tradetime;//交易时间
	private String thirdno;//第三方订单号
	private String remark;//备注

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getTradetype() {
		return tradetype;
	}

	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}

	public String getTrademoney() {
		return trademoney;
	}

	public void setTrademoney(String trademoney) {
		this.trademoney = trademoney;
	}

	public String getTradetime() {
		return tradetime;
	}

	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}

	public String getThirdno() {
		return thirdno;
	}

	public void setThirdno(String thirdno) {
		this.thirdno = thirdno;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
