package com.bestvike.website.data;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "View_HouseInfo")
public class ViewHouseInfo implements Serializable {

	private static final long serialVersionUID = 7701428993616884751L;

	@Id
	private String houseId;
	private String bldId;
	private String projectId;
	private String bldNo;
	private String houseUse;
	private String houseProp;
	private String houseType;
	private String cellNo;
	private String cellName;
	private String floorNo;
	private String floorName;
	private String showName;
	private BigDecimal constructArea;
	private BigDecimal realConstructArea;
	private BigDecimal ownArea;
	private BigDecimal perConstructArea;
	private BigDecimal realOwnArea;
	private BigDecimal shareArea;
	private BigDecimal realShareArea;
	private BigDecimal balconyArea;
	private BigDecimal realBalconyArea;
	private BigDecimal otherArea;
	private String houseStructure;
	private String houseHold;
	private BigDecimal balconyNum;
	private String roomDirection;
	private BigDecimal skipNum;
	private BigDecimal mergeNum;
	private BigDecimal presellPrice;
	private BigDecimal realPrice;
	private String presellState;
	private String mortgageFlag;
	private String nortgageNo;
	private String mortgageDate;
	private String closeFlag;
	private String closeNo;
	private String closeStartDate;
	private String closeStopDate;
	private String frozenFlag;
	private String preregFlag;
	private String preregDate;
	private String provcardFlag;
	private String provcardDate;
	private String passFlag;
	private String passDate;
	private String pledgeFlag;
	private String pledgeNo;
	private String pledgeDate;
	private String salesFlag;
	private String nosaleReason;
	private BigDecimal orderNum;
	private String remark;

	@Transient
	private BigDecimal initRoom;
	@Transient
	private BigDecimal saleNum;
	@Transient
	private BigDecimal noSaleNum;
	@Transient
	private BigDecimal noSaledNum;
	@Transient
	private BigDecimal totalNum;
	@Transient
	private String presellStateName;
	@Transient
	private String presellStateCode;

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public String getBldId() {
		return bldId;
	}

