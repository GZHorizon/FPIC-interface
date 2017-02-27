package com.yqsh.ifmp.model;

public class CardInfoRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**卡号*/
	private String card_no;
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
