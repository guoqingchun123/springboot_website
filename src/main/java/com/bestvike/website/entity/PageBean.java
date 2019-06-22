package com.bestvike.website.entity;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class PageBean implements Serializable {

	private static final long serialVersionUID = -3251485206891346573L;

	private int pageNo;
	private int pageSize;
	private long totalSize;
	private int totalPage;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
