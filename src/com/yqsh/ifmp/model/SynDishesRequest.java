package com.yqsh.ifmp.model;

/**
 * 菜品同步实体
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class SynDishesRequest extends BaseRequest {

	private String dishNo;
	private String dishName;
	private String dishType;//B：大类；S：小类；M：明细
	private String parentNo;//如果菜品类别是大类，那么上级编号为0.
	private String dishUnit;//保留两位小数；如果菜品类别为明细，那么单价必填；
	private String dishZk;//默认0不打折（0-9）如果菜品类别为明细，那么折扣必填；

	public String getDishNo() {
		return dishNo;
	}

	public void setDishNo(String dishNo) {
		this.dishNo = dishNo;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDishType() {
		return dishType;
	}

	public void setDishType(String dishType) {
		this.dishType = dishType;
	}

	public String getParentNo() {
		return parentNo;
	}

	public void setParentNo(String parentNo) {
		this.parentNo = parentNo;
	}

	public String getDishUnit() {
		return dishUnit;
	}

	public void setDishUnit(String dishUnit) {
		this.dishUnit = dishUnit;
	}

	public String getDishZk() {
		return dishZk;
	}

	public void setDishZk(String dishZk) {
		this.dishZk = dishZk;
	}

}
