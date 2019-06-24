package com.bestvike.website.data;

import com.bestvike.website.entity.BldCells;
import com.bestvike.website.entity.DocFiles;
import com.bestvike.website.entity.FloorSummary;
import com.bestvike.website.entity.HouseHoldSales;
import com.bestvike.website.entity.RegionCell;
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
}