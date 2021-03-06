package com.bestvike.website.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import org.springframework.util.StringUtils;

@Entity
public class Trade implements Serializable {

	private static final long serialVersionUID = 6250313846057457514L;

	private String regionName;
	private String divisionName;
	private String sales;
	private String avgPrice;

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getSales() {
		return sales;
	}

	public void setSales(String sales) {
		this.sales = sales;
	}

	public String getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}
}
