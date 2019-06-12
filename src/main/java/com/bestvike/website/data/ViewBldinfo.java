package com.bestvike.website.data;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "View_BldInfo")
public class ViewBldinfo implements Serializable {

	@Id
	private String bldId;
	private String sheetNo;
	private String landNo;
	private String orientation;
	private String preSaleNo;
	private String preSaleDate;
	private String corpName;
	private String subName;
	private String bldName;
	private String bldAddress;
	private String frameAttr;
	private String heightAttr;
	private BigDecimal overFloor;
	private BigDecimal underFloor;
	private String startDate;
	private String finishDate;
	private String remark;

	public String getBldId() {
		return bldId;
	}

	public void setBldId(String bldId) {
		this.bldId = bldId;
	}

	public String getSheetNo() {
		return sheetNo;
	}

	public void setSheetNo(String sheetNo) {
		this.sheetNo = sheetNo;
	}

	public String getLandNo() {
		return landNo;
	}

	public void setLandNo(String landNo) {
		this.landNo = landNo;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getPreSaleNo() {
		return preSaleNo;
	}

	public void setPreSaleNo(String preSaleNo) {
		this.preSaleNo = preSaleNo;
	}

	public String getPreSaleDate() {
		return preSaleDate;
	}

	public void setPreSaleDate(String preSaleDate) {
		this.preSaleDate = preSaleDate;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getBldName() {
		return bldName;
	}

	public void setBldName(String bldName) {
		this.bldName = bldName;
	}

	public String getBldAddress() {
		return bldAddress;
	}

	public void setBldAddress(String bldAddress) {
		this.bldAddress = bldAddress;
	}

	public String getFrameAttr() {
		return frameAttr;
	}

	public void setFrameAttr(String frameAttr) {
		this.frameAttr = frameAttr;
	}

	public String getHeightAttr() {
		return heightAttr;
	}

	public void setHeightAttr(String heightAttr) {
		this.heightAttr = heightAttr;
	}

	public BigDecimal getOverFloor() {
		return overFloor;
	}

	public void setOverFloor(BigDecimal overFloor) {
		this.overFloor = overFloor;
	}

	public BigDecimal getUnderFloor() {
		return underFloor;
	}

	public void setUnderFloor(BigDecimal underFloor) {
		this.underFloor = underFloor;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
