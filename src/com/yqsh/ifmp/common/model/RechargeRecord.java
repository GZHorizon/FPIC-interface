package com.yqsh.ifmp.common.model;

import java.util.Date;

public class RechargeRecord {

	private String orderId;//ҵ�񶩵���
	private String orderSeq;//������������ˮ��
	private String tranSeq;//֧��ƽ̨������ˮ��
	private String merchantId;//�̻���
	private String phoneNumber;//�ͻ���-�ֻ�����
	private String iccid;//ѧ�Ż�̹���
	private String name;//����
	private String amount;//���׽��
	private String orderDate;//����ʱ��
	private String clientId;//�����������ͻ���01���Ż���վ02��
	private String versionId;//����汾
	private Date createTime;//�Ǽ�ʱ��
	private String join;//���뷽
	private int flag = 0;//�Ƿ���ˣ�0��δ���ˣ�1���Ѷ��ˣ�
	private String dealTime;//����ʱ��
	private String result;//���׽����0000��֧������ֵ�ɹ���0001��֧���ɹ�����ֵ���δ֪��0002���˿��¼��

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}

	public String getTranSeq() {
		return tranSeq;
	}

	public void setTranSeq(String tranSeq) {
		this.tranSeq = tranSeq;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getJoin() {
		return join;
	}

	public void setJoin(String join) {
		this.join = join;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
