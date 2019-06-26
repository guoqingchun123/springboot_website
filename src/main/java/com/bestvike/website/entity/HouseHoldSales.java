package com.bestvike.website.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;

@Entity
public class HouseHoldSales implements Serializable {

	private static final long serialVersionUID = -8881290539595052784L;

	private String houseUse;
	private String houseHold;
	private String nosaleReason;
	private BigDecimal totalNum;
	private BigDecimal sellNum;
	private BigDecimal nosaledNum;
	private BigDecimal nosaleNum;

	public String getHouseUse() {
		return houseUse;
	}

	public void setHouseUse(String houseUse) {
		this.houseUse = houseUse;
	}

	public String getHouseHold() {
		return houseHold;
	}

	public void setHouseHold(String houseHold) {
		this.houseHold = houseHold;
	}

	public String getNosaleReason() {
		return nosaleReason;
	}

	public void setNosaleReason(String nosaleReason) {
		this.nosaleReason = nosaleReason;
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
}
