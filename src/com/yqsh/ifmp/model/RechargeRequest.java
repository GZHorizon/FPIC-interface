package com.yqsh.ifmp.model;

@SuppressWarnings("serial")
public class RechargeRequest extends BaseRequest {

	private String account;//�ʺ�
	private String cardno;//����
	private String tradetype;//�������ͣ�1���ֽ��ֵ��2���ֽ���-������3���ƴβ���-��������
	private String trademoney;//���׽��
	private String tradetime;//����ʱ��
	private String thirdno;//������������
	private String remark;//��ע

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
