package com.bestvike.website.dao;

import com.bestvike.website.data.AssRegBld;
import com.bestvike.website.data.ViewDivisionInfo;
import com.bestvike.website.data.ViewHouseInfo;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ViewHouseInfoDao extends Mapper<ViewHouseInfo> {

	public List<ViewDivisionInfo> selectDivisions();

	public List<AssRegBld> selectRegionBldList(String regionId);

	public List<Map<String, Object>> selectRegionCellList(String regionId);

	public List<ViewHouseInfo> selectHouseInfoList(Map<String, Object> parameterMap);
}