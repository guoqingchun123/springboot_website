package com.bestvike.website.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "View_CorpInfo")
public class ViewCorpinfo implements Serializable {

  @Id
  private String corpId;
  private String compType;
  private String compState;
  private String compName;
  private String creditCode;
  private String creditStartDate;
  private String creditEndDate;
  private String devisionCode;
  private String busiScope;
  private String registerAddress;
  private String contactAddress;
  private String postalCode;
  private String linkMan;
  private String linkPhone;
  private String faxNo;
  private String email;
  private String website;
  private String qualifyGrade;
  private String qualifyNo;
  private String qualifyStartDate;
  private String qualifyEndDate;
  private String hasTitleNum;
  private String highTitleNum;
  private String headCount;
  private String middleTitleNum;
  private String primaryTitleNum;
  private String netAsset;
  private String totalAsset;
  private String registerAsset;
  private String generalManager;
  private String legalName;
  private String legalPhone;
  private String legalCertType;
  private String legalCertNo;
  private String corpProperty;
  private String createDate;
  private String approveDate;
  private String remark;


  public String getCorpId() {
    return corpId;
  }

  public void setCorpId(String corpId) {
    this.corpId = corpId;
  }


  public String getCompType() {
    return compType;
  }

  public void setCompType(String compType) {
    this.compType = compType;
  }


  public String getCompState() {
    return compState;
  }

  public void setCompState(String compState) {
    this.compState = compState;
  }


  public String getCompName() {
    return compName;
  }

  public void setCompName(String compName) {
    this.compName = compName;
  }


  public String getCreditCode() {
    return creditCode;
  }

  public void setCreditCode(String creditCode) {
    this.creditCode = creditCode;
  }


  public String getCreditStartDate() {
    return creditStartDate;
  }

  public void setCreditStartDate(String creditStartDate) {
    this.creditStartDate = creditStartDate;
  }


  public String getCreditEndDate() {
    return creditEndDate;
  }

  public void setCreditEndDate(String creditEndDate) {
    this.creditEndDate = creditEndDate;
  }


  public String getDevisionCode() {
    return devisionCode;
  }

  public void setDevisionCode(String devisionCode) {
    this.devisionCode = devisionCode;
  }


  public String getBusiScope() {
    return busiScope;
  }

  public void setBusiScope(String busiScope) {
    this.busiScope = busiScope;
  }


  public String getRegisterAddress() {
    return registerAddress;
  }

  public void setRegisterAddress(String registerAddress) {
    this.registerAddress = registerAddress;
  }


  public String getContactAddress() {
    return contactAddress;
  }

  public void setContactAddress(String contactAddress) {
    this.contactAddress = contactAddress;
  }


  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }


  public String getLinkMan() {
    return linkMan;
  }

  public void setLinkMan(String linkMan) {
    this.linkMan = linkMan;
  }


  public String getLinkPhone() {
    return linkPhone;
  }

  public void setLinkPhone(String linkPhone) {
    this.linkPhone = linkPhone;
  }


  public String getFaxNo() {
    return faxNo;
  }

  public void setFaxNo(String faxNo) {
    this.faxNo = faxNo;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }


  public String getQualifyGrade() {
    return qualifyGrade;
  }

  public void setQualifyGrade(String qualifyGrade) {
    this.qualifyGrade = qualifyGrade;
  }


  public String getQualifyNo() {
    return qualifyNo;
  }

  public void setQualifyNo(String qualifyNo) {
    this.qualifyNo = qualifyNo;
  }


  public String getQualifyStartDate() {
    return qualifyStartDate;
  }

  public void setQualifyStartDate(String qualifyStartDate) {
    this.qualifyStartDate = qualifyStartDate;
  }


  public String getQualifyEndDate() {
    return qualifyEndDate;
  }

  public void setQualifyEndDate(String qualifyEndDate) {
    this.qualifyEndDate = qualifyEndDate;
  }


  public String getHasTitleNum() {
    return hasTitleNum;
  }

  public void setHasTitleNum(String hasTitleNum) {
    this.hasTitleNum = hasTitleNum;
  }


  public String getHighTitleNum() {
    return highTitleNum;
  }

  public void setHighTitleNum(String highTitleNum) {
    this.highTitleNum = highTitleNum;
  }


  public String getHeadCount() {
    return headCount;
  }

  public void setHeadCount(String headCount) {
    this.headCount = headCount;
  }


  public String getMiddleTitleNum() {
    return middleTitleNum;
  }

  public void setMiddleTitleNum(String middleTitleNum) {
    this.middleTitleNum = middleTitleNum;
  }


  public String getPrimaryTitleNum() {
    return primaryTitleNum;
  }

  public void setPrimaryTitleNum(String primaryTitleNum) {
    this.primaryTitleNum = primaryTitleNum;
  }


  public String getNetAsset() {
    return netAsset;
  }

  public void setNetAsset(String netAsset) {
    this.netAsset = netAsset;
  }


  public String getTotalAsset() {
    return totalAsset;
  }

  public void setTotalAsset(String totalAsset) {
    this.totalAsset = totalAsset;
  }


  public String getRegisterAsset() {
    return registerAsset;
  }

  public void setRegisterAsset(String registerAsset) {
    this.registerAsset = registerAsset;
  }


  public String getGeneralManager() {
    return generalManager;
  }

  public void setGeneralManager(String generalManager) {
    this.generalManager = generalManager;
  }


  public String getLegalName() {
    return legalName;
  }

  public void setLegalName(String legalName) {
    this.legalName = legalName;
  }


  public String getLegalPhone() {
    return legalPhone;
  }

  public void setLegalPhone(String legalPhone) {
    this.legalPhone = legalPhone;
  }


  public String getLegalCertType() {
    return legalCertType;
  }

  public void setLegalCertType(String legalCertType) {
    this.legalCertType = legalCertType;
  }


  public String getLegalCertNo() {
    return legalCertNo;
  }

  public void setLegalCertNo(String legalCertNo) {
    this.legalCertNo = legalCertNo;
  }


  public String getCorpProperty() {
    return corpProperty;
  }

  public void setCorpProperty(String corpProperty) {
    this.corpProperty = corpProperty;
  }


  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }


  public String getApproveDate() {
    return approveDate;
  }

  public void setApproveDate(String approveDate) {
    this.approveDate = approveDate;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
