package com.yqsh.ifmp.model;

@SuppressWarnings("serial")
public class SearchAWRequest extends BaseRequest {
	
	private String index;//起始位置
	private String pagesize;//每页条数
	private String consumerId;//用户编号
	
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
	public String getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	
	
}
