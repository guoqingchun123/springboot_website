package com.bestvike.website.dao;

import com.bestvike.website.data.ViewRegionInfo;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ViewRegionInfoDao extends Mapper<ViewRegionInfo> {

	public List<ViewRegionInfo> selectAllRegions(Map<String, Object> parameterMap);

	public List<ViewRegionInfo> selectRegionByKeywords(String keywords);

	public ViewRegionInfo selectRegion(String regionId);

	public Map<String, Object> selectRegionSalesData(String regionId);

	public List<Map<String, Object>> selectRegionHouseHoldData(String regionId);
}