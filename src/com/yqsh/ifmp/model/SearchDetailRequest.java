package com.yqsh.ifmp.model;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("serial")
public class SearchDetailRequest extends BaseRequest {

	private String begintime;//起始日期（格式：yyyy-MM-dd）
	private String endtime;//结束日期(格式：yyyy-MM-dd)
	private String index;//起始位置
	private String pagesize;//每页条数
	private String serialnotype;//编号类型(1:帐号；2:部门编号)
	private String serialno;//编号

	private String startno;
	private String number;
	
	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

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

	public String getSerialnotype() {
		return serialnotype;
	}

	public void setSerialnotype(String serialnotype) {
		this.serialnotype = serialnotype;
	}

	public String getSerialno() {
		return serialno;
	}

	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}

	public String getStartno() {
		return startno;
	}

	public void setStartno(String startno) {
		this.startno = startno;
	}

	public String getNumber() {
		if(StringUtils.isBlank(number)){
			number = "10";
		}
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	
}
