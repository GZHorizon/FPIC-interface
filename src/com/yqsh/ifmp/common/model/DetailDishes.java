package com.yqsh.ifmp.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜品明细
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class DetailDishes implements Serializable {

	private Integer id;
	private String code;// 编号
	private String name;// 菜品明细名称
	private BigDecimal unitPrice;// 单价
	private String unit;// 单位
	private Float discount;// 折扣
	private String smallCode;// 所属小类
	private String remark;// 描述

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

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Float getDiscount() {
		return discount;
	}

	public void setDiscount(Float discount) {
		this.discount = discount;
	}

	public String getSmallCode() {
		return smallCode;
	}

	public void setSmallCode(String smallCode) {
		this.smallCode = smallCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "[code:" + this.code + " name:" + this.name + " unitPrice:" + this.unitPrice + " unit:" + this.unit
				+ " discount:" + this.discount + " smallCode:" + this.smallCode + " remark:" + this.remark + "]";
	}
}
