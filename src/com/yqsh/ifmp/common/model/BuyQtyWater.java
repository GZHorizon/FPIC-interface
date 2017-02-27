package com.yqsh.ifmp.common.model;

public class BuyQtyWater {
	private String conSumerId;	// 用户编号
	private String billDate;	// 充值时间
	private String roomName;	// 房间名称
	private String quantity;	// 购买电量
	private String price;		// 电量单价
	private String total;		// 花费金额
	
	public String getConSumerId() {
		return conSumerId;
	}
	public void setConSumerId(String conSumerId) {
		this.conSumerId = conSumerId;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
}
