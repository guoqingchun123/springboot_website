package com.bestvike.website.data;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class ArcProjectCoordinate implements Serializable {

	@Id
	private String projectno;
	private String xcoordinate;
	private String ycoordinate;
	private BigDecimal averageprice;
	private BigDecimal popularity;

	@Transient
	private String projectname;
	@Transient
	private String address;

	public String getProjectno() {
		return projectno;
	}

	public void setProjectno(String projectno) {
		this.projectno = projectno;
	}

	public String getXcoordinate() {
		return xcoordinate;
	}

	public void setXcoordinate(String xcoordinate) {
		this.xcoordinate = xcoordinate;
	}

	public String getYcoordinate() {
		return ycoordinate;
	}

	public void setYcoordinate(String ycoordinate) {
		this.ycoordinate = ycoordinate;
	}

	public BigDecimal getAverageprice() {
		return averageprice;
	}

	public void setAverageprice(BigDecimal averageprice) {
		this.averageprice = averageprice;
	}

	public BigDecimal getPopularity() {
		return popularity;
	}

	public void setPopularity(BigDecimal popularity) {
		this.popularity = popularity;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
