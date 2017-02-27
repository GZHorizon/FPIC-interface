package com.yqsh.ifmp.model;


@SuppressWarnings("serial")
public class SynOrgRequest extends BaseRequest {
	
	private String orgcode;//组织id
	private String orgname;//组织名称
	private String orgInfo;//组织机构备注
	private String state;//0新增；1修改
	
	private String level;//级别
	
	public String getOrgcode() {
		return orgcode;
	}
	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getOrgInfo() {
		return orgInfo;
	}
	public void setOrgInfo(String orgInfo) {
		this.orgInfo = orgInfo;
	}

}
