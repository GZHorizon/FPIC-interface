package com.yqsh.ifmp.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DealDetailBody implements Serializable {

	private String account;//�ʺ�
	private String cardno;//����
	private String name;//����
	private String prebalance;//����ǰ���
	private String money;//���׶�
	private String curbalance;//���׺����
	private String tradetype;//��������
	private String pursetype;//Ǯ������
	private String tradetime;//����ʱ��
	private String posid;//�����ն�
	private String flowno;//��ˮ��

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrebalance() {
		return prebalance;
	}

	public void setPrebalance(String prebalance) {
		this.prebalance = prebalance;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getCurbalance() {
		return curbalance;
	}

	public void setCurbalance(String curbalance) {
		this.curbalance = curbalance;
	}

	public String getTradetype() {
		return tradetype;
	}

	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}

	public String getPursetype() {
		return pursetype;
	}

	public void setPursetype(String pursetype) {
		this.pursetype = pursetype;
	}

	public String getTradetime() {
		return tradetime;
	}

	public void setTradetime(String tradetime) {
		this.tradetime = tradetime;
	}

	public String getPosid() {
		return posid;
	}

	public void setPosid(String posid) {
		this.posid = posid;
	}

	public String getFlowno() {
		return flowno;
	}

	public void setFlowno(String flowno) {
		this.flowno = flowno;
	}

}
