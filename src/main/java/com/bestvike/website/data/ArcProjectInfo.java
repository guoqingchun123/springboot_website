package com.bestvike.website.data;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Arc_ProjectInfo")
public class ArcProjectInfo implements Serializable {

	@Id
	private String projectno;
	private String projectname;
	private String address;
	private String corpno;

	@Transient
	private String corpname;
	@Transient
	private BigDecimal bldnum;
	@Transient
	private BigDecimal coverarea;
	@Transient
	private BigDecimal plantotalarea;
	@Transient
	private BigDecimal plantotalinvest;
	@Transient
	private String opendate;
	@Transient
	private String admitdate;
	@Transient
	private String salephoneno;
	@Transient
	private String saleaddress;

	public String getProjectno() {
		return projectno;
	}

	public void setProjectno(String projectno) {
		this.projectno = projectno;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCorpno() {
		return corpno;
	}

	public void setCorpno(String corpno) {
		this.corpno = corpno;
	}

	public String getCorpname() {
		return corpname;
	}

	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}

	public BigDecimal getBldnum() {
		return bldnum;
	}

	public void setBldnum(BigDecimal bldnum) {
		this.bldnum = bldnum;
	}

	public BigDecimal getCoverarea() {
		return coverarea;
	}

	public void setCoverarea(BigDecimal coverarea) {
		this.coverarea = coverarea;
	}

	public BigDecimal getPlantotalarea() {
		return plantotalarea;
	}

	public void setPlantotalarea(BigDecimal plantotalarea) {
		this.plantotalarea = plantotalarea;
	}

	public BigDecimal getPlantotalinvest() {
		return plantotalinvest;
	}

	public void setPlantotalinvest(BigDecimal plantotalinvest) {
		this.plantotalinvest = plantotalinvest;
	}

	public String getOpendate() {
		return opendate;
	}

	public void setOpendate(String opendate) {
		this.opendate = opendate;
	}

	public String getAdmitdate() {
		return admitdate;
	}

	public void setAdmitdate(String admitdate) {
		this.admitdate = admitdate;
	}

	public String getSalephoneno() {
		return salephoneno;
	}

	public void setSalephoneno(String salephoneno) {
		this.salephoneno = salephoneno;
	}

	public String getSaleaddress() {
		return saleaddress;
	}

	public void setSaleaddress(String saleaddress) {
		this.saleaddress = saleaddress;
	}
}
