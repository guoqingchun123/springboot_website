package com.bestvike.website.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;

@Entity
public class BldSales implements Serializable {

	private static final long serialVersionUID = -8881290539595052784L;

	private String projectId;
	private String bldNo;
	private BigDecimal totalNum;
	private BigDecimal sellNum;
	private BigDecimal noSaledNum;
	private BigDecimal noSaleNum;
	private BigDecimal closeNum;
	private BigDecimal mortgageNum;
	private BigDecimal frozenNum;

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

	public BigDecimal getNoSaledNum() {
		return noSaledNum;
	}

	public void setNoSaledNum(BigDecimal noSaledNum) {
		this.noSaledNum = noSaledNum;
	}

	public BigDecimal getNoSaleNum() {
		return noSaleNum;
	}

	public void setNoSaleNum(BigDecimal noSaleNum) {
		this.noSaleNum = noSaleNum;
	}

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

	public BigDecimal getCloseNum() {
		return closeNum;
	}

	public void setCloseNum(BigDecimal closeNum) {
		this.closeNum = closeNum;
	}

	public BigDecimal getMortgageNum() {
		return mortgageNum;
	}

	public void setMortgageNum(BigDecimal mortgageNum) {
		this.mortgageNum = mortgageNum;
	}

	public BigDecimal getFrozenNum() {
		return frozenNum;
	}

	public void setFrozenNum(BigDecimal frozenNum) {
		this.frozenNum = frozenNum;
	}
}
