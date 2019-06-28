package com.bestvike.website.data;

import com.bestvike.website.entity.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "View_RegionInfo")
public class ViewRegionInfo implements Serializable {

	private static final long serialVersionUID = -3053189041412826315L;

	@Id
	private String regionId;
	private String regionName;
	private String address;
	private String preSaleDate;
	private BigDecimal x;
	private BigDecimal y;
	private String remark;
	private String logoPath;
	private String viewPath;
	private String houseHolds;

	@Transient
	private String houseNum;
	@Transient
	private String salePhone;
	@Transient
	private String areaRange;
	@Transient
	private String houseHold;
	@Transient
	private BigDecimal avgPrice;
	@Transient
	private String greeningRate;
	@Transient
	private List<Map<String, Object>> salesData;
	@Transient
	private List<HouseHoldSales> listHouseHold;
	@Transient
	private List<BldCells> listBldCells;
	@Transient
	private List<FloorSummary> listCellFloors;
	@Transient
	private List<ViewHouseInfo> listHouse;
	@Transient
	private List<DocFiles> listDocFiles;
	@Transient
	private List<RegionBlds> listRegionBlds;
	@Transient
	private BldSales bldSales;
	@Transient
	private List<MonthSales> listRegionSales;
	@Transient
	private List<String> regionLogos;

	@Transient
	private Boolean showCell;
	@Transient
	private List<Floor> floors;
	@Transient
	private List<Cell> cells;
	@Transient
	private BldView bldView;

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPreSaleDate() {
		return preSaleDate;
	}

	public void setPreSaleDate(String preSaleDate) {
		this.preSaleDate = preSaleDate;
	}

	public BigDecimal getX() {
		return x;
	}

	public void setX(BigDecimal x) {
		this.x = x;
	}

	public BigDecimal getY() {
		return y;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSalePhone() {
		return salePhone;
	}

	public void setSalePhone(String salePhone) {
		this.salePhone = salePhone;
	}

	public String getAreaRange() {
		return areaRange;
	}

	public void setAreaRange(String areaRange) {
		this.areaRange = areaRange;
	}

	public String getHouseHold() {
		return houseHold;
	}

	public void setHouseHold(String houseHold) {
		this.houseHold = houseHold;
	}

	public BigDecimal getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getGreeningRate() {
		return greeningRate;
	}

	public void setGreeningRate(String greeningRate) {
		this.greeningRate = greeningRate;
	}

	public List<Map<String, Object>> getSalesData() {
		return salesData;
	}

	public void setSalesData(List<Map<String, Object>> salesData) {
		this.salesData = salesData;
	}

	public String getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(String houseNum) {
		this.houseNum = houseNum;
	}

	public List<HouseHoldSales> getListHouseHold() {
		return listHouseHold;
	}

	public void setListHouseHold(List<HouseHoldSales> listHouseHold) {
		this.listHouseHold = listHouseHold;
	}

	public List<BldCells> getListBldCells() {
		return listBldCells;
	}

	public void setListBldCells(List<BldCells> listBldCells) {
		this.listBldCells = listBldCells;
	}

	public List<ViewHouseInfo> getListHouse() {
		return listHouse;
	}

	public void setListHouse(List<ViewHouseInfo> listHouse) {
		this.listHouse = listHouse;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public List<FloorSummary> getListCellFloors() {
		return listCellFloors;
	}

	public void setListCellFloors(List<FloorSummary> listCellFloors) {
		this.listCellFloors = listCellFloors;
	}

	public List<DocFiles> getListDocFiles() {
		return listDocFiles;
	}

	public void setListDocFiles(List<DocFiles> listDocFiles) {
		this.listDocFiles = listDocFiles;
	}

	public String getViewPath() {
		return viewPath;
	}

	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public List<RegionBlds> getListRegionBlds() {
		return listRegionBlds;
	}

	public void setListRegionBlds(List<RegionBlds> listRegionBlds) {
		this.listRegionBlds = listRegionBlds;
	}

	public String getHouseHolds() {
		return houseHolds;
	}

	public void setHouseHolds(String houseHolds) {
		this.houseHolds = houseHolds;
	}

	public BldSales getBldSales() {
		return bldSales;
	}

	public void setBldSales(BldSales bldSales) {
		this.bldSales = bldSales;
	}

	public List<MonthSales> getListRegionSales() {
		return listRegionSales;
	}

	public void setListRegionSales(List<MonthSales> listRegionSales) {
		this.listRegionSales = listRegionSales;
	}

	public List<String> getRegionLogos() {
		return regionLogos;
	}

	public void setRegionLogos(List<String> regionLogos) {
		this.regionLogos = regionLogos;
	}

	public Boolean getShowCell() {
		return showCell;
	}

	public void setShowCell(Boolean showCell) {
		this.showCell = showCell;
	}

	public List<Floor> getFloors() {
		return floors;
	}

	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public void setCells(List<Cell> cells) {
		this.cells = cells;
	}

	public BldView getBldView() {
		return bldView;
	}

	public void setBldView(BldView bldView) {
		this.bldView = bldView;
	}
}