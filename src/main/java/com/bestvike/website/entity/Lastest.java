package com.bestvike.website.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;

@Entity
public class Lastest implements Serializable {

	private static final long serialVersionUID = 4552448558264050263L;

	private BigDecimal residenceNum;
	private BigDecimal businessNum;
	private BigDecimal matingNum;
	private BigDecimal residenceArea;
	private BigDecimal businessArea;
	private BigDecimal matingArea;
	private BigDecimal yesTradeNum;
	private BigDecimal yesTradeArea;
	private BigDecimal todTradeNum;
	private BigDecimal todTradeArea;

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

	public BigDecimal getYesTradeNum() {
		return yesTradeNum;
	}

	public void setYesTradeNum(BigDecimal yesTradeNum) {
		this.yesTradeNum = yesTradeNum;
	}

	public BigDecimal getYesTradeArea() {
		return yesTradeArea;
	}

	public void setYesTradeArea(BigDecimal yesTradeArea) {
		this.yesTradeArea = yesTradeArea;
	}

	public BigDecimal getTodTradeNum() {
		return todTradeNum;
	}

	public void setTodTradeNum(BigDecimal todTradeNum) {
		this.todTradeNum = todTradeNum;
	}

	public BigDecimal getTodTradeArea() {
		return todTradeArea;
	}

	public void setTodTradeArea(BigDecimal todTradeArea) {
		this.todTradeArea = todTradeArea;
	}
}
