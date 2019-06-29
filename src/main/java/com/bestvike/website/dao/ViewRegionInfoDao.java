package com.bestvike.website.dao;

import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.entity.BldCells;
import com.bestvike.website.entity.Cell;
import com.bestvike.website.entity.DivisionTrade;
import com.bestvike.website.entity.Floor;
import com.bestvike.website.entity.HouseHoldSales;
import com.bestvike.website.entity.MonthData;
import com.bestvike.website.entity.Region;
import com.bestvike.website.entity.RegionBlds;
import com.bestvike.website.entity.Trade;
import java.math.BigDecimal;
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

	public List<Cell> selectBldCells(Map<String, Object> parameterMap);

	public List<Floor> selectBldFloors(Map<String, Object> parameterMap);

	public List<Cell> selectFloorCells(Map<String, Object> parameterMap);

	public List<RegionBlds> selectRegionBlds(String regionId);

	public List<MonthData> selectRegionMonthSale(Map<String, Object> parameterMap);

	public ViewRegionInfo selectRegionByProjectId(String projectId);

	public BigDecimal selectRegionNum();

	public BigDecimal selectStockArea();

	public BigDecimal selectYesterdayTradingArea();

	public BigDecimal selectTodayTradingArea();

	public List<MonthData> selectMonthData();

	public List<Trade> selectTodayTrade();

	public List<DivisionTrade> selectDivisionTrade();
}