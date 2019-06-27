package com.bestvike.website.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class CellSummary implements Serializable {

	private static final long serialVersionUID = -5473611323025796123L;

	private String floorNo;
	private String floorName;
	private BigDecimal totalNum;
	private BigDecimal sellNum;
	private String sellColor;
	private BigDecimal nosaledNum;
	private String nosaledColor;
	private BigDecimal nosaleNum;
	private String nosaleColor;

	// 微信端使用
	private List<House> rooms;

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

	public BigDecimal getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}

	public BigDecimal getSellNum() {
		return sellNum;
	}

	public void setSellNum(BigDecimal sellNum) {
		this.sellNum = sellNum;
	}

	public BigDecimal getNosaledNum() {
		return nosaledNum;
	}

	public void setNosaledNum(BigDecimal nosaledNum) {
		this.nosaledNum = nosaledNum;
	}

	public BigDecimal getNosaleNum() {
		return nosaleNum;
	}

	public void setNosaleNum(BigDecimal nosaleNum) {
		this.nosaleNum = nosaleNum;
	}

	public List<House> getRooms() {
		return rooms;
	}

	public void setRooms(List<House> rooms) {
		this.rooms = rooms;
	}

	public String getSellColor() {
		return sellColor;
	}

	public void setSellColor(String sellColor) {
		this.sellColor = sellColor;
	}

	public String getNosaledColor() {
		return nosaledColor;
	}

	public void setNosaledColor(String nosaledColor) {
		this.nosaledColor = nosaledColor;
	}

	public String getNosaleColor() {
		return nosaleColor;
	}

	public void setNosaleColor(String nosaleColor) {
		this.nosaleColor = nosaleColor;
	}
}
