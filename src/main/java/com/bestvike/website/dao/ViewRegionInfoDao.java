package com.bestvike.website.dao;

import com.bestvike.website.data.ViewRegionInfo;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ViewRegionInfoDao extends Mapper<ViewRegionInfo> {

	public List<ViewRegionInfo> selectAllRegions(Map<String, Object> parameterMap);

	public ViewRegionInfo selectRegion(String regionId);
}