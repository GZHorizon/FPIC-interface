package com.yqsh.ifmp.common.model;

public class CustomerInfo {
	private String cardNo;// 卡号
	private String custNo; // 学工号
	private String name; // 姓名
	private String idcard;// 身份证号码
	private String deptName;// 部门名称
	private String deptId;//部门编号
	private int cardState; // 卡状态
	private String sex;// 性别
	private String accountId; // 账号id
	
	
	private String balance;// 余额(旧卡的总余额)
	
	private String serialnumber;// 物理卡号

	private int appid;// 应用id
	private int cardtypeid;// 卡类型id
	private String mphone;// 手机
	
	/************ 新卡新增字段 **************/
	private String balance1; // 现金余额
	private String balance2; // 补助余额
	private String balance3;// 余额次数

	@Override
	public String toString() {
		return "\n"
				+ "CustomerInfo [cardNo=" + cardNo + ", custNo=" + custNo + ", name=" + name + ", idcard=" + idcard
				+ ", deptName=" + deptName + ", deptId=" + deptId + ", cardState=" + cardState + ", sex=" + sex
				+ ", accountId=" + accountId + ", balance=" + balance + ", serialnumber=" + serialnumber + ", appid="
				+ appid + ", cardtypeid=" + cardtypeid + ", mphone=" + mphone + ", balance1=" + balance1 + ", balance2="
				+ balance2 + ", balance3=" + balance3 + "]";
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public int getCardState() {
		return cardState;
	}

	public void setCardState(int cardState) {
		this.cardState = cardState;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}

	public int getCardtypeid() {
		return cardtypeid;
	}

	public void setCardtypeid(int cardtypeid) {
		this.cardtypeid = cardtypeid;
	}

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getBalance1() {
		return balance1;
	}

	public void setBalance1(String balance1) {
		this.balance1 = balance1;
	}

	public String getBalance2() {
		return balance2;
	}

	public void setBalance2(String balance2) {
		this.balance2 = balance2;
	}

	public String getBalance3() {
		return balance3;
	}

	public void setBalance3(String balance3) {
		this.balance3 = balance3;
	}

}
