package com.bestvike.website.data;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Per_BaseInfo")
public class PerBaseInfo implements Serializable {

	@Id
	private String sysguid;
	private String presellno;
	private String address;
	private String bldfunction;
	private BigDecimal totalnum;
	private BigDecimal totalarea;
	private BigDecimal salenum;
	private BigDecimal salearea;
	private String startdate;
	private String stopdate;

	public String getSysguid() {
		return sysguid;
	}

	public void setSysguid(String sysguid) {
		this.sysguid = sysguid;
	}

	public String getPresellno() {
		return presellno;
	}

	public void setPresellno(String presellno) {
		this.presellno = presellno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBldfunction() {
		return bldfunction;
	}

	public void setBldfunction(String bldfunction) {
		this.bldfunction = bldfunction;
	}

	public BigDecimal getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(BigDecimal totalnum) {
		this.totalnum = totalnum;
	}

	public BigDecimal getTotalarea() {
		return totalarea;
	}

	public void setTotalarea(BigDecimal totalarea) {
		this.totalarea = totalarea;
	}

	public BigDecimal getSalenum() {
		return salenum;
	}

	public void setSalenum(BigDecimal salenum) {
		this.salenum = salenum;
	}

	public BigDecimal getSalearea() {
		return salearea;
	}

	public void setSalearea(BigDecimal salearea) {
		this.salearea = salearea;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getStopdate() {
		return stopdate;
	}

	public void setStopdate(String stopdate) {
		this.stopdate = stopdate;
	}
}
