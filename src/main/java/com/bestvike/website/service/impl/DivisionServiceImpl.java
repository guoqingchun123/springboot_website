package com.bestvike.website.service.impl;

import com.bestvike.website.dao.ViewHouseinfoDao;
import com.bestvike.website.data.ViewDivisioninfo;
import com.bestvike.website.service.DivisionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DivisionServiceImpl implements DivisionService {

	@Autowired
	private ViewHouseinfoDao viewHouseinfoDao;

	@Override
	public List<ViewDivisioninfo> selectDivisions() {
		return viewHouseinfoDao.selectDivisions();
	}
}
