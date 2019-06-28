package com.bestvike.website.entity;

import com.bestvike.website.config.Const;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;

@Entity
public class BldView implements Serializable {

	private static final long serialVersionUID = -8881290539595052784L;

	private String projectId;
	private String bldNo;
	private String bldName;
	private BigDecimal cellFloorNum;
	private List<Cell> cells;
	private List<Floor> floors;
	private BldSales bldSales;

	private List<Map> legends;

	private boolean showCell;

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

	public BigDecimal getCellFloorNum() {
		return cellFloorNum;
	}

	public void setCellFloorNum(BigDecimal cellFloorNum) {
		this.cellFloorNum = cellFloorNum;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	public List<Floor> getFloors() {
		return floors;
	}

	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}

	public BldSales getBldSales() {
		return bldSales;
	}

	public void setBldSales(BldSales bldSales) {
		this.bldSales = bldSales;
	}

	public boolean isShowCell() {
		return showCell;
	}

	public void setShowCell(boolean showCell) {
		this.showCell = showCell;
	}

	public List<Map> getLegends() {
		return Const.legends;
	}

	public void setLegends(List<Map> legends) {
		this.legends = legends;
	}
}
