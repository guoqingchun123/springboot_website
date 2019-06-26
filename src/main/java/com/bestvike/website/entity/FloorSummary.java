package com.bestvike.website.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class FloorSummary implements Serializable {

	private static final long serialVersionUID = -5473611323025796123L;

	private String floorNo;
	private String floorName;
	private BigDecimal totalNum;
	private BigDecimal sellNum;
	private BigDecimal nosaledNum;
	private BigDecimal nosaleNum;

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
}
