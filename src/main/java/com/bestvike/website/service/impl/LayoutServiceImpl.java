package com.bestvike.website.service.impl;

import com.bestvike.website.dao.ArcCorpInfoDao;
import com.bestvike.website.dao.ArcProjectCoordinateDao;
import com.bestvike.website.data.ArcCorpInfo;
import com.bestvike.website.data.ArcProjectCoordinate;
import com.bestvike.website.data.ArcProjectInfo;
import com.bestvike.website.data.PerBaseInfo;
import com.bestvike.website.service.LayoutService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LayoutServiceImpl implements LayoutService {

	@Autowired
	private ArcProjectCoordinateDao arcProjectCoordinateDao;
	@Autowired
	private ArcCorpInfoDao arcCorpInfoDao;

	@Override
	public List<ArcProjectCoordinate> selectAllProject(String order) {
		return arcProjectCoordinateDao.selectAllProject(order);
	}

	@Override
	public List<ArcProjectCoordinate> selectProjectByKeywords(String keywords) {
		return arcProjectCoordinateDao.selectProjectByKeywords(keywords);
	}

	@Override
	public ArcProjectInfo selectProject(String projectNo) {
		return arcProjectCoordinateDao.selectProjectByProjectNo(projectNo);
	}

	@Override
	public List<PerBaseInfo> selectPerBaseInfoByProjectNo(String projectNo) {
		return arcProjectCoordinateDao.selectPerBaseInfoByProjectNo(projectNo);
	}

	@Override
	public ArcCorpInfo selectCorpInfo(String corpNo) {
		return arcCorpInfoDao.selectByPrimaryKey(corpNo);
	}
}
