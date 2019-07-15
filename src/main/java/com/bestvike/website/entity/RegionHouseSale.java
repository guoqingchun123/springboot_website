package com.bestvike.website.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class RegionHouseSale implements Serializable {

	private static final long serialVersionUID = -8881290539595052784L;

	private String houseShow;
	private String houseUse;
	private String houseHold;
	private String nosaleReason;
	private BigDecimal totalNum;
	private BigDecimal sellNum;
	private BigDecimal signedNum;
	private BigDecimal signingNum;
	private BigDecimal nosaledNum;
	private BigDecimal closeNum;
	private BigDecimal mortgageNum;
	private BigDecimal nosaleNum;
	private BigDecimal frozen;

	public String getHouseShow() {
		return houseShow;
	}

	public void setHouseShow(String houseShow) {
		this.houseShow = houseShow;
	}

	public String getHouseUse() {
		return houseUse;
	}

	public void setHouseUse(String houseUse) {
		this.houseUse = houseUse;
	}

	public String getHouseHold() {
		return houseHold;
	}

	public void setHouseHold(String houseHold) {
		this.houseHold = houseHold;
	}

	public String getNosaleReason() {
		return nosaleReason;
	}

	public void setNosaleReason(String nosaleReason) {
		this.nosaleReason = nosaleReason;
	}

	public BigDecimal getTotalNum() {
		return null != totalNum ? totalNum : BigDecimal.ZERO;
	}

	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}

	public BigDecimal getSellNum() {
		return null != sellNum ? sellNum : BigDecimal.ZERO;
	}

	public void setSellNum(BigDecimal sellNum) {
		this.sellNum = sellNum;
	}

	public BigDecimal getSignedNum() {
		return null != signedNum ? signedNum : BigDecimal.ZERO;
	}

	public void setSignedNum(BigDecimal signedNum) {
		this.signedNum = signedNum;
	}

	public BigDecimal getSigningNum() {
		return null != signingNum ? signingNum : BigDecimal.ZERO;
	}

	public void setSigningNum(BigDecimal signingNum) {
		this.signingNum = signingNum;
	}

	public BigDecimal getNosaledNum() {
		return null != nosaledNum ? nosaledNum : BigDecimal.ZERO;

	}

	public void setNosaledNum(BigDecimal nosaledNum) {
		this.nosaledNum = nosaledNum;
	}


	public BigDecimal getCloseNum() {
		return null != closeNum ? closeNum : BigDecimal.ZERO;
	}

	public void setCloseNum(BigDecimal closeNum) {
		this.closeNum = closeNum;
	}

	public BigDecimal getNosaleNum() {
		return null != nosaledNum ? nosaleNum : BigDecimal.ZERO;
	}

	public void setNosaleNum(BigDecimal nosaleNum) {
		this.nosaleNum = nosaleNum;
	}

	public BigDecimal getFrozen() {
		return null != frozen ? frozen : BigDecimal.ZERO;
	}

	public void setFrozen(BigDecimal frozen) {
		this.frozen = frozen;
	}

	public BigDecimal getMortgageNum() {
		return null != mortgageNum ? mortgageNum : BigDecimal.ZERO;
	}

	public void setMortgageNum(BigDecimal mortgageNum) {
		this.mortgageNum = mortgageNum;
	}
}
