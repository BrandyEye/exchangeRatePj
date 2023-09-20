package com.guisi.exchangeRate.vo;

public class SearchBean {
	
	/**
	 * 分頁大小
	 */
	private Integer pageSize;
	
	/**
	 * 分頁頁碼
	 */
	private Integer pageNum;
	
	/**
	 * 時間查詢因子
	 */
	private String dateCondition;

	
	
	public SearchBean(Integer pageSize, Integer pageNum, String dateCondition) {
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.dateCondition = dateCondition;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getDateCondition() {
		return dateCondition;
	}

	public void setDateCondition(String dateCondition) {
		this.dateCondition = dateCondition;
	}
	

}
