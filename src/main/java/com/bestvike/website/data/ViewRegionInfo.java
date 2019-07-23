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
	private String state;
	private String divisionCode;
	private String address;
	private String preSaleDate;
	private BigDecimal x;
	private BigDecimal y;
	private String remark;
	private String logoPath;
	private String viewPath;
	private String houseHolds;
	private String videoNo;

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
	private List<HouseHoldSale> listHouseHold;
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
	private List<MonthData> listRegionSales;
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
	@Transient
	private String floorNo;
	@Transient
	private String cellNo;
	@Transient
	private List<ViewBldFloor> bldFloors;
	@Transient
	private List<ViewFloorCell> viewFloorCells;
	@Transient
	private List<PriceShow> priceShows;
	@Transient
	private List<HouseHoldSale> residence;
	@Transient
	private RegionHouseSale residenceCollects;
	@Transient
	private List<HouseHoldSale> business;
	@Transient
	private RegionHouseSale businessCollects;
	@Transient
	private List<HouseHoldSale> mating;
	@Transient
	private RegionHouseSale matingCollects;
	@Transient
	private int hasVideo;

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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
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

	public List<ViewBldFloor> getBldFloors() {
		return bldFloors;
	}

	public void setBldFloors(List<ViewBldFloor> bldFloors) {
		this.bldFloors = bldFloors;
	}

	public List<ViewFloorCell> getViewFloorCells() {
		return viewFloorCells;
	}

	public void setViewFloorCells(List<ViewFloorCell> viewFloorCells) {
		this.viewFloorCells = viewFloorCells;
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

	public List<HouseHoldSale> getListHouseHold() {
		return listHouseHold;
	}

	public void setListHouseHold(List<HouseHoldSale> listHouseHold) {
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

	public List<MonthData> getListRegionSales() {
		return listRegionSales;
	}

	public void setListRegionSales(List<MonthData> listRegionSales) {
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

	public String getCellNo() {
		return cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	public List<PriceShow> getPriceShows() {
		return priceShows;
	}

	public void setPriceShows(List<PriceShow> priceShows) {
		this.priceShows = priceShows;
	}

	public List<HouseHoldSale> getResidence() {
		return residence;
	}

	public void setResidence(List<HouseHoldSale> residence) {
		this.residence = residence;
	}

	public RegionHouseSale getResidenceCollects() {
		return residenceCollects;
	}

	public void setResidenceCollects(RegionHouseSale residenceCollects) {
		this.residenceCollects = residenceCollects;
	}

	public List<HouseHoldSale> getBusiness() {
		return business;
	}

	public void setBusiness(List<HouseHoldSale> business) {
		this.business = business;
	}

	public RegionHouseSale getBusinessCollects() {
		return businessCollects;
	}

	public void setBusinessCollects(RegionHouseSale businessCollects) {
		this.businessCollects = businessCollects;
	}

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public List<HouseHoldSale> getMating() {
		return mating;
	}

	public void setMating(List<HouseHoldSale> mating) {
		this.mating = mating;
	}

	public RegionHouseSale getMatingCollects() {
		return matingCollects;
	}

	public void setMatingCollects(RegionHouseSale matingCollects) {
		this.matingCollects = matingCollects;
	}

	public String getVideoNo() {
		return videoNo;
	}

	public void setVideoNo(String videoNo) {
		this.videoNo = videoNo;
	}

	public int getHasVideo() {
		return hasVideo;
	}

	public void setHasVideo(int hasVideo) {
		this.hasVideo = hasVideo;
	}
}