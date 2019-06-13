package com.bestvike.website.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "View_Presalecard")
public class ViewPresalecard implements Serializable {

  @Id
  private String presaleNo;
  private String projectId;
  private String address;
  private String bldFunction;
  private String totalArea;
  private String totalNum;
  private String intArea;
  private String intNum;
  private String extArea;
  private String extNum;
  private String saleArea;
  private String saleNum;
  private String houseArea;
  private String houseNum;
  private String shopArea;
  private String shopNum;
  private String busiArea;
  private String busiNum;
  private String garageArea;
  private String garageNum;
  private String storeroomArea;
  private String storeroomNum;
  private String otherArea;
  private String otherNum;
  private String certficateFlag;
  private String authority;
  private String certificateTime;
  private String startDate;
  private String stopDate;
  private String remark;


  public String getPresaleNo() {
    return presaleNo;
  }

  public void setPresaleNo(String presaleNo) {
    this.presaleNo = presaleNo;
  }


  public String getProjectId() {
    return projectId;
  }

  public void setProjectId(String projectId) {
    this.projectId = projectId;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getBldFunction() {
    return bldFunction;
  }

  public void setBldFunction(String bldFunction) {
    this.bldFunction = bldFunction;
  }


  public String getTotalArea() {
    return totalArea;
  }

  public void setTotalArea(String totalArea) {
    this.totalArea = totalArea;
  }


  public String getTotalNum() {
    return totalNum;
  }

  public void setTotalNum(String totalNum) {
    this.totalNum = totalNum;
  }


  public String getIntArea() {
    return intArea;
  }

  public void setIntArea(String intArea) {
    this.intArea = intArea;
  }


  public String getIntNum() {
    return intNum;
  }

  public void setIntNum(String intNum) {
    this.intNum = intNum;
  }


  public String getExtArea() {
    return extArea;
  }

  public void setExtArea(String extArea) {
    this.extArea = extArea;
  }


  public String getExtNum() {
    return extNum;
  }

  public void setExtNum(String extNum) {
    this.extNum = extNum;
  }


  public String getSaleArea() {
    return saleArea;
  }

  public void setSaleArea(String saleArea) {
    this.saleArea = saleArea;
  }


  public String getSaleNum() {
    return saleNum;
  }

  public void setSaleNum(String saleNum) {
    this.saleNum = saleNum;
  }


  public String getHouseArea() {
    return houseArea;
  }

  public void setHouseArea(String houseArea) {
    this.houseArea = houseArea;
  }


  public String getHouseNum() {
    return houseNum;
  }

  public void setHouseNum(String houseNum) {
    this.houseNum = houseNum;
  }


  public String getShopArea() {
    return shopArea;
  }

  public void setShopArea(String shopArea) {
    this.shopArea = shopArea;
  }


  public String getShopNum() {
    return shopNum;
  }

  public void setShopNum(String shopNum) {
    this.shopNum = shopNum;
  }


  public String getBusiArea() {
    return busiArea;
  }

  public void setBusiArea(String busiArea) {
    this.busiArea = busiArea;
  }


  public String getBusiNum() {
    return busiNum;
  }

  public void setBusiNum(String busiNum) {
    this.busiNum = busiNum;
  }


  public String getGarageArea() {
    return garageArea;
  }

  public void setGarageArea(String garageArea) {
    this.garageArea = garageArea;
  }


  public String getGarageNum() {
    return garageNum;
  }

  public void setGarageNum(String garageNum) {
    this.garageNum = garageNum;
  }


  public String getStoreroomArea() {
    return storeroomArea;
  }

  public void setStoreroomArea(String storeroomArea) {
    this.storeroomArea = storeroomArea;
  }


  public String getStoreroomNum() {
    return storeroomNum;
  }

  public void setStoreroomNum(String storeroomNum) {
    this.storeroomNum = storeroomNum;
  }


  public String getOtherArea() {
    return otherArea;
  }

  public void setOtherArea(String otherArea) {
    this.otherArea = otherArea;
  }


  public String getOtherNum() {
    return otherNum;
  }

  public void setOtherNum(String otherNum) {
    this.otherNum = otherNum;
  }


  public String getCertficateFlag() {
    return certficateFlag;
  }

  public void setCertficateFlag(String certficateFlag) {
    this.certficateFlag = certficateFlag;
  }


  public String getAuthority() {
    return authority;
  }

  public void setAuthority(String authority) {
    this.authority = authority;
  }


  public String getCertificateTime() {
    return certificateTime;
  }

  public void setCertificateTime(String certificateTime) {
    this.certificateTime = certificateTime;
  }


  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }


  public String getStopDate() {
    return stopDate;
  }

  public void setStopDate(String stopDate) {
    this.stopDate = stopDate;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
