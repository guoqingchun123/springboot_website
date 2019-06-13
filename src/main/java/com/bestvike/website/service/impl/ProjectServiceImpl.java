package com.bestvike.website.service.impl;

import com.bestvike.website.dao.ViewProjectinfoDao;
import com.bestvike.website.data.ViewProjectinfo;
import com.bestvike.website.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ViewProjectinfoDao viewProjectinfoDao;


	@Override
	public ViewProjectinfo project(String projectId) {
		return viewProjectinfoDao.selectByPrimaryKey(projectId);
	}
}
