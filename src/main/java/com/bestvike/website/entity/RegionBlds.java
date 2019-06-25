package com.bestvike.website.entity;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class RegionBlds implements Serializable {

	private static final long serialVersionUID = -1126988432815615509L;

	private String regionId;
	private String projectId;
	private String bldNo;
	private String location;
	private String bldName;

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBldName() {
		return bldName;
	}

	public void setBldName(String bldName) {
		this.bldName = bldName;
	}
}
