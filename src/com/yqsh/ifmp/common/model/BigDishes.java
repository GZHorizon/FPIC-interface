package com.yqsh.ifmp.common.model;

import java.io.Serializable;

/**
 * 菜品大类
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class BigDishes implements Serializable {

	private Integer id;
	private String code;// 编号
	private String name;// 菜品大类名称
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "[code:" + this.code + " name:" + this.name + " remark:" + this.remark + "]";
	}

}
