package com.yqsh.ifmp.common.model;

import com.yqsh.ifmp.util.Global;


public class BaseQuery{
	// Ĭ�ϵĵ�ǰҳ
	private static final int DEFAULT_CURRENT_PAGE = 1;
	// Ĭ�ϵ�ÿҳ����
	private static final int DEFAULT_PAGE_SIZE = 20;
	// ��ǰҳ
	private int currentPage = DEFAULT_CURRENT_PAGE;
	// ÿҳ����
	private int pageSize = DEFAULT_PAGE_SIZE;
	// �ܼ�¼����
	private int total;
	// ��ʼ�ļ�¼����
	private int start;
	private String top;
	private String where;
	
	private String totalMoney = "0";
	private String totalCount = "0";
	
	private String gridSum;
	
	private int awStart;
	private int awEnd;
	
	
	
	public int getAwStart() {
		return awStart;
	}

	public void setAwStart(int awStart) {
		this.awStart = awStart;
	}

	public int getAwEnd() {
		return awEnd;
	}

	public void setAwEnd(int awEnd) {
		this.awEnd = awEnd;
	}

	public String getWhere()
	{
		return where;
	}

	public void setWhere(String where)
	{
		this.where = where;
	}

	public String getTop()
	{
		return top;
	}

	public void setTop(String top)
	{
		this.top = top;
	}

	public Integer getCurrentPage()
	{
		// δ֪��ʼ�ļ�¼������֪��ǰҳ��
		if (start == 0)
			return this.currentPage;
		// ��֪��ʼ�ļ�¼������㵱ǰҳ��
		if (start > 0)
		{
			this.currentPage = (this.start - 1) / this.pageSize + 1;
		}
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage)
	{
		this.currentPage = currentPage;
	}

	public Integer getEnd()
	{
		int end = 0;
		if(Global.ISMYSQL){
			end = getPageSize();
		}else if(Global.ISORACLE){
			if (getTotal().intValue() == 0)
			{
				return end;
			}
			end = getPageSize() * getCurrentPage();
			if (end > this.total)
			{
				end = this.total;
			}
		}
		return end;
	}

	public Integer getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(Integer pageSize)
	{
		if(Global.ISMYSQL){
			this.pageSize = pageSize;
		}else if(Global.ISORACLE){
			this.pageSize = pageSize * ((getStart() / pageSize) + 1);
		}else{
			this.pageSize = pageSize;
		}
	}

	public Integer getStart()
	{
		if(Global.ISMYSQL){
			return this.start;
		} else if (Global.ISORACLE){
			// ��֪��ʼ�ļ�¼����
			if (this.start > 0)
				return this.start;
			// δ֪��ʼ�ļ�¼����,���㿪ʼ�ļ�¼����
			int startRecord = this.pageSize * (this.currentPage - 1) + 1;
			if (startRecord >= this.total)
			{
				startRecord = this.total;
			}
			this.start = startRecord;
		}
		return this.start;
	}

	public void setStart(Integer start)
	{
		if(Global.ISMYSQL){
			this.start = start;
		}else if(Global.ISORACLE){
			this.start = start + 1;
		}else{
			this.start = start;
		}
	}

	public Integer getTotal()
	{
		return total;
	}

	public void setTotal(Integer total)
	{
		this.total = total;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getGridSum() {
		return gridSum;
	}

	public void setGridSum(String gridSum) {
		this.gridSum = gridSum;
	}
	
}
