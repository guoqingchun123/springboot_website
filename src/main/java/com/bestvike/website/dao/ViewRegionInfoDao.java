package com.bestvike.website.dao;

import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.entity.HouseHoldSales;
import com.bestvike.website.entity.SimpleRegion;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ViewRegionInfoDao extends Mapper<ViewRegionInfo> {

	public List<SimpleRegion> selectAllRegions(Map<String, Object> parameterMap);

	public List<SimpleRegion> selectRegionByKeywords(Map<String, Object> parameterMap);

	public ViewRegionInfo selectRegion(String regionId);

	public Map<String, Object> selectRegionSalesData(String regionId);

	public List<HouseHoldSales> selectRegionHouseHoldData(String regionId);
}