package com.bestvike.website.dao;

import com.bestvike.website.data.ViewDivisionInfo;
import com.bestvike.website.data.ViewHouseInfo;
import com.bestvike.website.entity.BldSales;
import com.bestvike.website.entity.BldView;
import com.bestvike.website.entity.CellSummary;
import com.bestvike.website.entity.FloorSummary;
import com.bestvike.website.entity.House;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ViewHouseInfoDao extends Mapper<ViewHouseInfo> {

	public List<ViewDivisionInfo> selectDivisions();

//	public List<RegionCell> selectRegionCellList(String regionId);

//	public List<ViewHouseInfo> selectHouseInfoList(Map<String, Object> parameterMap);

	public List<House> selectHouseByParameter(Map<String, Object> parameterMap);

	public List<FloorSummary> selectFloorSummary(Map<String, Object> parameterMap);

	public BldSales selectBldSalesData(Map<String, Object> parameterMap);

	public CellSummary selectCellSummary(Map<String, Object> parameterMap);

	public List<ViewHouseInfo> selectFloorCellHouses(Map<String, Object> parameterMap);

	public BldView selectBldView(Map<String, Object> parameterMap);

	public List<ViewHouseInfo> selectBldHouse(Map<String, Object> parameterMap);
}