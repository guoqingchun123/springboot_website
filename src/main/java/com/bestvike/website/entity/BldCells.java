package com.bestvike.website.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class BldCells implements Serializable {

	private static final long serialVersionUID = -8881290539595052784L;

	private String projectId;
	private String bldNo;
	private String bldName;
	private List<Cell> listCell;

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

	public String getBldName() {
		return bldName;
	}

	public void setBldName(String bldName) {
		this.bldName = bldName;
	}

	public List<Cell> getListCell() {
		return listCell;
	}

	public void setListCell(List<Cell> listCell) {
		this.listCell = listCell;
	}
}
