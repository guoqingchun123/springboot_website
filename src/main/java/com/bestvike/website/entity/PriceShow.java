package com.bestvike.website.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

@Entity
public class PriceShow implements Serializable {

	private static final long serialVersionUID = -4846882098596592867L;

	private String houseShow;
	private List<HousePrice> housePrices;

	public String getHouseShow() {
		return houseShow;
	}

	public void setHouseShow(String houseShow) {
		this.houseShow = houseShow;
	}

	public List<HousePrice> getHousePrices() {
		return housePrices;
	}

	public void setHousePrices(List<HousePrice> housePrices) {
		this.housePrices = housePrices;
	}
}