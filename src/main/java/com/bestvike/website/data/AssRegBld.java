package com.bestvike.website.data;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Ass_RegBld")
public class AssRegBld implements Serializable {

	private static final long serialVersionUID = 9086418361123853195L;

	@Id
	private String bldId;
	private String regionId;
	private String projectId;
	private String remark;

	public String getBldId() {
		return bldId;
	}

	public void setBldId(String bldId) {
		this.bldId = bldId;
	}

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
