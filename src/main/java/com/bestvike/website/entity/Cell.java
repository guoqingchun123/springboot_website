package com.bestvike.website.entity;

import com.bestvike.website.data.ViewHouseInfo;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

@Entity
public class Cell implements Serializable {

	private static final long serialVersionUID = -588508668089733059L;

	private String projectId;
	private String bldNo;
	private String bldName;
	private String cellNo;
	private String cellName;

	private String id;
	private String text;

	@Transient
	private Integer maxHouseNum;
	@Transient
	private Integer showHouseNum;
	@Transient
	private List<ViewHouseInfo> houses;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getBldNo() {
		return bldNo;
	}

	public void setBldNo(String bldNo) {
		this.bldNo = bldNo;
	}

	public String getBldName() {
		return bldName;
	}

	public void setBldName(String bldName) {
		this.bldName = bldName;
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

	public Integer getMaxHouseNum() {
		return maxHouseNum;
	}

	public void setMaxHouseNum(Integer maxHouseNum) {
		this.maxHouseNum = maxHouseNum;
	}

	public Integer getShowHouseNum() {
		return showHouseNum;
	}

	public void setShowHouseNum(Integer showHouseNum) {
		this.showHouseNum = showHouseNum;
	}

	public List<ViewHouseInfo> getHouses() {
		return houses;
	}

	public void setHouses(List<ViewHouseInfo> houses) {
		this.houses = houses;
	}
}