	public void setBldId(String bldId) {
		this.bldId = bldId;
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

	public String getHouseUse() {
		return houseUse;
	}

	public void setHouseUse(String houseUse) {
		this.houseUse = houseUse;
	}

	public String getHouseProp() {
		return houseProp;
	}

	public void setHouseProp(String houseProp) {
		this.houseProp = houseProp;
	}

	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public String getCellNo() {
		return cellNo;
	}

	public void setCellNo(String cellNo) {
		this.cellNo = cellNo;
	}

	public String getCellName() {
		return cellName;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public BigDecimal getConstructArea() {
		return constructArea;
	}

	public void setConstructArea(BigDecimal constructArea) {
		this.constructArea = constructArea;
	}

	public BigDecimal getRealConstructArea() {
		return realConstructArea;
	}

	public void setRealConstructArea(BigDecimal realConstructArea) {
		this.realConstructArea = realConstructArea;
	}

	public BigDecimal getOwnArea() {
		return ownArea;
	}

	public void setOwnArea(BigDecimal ownArea) {
		this.ownArea = ownArea;
	}

	public BigDecimal getPerConstructArea() {
		return perConstructArea;
	}

	public void setPerConstructArea(BigDecimal perConstructArea) {
		this.perConstructArea = perConstructArea;
	}

	public BigDecimal getRealOwnArea() {
		return realOwnArea;
	}

	public void setRealOwnArea(BigDecimal realOwnArea) {
		this.realOwnArea = realOwnArea;
	}

	public BigDecimal getShareArea() {
		return shareArea;
	}

	public void setShareArea(BigDecimal shareArea) {
		this.shareArea = shareArea;
	}

	public BigDecimal getRealShareArea() {
		return realShareArea;
	}

	public void setRealShareArea(BigDecimal realShareArea) {
		this.realShareArea = realShareArea;
	}

	public BigDecimal getBalconyArea() {
		return balconyArea;
	}

	public void setBalconyArea(BigDecimal balconyArea) {
		this.balconyArea = balconyArea;
	}

	public BigDecimal getRealBalconyArea() {
		return realBalconyArea;
	}

	public void setRealBalconyArea(BigDecimal realBalconyArea) {
		this.realBalconyArea = realBalconyArea;
	}

	public BigDecimal getOtherArea() {
		return otherArea;
	}

	public void setOtherArea(BigDecimal otherArea) {
		this.otherArea = otherArea;
	}

	public String getHouseStructure() {
		return houseStructure;
	}

	public void setHouseStructure(String houseStructure) {
		this.houseStructure = houseStructure;
	}

	public String getHouseHold() {
		return houseHold;
	}

	public void setHouseHold(String houseHold) {
		this.houseHold = houseHold;
	}

	public BigDecimal getBalconyNum() {
		return balconyNum;
	}

	public void setBalconyNum(BigDecimal balconyNum) {
		this.balconyNum = balconyNum;
	}

	public String getRoomDirection() {
		return roomDirection;
	}

	public void setRoomDirection(String roomDirection) {
		this.roomDirection = roomDirection;
	}

	public BigDecimal getSkipNum() {
		return skipNum;
	}

	public void setSkipNum(BigDecimal skipNum) {
		this.skipNum = skipNum;
	}

	public BigDecimal getMergeNum() {
		return mergeNum;
	}

	public void setMergeNum(BigDecimal mergeNum) {
		this.mergeNum = mergeNum;
	}

	public BigDecimal getPresellPrice() {
		return presellPrice;
	}

	public void setPresellPrice(BigDecimal presellPrice) {
		this.presellPrice = presellPrice;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public String getPresellState() {
		return presellState;
	}

	public void setPresellState(String presellState) {
		this.presellState = presellState;
	}

	public String getMortgageFlag() {
		return mortgageFlag;
	}

	public void setMortgageFlag(String mortgageFlag) {
		this.mortgageFlag = mortgageFlag;
	}

	public String getNortgageNo() {
		return nortgageNo;
	}

	public void setNortgageNo(String nortgageNo) {
		this.nortgageNo = nortgageNo;
	}

	public String getMortgageDate() {
		return mortgageDate;
	}

	public void setMortgageDate(String mortgageDate) {
		this.mortgageDate = mortgageDate;
	}

	public String getCloseFlag() {
		return closeFlag;
	}

	public void setCloseFlag(String closeFlag) {
		this.closeFlag = closeFlag;
	}

	public String getCloseNo() {
		return closeNo;
	}

	public void setCloseNo(String closeNo) {
		this.closeNo = closeNo;
	}

	public String getCloseStartDate() {
		return closeStartDate;
	}

	public void setCloseStartDate(String closeStartDate) {
		this.closeStartDate = closeStartDate;
	}

	public String getCloseStopDate() {
		return closeStopDate;
	}

	public void setCloseStopDate(String closeStopDate) {
		this.closeStopDate = closeStopDate;
	}

	public String getFrozenFlag() {
		return frozenFlag;
	}

	public void setFrozenFlag(String frozenFlag) {
		this.frozenFlag = frozenFlag;
	}

	public String getPreregFlag() {
		return preregFlag;
	}

	public void setPreregFlag(String preregFlag) {
		this.preregFlag = preregFlag;
	}

	public String getPreregDate() {
		return preregDate;
	}

	public void setPreregDate(String preregDate) {
		this.preregDate = preregDate;
	}

	public String getProvcardFlag() {
		return provcardFlag;
	}

	public void setProvcardFlag(String provcardFlag) {
		this.provcardFlag = provcardFlag;
	}

	public String getProvcardDate() {
		return provcardDate;
	}

	public void setProvcardDate(String provcardDate) {
		this.provcardDate = provcardDate;
	}

	public String getPassFlag() {
		return passFlag;
	}

	public void setPassFlag(String passFlag) {
		this.passFlag = passFlag;
	}

	public String getPassDate() {
		return passDate;
	}

	public void setPassDate(String passDate) {
		this.passDate = passDate;
	}

	public String getPledgeFlag() {
		return pledgeFlag;
	}

	public void setPledgeFlag(String pledgeFlag) {
		this.pledgeFlag = pledgeFlag;
	}

	public String getPledgeNo() {
		return pledgeNo;
	}

	public void setPledgeNo(String pledgeNo) {
		this.pledgeNo = pledgeNo;
	}

	public String getPledgeDate() {
		return pledgeDate;
	}

	public void setPledgeDate(String pledgeDate) {
		this.pledgeDate = pledgeDate;
	}

	public BigDecimal getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(BigDecimal orderNum) {
		this.orderNum = orderNum;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getInitRoom() {
		return initRoom;
	}

	public void setInitRoom(BigDecimal initRoom) {
		this.initRoom = initRoom;
	}

	public BigDecimal getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(BigDecimal saleNum) {
		this.saleNum = saleNum;
	}

	public BigDecimal getNoSaleNum() {
		return noSaleNum;
	}

	public void setNoSaleNum(BigDecimal noSaleNum) {
		this.noSaleNum = noSaleNum;
	}

	public BigDecimal getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}

	public String getSalesFlag() {
		return salesFlag;
	}

	public void setSalesFlag(String salesFlag) {
		this.salesFlag = salesFlag;
	}

	public String getNosaleReason() {
		return nosaleReason;
	}

	public void setNosaleReason(String nosaleReason) {
		this.nosaleReason = nosaleReason;
	}

	public BigDecimal getNoSaledNum() {
		return noSaledNum;
	}

	public void setNoSaledNum(BigDecimal noSaledNum) {
		this.noSaledNum = noSaledNum;
	}

	public String getPresellStateName() {
		return presellStateName;
	}

	public void setPresellStateName(String presellStateName) {
		this.presellStateName = presellStateName;
	}

	public String getPresellStateCode() {
		return presellStateCode;
	}

	public void setPresellStateCode(String presellStateCode) {
		this.presellStateCode = presellStateCode;
	}
}