package com.bestvike.website.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_View_HouseInfo")
public class VViewHouseinfo implements Serializable {

  @Id
  private String houseId;
  private String bldId;
  private String houseUse;
  private String houseProp;
  private String houseType;
  private String cellNo;
  private String cellName;
  private String floorNo;
  private String floorName;
  private String showName;
  private String constructArea;
  private String realConstructArea;
  private String ownArea;
  private String perConstructArea;
  private String realOwnArea;
  private String shareArea;
  private String realShareArea;
  private String balconyArea;
  private String realBalconyArea;
  private String otherArea;
  private String houseStructure;
  private String houseHold;
  private String balconyNum;
  private String roomDirection;
  private String skipNum;
  private String mergeNum;
  private String presellPrice;
  private String realPrice;
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
  private String orderNum;
  private String remark;


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


  public String getConstructArea() {
    return constructArea;
  }

  public void setConstructArea(String constructArea) {
    this.constructArea = constructArea;
  }


  public String getRealConstructArea() {
    return realConstructArea;
  }

  public void setRealConstructArea(String realConstructArea) {
    this.realConstructArea = realConstructArea;
  }


  public String getOwnArea() {
    return ownArea;
  }

  public void setOwnArea(String ownArea) {
    this.ownArea = ownArea;
  }


  public String getPerConstructArea() {
    return perConstructArea;
  }

  public void setPerConstructArea(String perConstructArea) {
    this.perConstructArea = perConstructArea;
  }


  public String getRealOwnArea() {
    return realOwnArea;
  }

  public void setRealOwnArea(String realOwnArea) {
    this.realOwnArea = realOwnArea;
  }


  public String getShareArea() {
    return shareArea;
  }

  public void setShareArea(String shareArea) {
    this.shareArea = shareArea;
  }


  public String getRealShareArea() {
    return realShareArea;
  }

  public void setRealShareArea(String realShareArea) {
    this.realShareArea = realShareArea;
  }


  public String getBalconyArea() {
    return balconyArea;
  }

  public void setBalconyArea(String balconyArea) {
    this.balconyArea = balconyArea;
  }


  public String getRealBalconyArea() {
    return realBalconyArea;
  }

  public void setRealBalconyArea(String realBalconyArea) {
    this.realBalconyArea = realBalconyArea;
  }


  public String getOtherArea() {
    return otherArea;
  }

  public void setOtherArea(String otherArea) {
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


  public String getBalconyNum() {
    return balconyNum;
  }

  public void setBalconyNum(String balconyNum) {
    this.balconyNum = balconyNum;
  }


  public String getRoomDirection() {
    return roomDirection;
  }

  public void setRoomDirection(String roomDirection) {
    this.roomDirection = roomDirection;
  }


  public String getSkipNum() {
    return skipNum;
  }

  public void setSkipNum(String skipNum) {
    this.skipNum = skipNum;
  }


  public String getMergeNum() {
    return mergeNum;
  }

  public void setMergeNum(String mergeNum) {
    this.mergeNum = mergeNum;
  }


  public String getPresellPrice() {
    return presellPrice;
  }

  public void setPresellPrice(String presellPrice) {
    this.presellPrice = presellPrice;
  }


  public String getRealPrice() {
    return realPrice;
  }

  public void setRealPrice(String realPrice) {
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


  public String getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
