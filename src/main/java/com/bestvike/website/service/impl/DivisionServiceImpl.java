package com.bestvike.website.service.impl;

import com.bestvike.website.dao.ViewHouseInfoDao;
import com.bestvike.website.data.ViewDivisionInfo;
import com.bestvike.website.service.DivisionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivisionServiceImpl implements DivisionService {

	@Autowired
	private ViewHouseInfoDao viewHouseInfoDao;

	@Override
	public List<ViewDivisionInfo> selectDivisions() {
		return viewHouseInfoDao.selectDivisions();
	}
}
