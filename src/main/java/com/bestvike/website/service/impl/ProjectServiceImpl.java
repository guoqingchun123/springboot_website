package com.bestvike.website.service.impl;

import com.bestvike.website.dao.ViewRegionInfoDao;
import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ViewRegionInfoDao viewRegionInfoDao;


	@Override
	public ViewRegionInfo region(String regionId) {
		return viewRegionInfoDao.selectRegion(regionId);
	}
}
