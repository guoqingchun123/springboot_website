package com.bestvike.website.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;

@Entity
public class MonthSales implements Serializable {

	private static final long serialVersionUID = -8881290539595052784L;

	private String signMonth;
	private BigDecimal residenceNum;
	private BigDecimal businessNum;
	private BigDecimal matingNum;

	public String getSignMonth() {
		return signMonth;
	}

	public void setSignMonth(String signMonth) {
		this.signMonth = signMonth;
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
}
