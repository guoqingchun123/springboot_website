package com.bestvike.website.service.impl;

import com.bestvike.website.dao.ViewProjectInfoDao;
import com.bestvike.website.dao.ViewRegionInfoDao;
import com.bestvike.website.data.ViewPresalecard;
import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.entity.SimpleRegion;
import com.bestvike.website.service.LayoutService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LayoutServiceImpl implements LayoutService {

	@Autowired
	private ViewRegionInfoDao viewRegionInfoDao;
	@Autowired
	private ViewProjectInfoDao viewProjectInfoDao;

	@Override
	public List<SimpleRegion> selectAllRegions(String order) {
		Map<String, Object> maps = new HashMap<>();
		maps.put("order", order);
		return viewRegionInfoDao.selectAllRegions(maps);
	}

	@Override
	public List<SimpleRegion> selectRegionByKeywords(String keywords) {
		Map<String, Object> maps = new HashMap<>();
		if (!StringUtils.isEmpty(keywords)) {
			maps.put("keywords", "%" + keywords + "%");
		}
		return viewRegionInfoDao.selectRegionByKeywords(maps);
	}

	@Override
	public List<ViewPresalecard> selectPerBaseInfoByProjectNo(String projectNo) {
		return viewProjectInfoDao.selectPerBaseInfoByProjectNo(projectNo);
	}
}