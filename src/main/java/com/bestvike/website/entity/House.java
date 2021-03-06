package com.bestvike.website.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;

@Entity
public class House implements Serializable {

	private static final long serialVersionUID = -5473611323025796123L;

	private String houseId;
	private String showName;
	private String houseHold;
	private String houseUse;
	private String cellNo;
	private String cellName;
	private String floorNo;
	private String floorName;
	private BigDecimal constructArea;
	private BigDecimal ownArea;
	private String presellState;
	private String colorCode;
	private BigDecimal presellPrice;

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public String getHouseHold() {
		return houseHold;
	}

	public void setHouseHold(String houseHold) {
		this.houseHold = houseHold;
	}

	public String getHouseUse() {
		return houseUse;
	}

	public void setHouseUse(String houseUse) {
		this.houseUse = houseUse;
	}

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

	public BigDecimal getConstructArea() {
		return constructArea;
	}

	public void setConstructArea(BigDecimal constructArea) {
		this.constructArea = constructArea;
	}

	public BigDecimal getOwnArea() {
		return ownArea;
	}

	public void setOwnArea(BigDecimal ownArea) {
		this.ownArea = ownArea;
	}

	public String getPresellState() {
		return presellState;
	}

	public void setPresellState(String presellState) {
		this.presellState = presellState;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public BigDecimal getPresellPrice() {
		return presellPrice;
	}

	public void setPresellPrice(BigDecimal presellPrice) {
		this.presellPrice = presellPrice;
	}
}
