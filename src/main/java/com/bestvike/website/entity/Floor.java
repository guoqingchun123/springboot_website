package com.bestvike.website.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Entity
public class Floor implements Serializable {

	private static final long serialVersionUID = -588508668089733059L;

	private String floorNo;
	private String floorName;

	@Transient
	private Boolean showCell;
	@Transient
	private List<Cell> cells;

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public Boolean getShowCell() {
		return showCell;
	}

	public void setShowCell(Boolean showCell) {
		this.showCell = showCell;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}
}
