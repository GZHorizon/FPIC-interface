package com.yqsh.ifmp.model;

public class BatchCustmerRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String index;//起始位置
	private String pagesize;//每页条数
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getPagesize() {
		return pagesize;
	}
	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
