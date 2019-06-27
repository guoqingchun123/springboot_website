package com.bestvike.website.dao;

import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.entity.BldCells;
import com.bestvike.website.entity.Cell;
import com.bestvike.website.entity.HouseHoldSales;
import com.bestvike.website.entity.MonthSales;
import com.bestvike.website.entity.Region;
import com.bestvike.website.entity.RegionBlds;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ViewRegionInfoDao extends Mapper<ViewRegionInfo> {

	public List<Region> selectAllRegions(Map<String, Object> parameterMap);

	public List<Region> selectRegionByParameter(Map<String, Object> parameterMap);

	public ViewRegionInfo selectRegion(String regionId);

	public Map<String, Object> selectRegionSalesData(String regionId);

	public List<HouseHoldSales> selectRegionHouseHoldData(Map<String, Object> parameterMap);

	public List<BldCells> selectBlds(String regionId);

	public List<Cell> selectBldCells(BldCells bldCells);

	public List<RegionBlds> selectRegionBlds(String regionId);

	public List<MonthSales> selectRegionMonthSales(Map<String, Object> parameterMap);

	public ViewRegionInfo selectRegionByProjectId(String projectId);
}