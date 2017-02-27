package com.yqsh.ifmp.common.model;

public class AccountRole {
	private int account_id;
	private int asmrole_id;
	@Override
	public String toString() {
		return "\n"
				+ "AccountRole [account_id=" + account_id + ", asmrole_id=" + asmrole_id + "]";
	}
	public AccountRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public int getAsmrole_id() {
		return asmrole_id;
	}
	public void setAsmrole_id(int asmrole_id) {
		this.asmrole_id = asmrole_id;
	}
}
