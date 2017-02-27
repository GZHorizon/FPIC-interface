package com.yqsh.ifmp.model;

@SuppressWarnings("serial")
public class DishesFlowQueryRequest extends BaseRequest {

	private String begintime;//起始日期（格式：yyyy-MM-dd）
	private String endtime;//结束日期(格式：yyyy-MM-dd)
	private String index;//起始位置
	private String pagesize;//每页条数
	private String dishNo;//菜品编号
	
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
	public String getDishNo() {
		return dishNo;
	}
	public void setDishNo(String dishNo) {
		this.dishNo = dishNo;
	}
	
}
