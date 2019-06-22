package com.bestvike.website.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class Cell implements Serializable {

	private static final long serialVersionUID = -588508668089733059L;

	private String cellNo;
	private String cellName;

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
}
