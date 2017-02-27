package com.yqsh.ifmp.model;

@SuppressWarnings("serial")
public class SynCustomerRequest extends BaseRequest {

	private String name;//姓名
	private String orgcode;//组织编号
	private String custno;//学工号
	private String sex;//性别
	private String phone;//电话
	private String idcard;//身份证号
	private String password;//密码
	private String img;//照片路径
	private String customerType;//0是老师还是1是学生
	private String state;//区分新增和修改和删除（0：新增；1：修改；2：删除）

	private String fAccountID;//员工编码
	
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getfAccountID() {
		return fAccountID;
	}

	public void setfAccountID(String fAccountID) {
		this.fAccountID = fAccountID;
	}


}
