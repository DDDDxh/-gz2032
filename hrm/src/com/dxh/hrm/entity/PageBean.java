package com.dxh.hrm.entity;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
	private int rowCount;//总数据条数
	private int pageSize=5;//页面大小
	private int pageNow;//当前页号
	private int pageCount;//总页数
	
	private List<T> list = new ArrayList<>();
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		setPageCount();
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		setPageCount();
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount() {
		this.pageCount = this.rowCount%this.pageSize == 0 ? this.rowCount/this.pageSize:this.rowCount/this.pageSize+1;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
