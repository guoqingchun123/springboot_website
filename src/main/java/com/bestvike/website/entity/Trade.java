package com.bestvike.website.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import org.springframework.util.StringUtils;

@Entity
public class Trade implements Serializable {

	private static final long serialVersionUID = 6250313846057457514L;

	private String regionName;
	private String address;
	private String houseHold;
	private String signDate;
	private String recordDate;
	private String payType;
	private String buyerNames;
	private String contractNo;

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

	public String getHouseHold() {
		return houseHold;
	}

	public void setHouseHold(String houseHold) {
		this.houseHold = houseHold;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getBuyerNames() {
		return buyerNames;
	}

	public void setBuyerNames(String buyerNames) {
		this.buyerNames = buyerNames;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
}
