package com.bestvike.website.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;

@Entity
public class MonthData implements Serializable {

	private static final long serialVersionUID = -8881290539595052784L;

	private String dataMonth;
	private BigDecimal totalNum;
	private BigDecimal totalStockNum;
	private BigDecimal totalArea;
	private BigDecimal totalStockArea;
	// 住宅销量套数
	private BigDecimal residenceNum;
	// 商业销量套数
	private BigDecimal businessNum;
	// 配套销量套数
	private BigDecimal matingNum;
	// 住宅销量面积
	private BigDecimal residenceArea;
	// 商业销量面积
	private BigDecimal businessArea;
	// 配套销量面积
	private BigDecimal matingArea;
	// 住宅存量套数
	private BigDecimal residenceStockNum;
	// 住宅存量面积
	private BigDecimal residenceStockArea;
	// 商业存量套数
	private BigDecimal businessStockNum;
	// 商业存量面积
	private BigDecimal businessStockArea;
	// 配套存量套数
	private BigDecimal matingStockNum;
	// 配套存量面积
	private BigDecimal matingStockArea;

	public String getDataMonth() {
		return dataMonth;
	}

	public void setDataMonth(String dataMonth) {
		this.dataMonth = dataMonth;
	}

	public BigDecimal getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}

	public BigDecimal getTotalStockNum() {
		return totalStockNum;
	}

	public void setTotalStockNum(BigDecimal totalStockNum) {
		this.totalStockNum = totalStockNum;
	}

	public BigDecimal getTotalArea() {
		return totalArea;
	}

	public void setTotalArea(BigDecimal totalArea) {
		this.totalArea = totalArea;
	}

	public BigDecimal getTotalStockArea() {
		return totalStockArea;
	}

	public void setTotalStockArea(BigDecimal totalStockArea) {
		this.totalStockArea = totalStockArea;
	}

	public BigDecimal getResidenceNum() {
		return residenceNum;
	}

	public void setResidenceNum(BigDecimal residenceNum) {
		this.residenceNum = residenceNum;
	}

	public BigDecimal getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(BigDecimal businessNum) {
		this.businessNum = businessNum;
	}

	public BigDecimal getMatingNum() {
		return matingNum;
	}

	public void setMatingNum(BigDecimal matingNum) {
		this.matingNum = matingNum;
	}

	public BigDecimal getResidenceArea() {
		return residenceArea;
	}

	public void setResidenceArea(BigDecimal residenceArea) {
		this.residenceArea = residenceArea;
	}

	public BigDecimal getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(BigDecimal businessArea) {
		this.businessArea = businessArea;
	}

	public BigDecimal getMatingArea() {
		return matingArea;
	}

	public void setMatingArea(BigDecimal matingArea) {
		this.matingArea = matingArea;
	}

	public BigDecimal getResidenceStockNum() {
		return residenceStockNum;
	}

	public void setResidenceStockNum(BigDecimal residenceStockNum) {
		this.residenceStockNum = residenceStockNum;
	}

	public BigDecimal getResidenceStockArea() {
		return residenceStockArea;
	}

	public void setResidenceStockArea(BigDecimal residenceStockArea) {
		this.residenceStockArea = residenceStockArea;
	}

	public BigDecimal getBusinessStockNum() {
		return businessStockNum;
	}

	public void setBusinessStockNum(BigDecimal businessStockNum) {
		this.businessStockNum = businessStockNum;
	}

	public BigDecimal getBusinessStockArea() {
		return businessStockArea;
	}

	public void setBusinessStockArea(BigDecimal businessStockArea) {
		this.businessStockArea = businessStockArea;
	}

	public BigDecimal getMatingStockNum() {
		return matingStockNum;
	}

	public void setMatingStockNum(BigDecimal matingStockNum) {
		this.matingStockNum = matingStockNum;
	}

	public BigDecimal getMatingStockArea() {
		return matingStockArea;
	}

	public void setMatingStockArea(BigDecimal matingStockArea) {
		this.matingStockArea = matingStockArea;
	}
}