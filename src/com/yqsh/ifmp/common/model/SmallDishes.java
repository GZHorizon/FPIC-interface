package com.yqsh.ifmp.common.model;

import java.io.Serializable;

/**
 * 菜品小类
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class SmallDishes implements Serializable {

	private Integer id;
	private String code;// 编号
	private String name;// 菜品小类名称
	private String bigCode;// 大类编号
	private String remark;// 备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBigCode() {
		return bigCode;
	}

	public void setBigCode(String bigCode) {
		this.bigCode = bigCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "[code:" + this.code + " name:" + this.name + " bigCode:"
				+ this.bigCode + " remark:" + this.remark + "]";
	}
}
