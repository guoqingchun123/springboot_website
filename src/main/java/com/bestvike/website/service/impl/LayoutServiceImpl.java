package com.bestvike.website.service.impl;

import com.bestvike.website.dao.ViewCorpInfoDao;
import com.bestvike.website.dao.ViewProjectInfoDao;
import com.bestvike.website.dao.ViewRegionInfoDao;
import com.bestvike.website.data.ViewCorpInfo;
import com.bestvike.website.data.ViewPresalecard;
import com.bestvike.website.data.ViewProjectInfo;
import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.service.LayoutService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LayoutServiceImpl implements LayoutService {

	@Autowired
	private ViewRegionInfoDao viewRegionInfoDao;
	@Autowired
	private ViewProjectInfoDao viewProjectInfoDao;
	@Autowired
	private ViewCorpInfoDao viewCorpInfoDao;

	@Override
	public List<ViewRegionInfo> selectAllRegions(String order) {
		Map<String, Object> maps = new HashMap<>();
		maps.put("order", order);
		return viewRegionInfoDao.selectAllRegions(maps);
	}

	@Override
	public List<ViewProjectInfo> selectProjectByKeywords(String keywords) {
		return viewProjectInfoDao.selectProjectByKeywords(keywords);
	}

	@Override
	public ViewProjectInfo selectProject(String projectNo) {
		return viewProjectInfoDao.selectProjectByProjectNo(projectNo);
	}

	@Override
	public List<ViewPresalecard> selectPerBaseInfoByProjectNo(String projectNo) {
		return viewProjectInfoDao.selectPerBaseInfoByProjectNo(projectNo);
	}

	@Override
	public ViewCorpInfo selectCorpInfo(String corpNo) {
		return viewCorpInfoDao.selectByPrimaryKey(corpNo);
	}
}
