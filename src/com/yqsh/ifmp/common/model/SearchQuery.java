package com.yqsh.ifmp.common.model;

public class SearchQuery extends BaseQuery {

	private String begintime;// ��ʼ���ڣ���ʽ��yyyy-MM-dd��
	private String endtime;// ��������(��ʽ��yyyy-MM-dd)
	private String account;//�ʺ�
	private String deptCode;//���ű��
	private String cardno;
	private String physicscardno;//���?��
	private String idcardno;//���֤��
	private String customerno;
	
	private String consumerId;
	
	private String dishNo;
	
	public String getDishNo() {
		return dishNo;
	}

	public void setDishNo(String dishNo) {
		this.dishNo = dishNo;
	}

	public String getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}

	public String getCustomerno() {
		return customerno;
	}

	public void setCustomerno(String customerno) {
		this.customerno = customerno;
	}

	private String tableName;

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getPhysicscardno() {
		return physicscardno;
	}

	public void setPhysicscardno(String physicscardno) {
		this.physicscardno = physicscardno;
	}

	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

}
