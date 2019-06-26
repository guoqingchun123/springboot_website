package com.bestvike.website.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class Cell implements Serializable {

	private static final long serialVersionUID = -588508668089733059L;

	private String cellNo;
	private String cellName;

	private String id;
	private String text;

	public String getCellNo() {
		return cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	public String getCellName() {
		return cellName;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public String getId() {
		return this.cellNo;
	}

	public void setId(String id) {
		this.cellNo = id;
	}

	public String getText() {
		return cellName;
	}

	public void setText(String text) {
		this.cellName = text;
	}
}
