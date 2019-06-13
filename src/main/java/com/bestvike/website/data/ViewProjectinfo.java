package com.bestvike.website.data;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "v_view_projectinfo")
public class ViewProjectinfo implements Serializable {

	private static final long serialVersionUID = 5525452015062772372L;

	@Id
	private String projectId;
	private String projectName;
	private String developerNo;
	private String developerName;
	private String serviceNo;
	private String serviceName;
	private String summary;
	private String state;
	private String address;
	private String divisionCode;
	private String planType;
	private BigDecimal constructArea;
	private BigDecimal coverArea;
	private String houseUse;
	private String salePhone;
	private BigDecimal bldNum;
	private BigDecimal houseNum;
	private BigDecimal maxPrice;
	private BigDecimal minPrice;
	private BigDecimal avgPrice;
	private BigDecimal floorNum;
	private String startDate;
	private String basicFinishDate;
	private String bodyFinishDate;
	private String finishDate;
	private String heatingType;
	private String heatingDate;
	private String aerationType;
	private String aerationDate;
	private String greeningRate;
	private String remark;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDeveloperNo() {
		return developerNo;
	}

	public void setDeveloperNo(String developerNo) {
		this.developerNo = developerNo;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public BigDecimal getConstructArea() {
		return constructArea;
	}

	public void setConstructArea(BigDecimal constructArea) {
		this.constructArea = constructArea;
	}

	public BigDecimal getCoverArea() {
		return coverArea;
	}

	public void setCoverArea(BigDecimal coverArea) {
		this.coverArea = coverArea;
	}

	public String getHouseUse() {
		return houseUse;
	}

	public void setHouseUse(String houseUse) {
		this.houseUse = houseUse;
	}

	public String getSalePhone() {
		return salePhone;
	}

	public void setSalePhone(String salePhone) {
		this.salePhone = salePhone;
	}

	public BigDecimal getBldNum() {
		return bldNum;
	}

	public void setBldNum(BigDecimal bldNum) {
		this.bldNum = bldNum;
	}

	public BigDecimal getHouseNum() {
		return houseNum;
	}

	public void setHouseNum(BigDecimal houseNum) {
		this.houseNum = houseNum;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}

	public BigDecimal getFloorNum() {
		return floorNum;
	}

	public void setFloorNum(BigDecimal floorNum) {
		this.floorNum = floorNum;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getBasicFinishDate() {
		return basicFinishDate;
	}

	public void setBasicFinishDate(String basicFinishDate) {
		this.basicFinishDate = basicFinishDate;
	}

	public String getBodyFinishDate() {
		return bodyFinishDate;
	}

	public void setBodyFinishDate(String bodyFinishDate) {
		this.bodyFinishDate = bodyFinishDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getHeatingType() {
		return heatingType;
	}

	public void setHeatingType(String heatingType) {
		this.heatingType = heatingType;
	}

	public String getHeatingDate() {
		return heatingDate;
	}

	public void setHeatingDate(String heatingDate) {
		this.heatingDate = heatingDate;
	}

	public String getAerationType() {
		return aerationType;
	}

	public void setAerationType(String aerationType) {
		this.aerationType = aerationType;
	}

	public String getAerationDate() {
		return aerationDate;
	}

	public void setAerationDate(String aerationDate) {
		this.aerationDate = aerationDate;
	}

	public String getGreeningRate() {
		return greeningRate;
	}

	public void setGreeningRate(String greeningRate) {
		this.greeningRate = greeningRate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
