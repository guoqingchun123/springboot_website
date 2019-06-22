package com.bestvike.website.dao;

import com.bestvike.website.data.ViewDivisionInfo;
import com.bestvike.website.data.ViewHouseInfo;
import com.bestvike.website.entity.FloorSummary;
import com.bestvike.website.entity.Region;
import com.bestvike.website.entity.RegionCell;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ViewHouseInfoDao extends Mapper<ViewHouseInfo> {

	public List<ViewDivisionInfo> selectDivisions();

	public List<RegionCell> selectRegionCellList(String regionId);

	public List<ViewHouseInfo> selectHouseInfoList(Map<String, Object> parameterMap);

	public List<Region> selectHouseByParameter(Map<String, Object> parameterMap);

	public List<FloorSummary> selectFloorSummary(Map<String, Object> parameterMap);
}