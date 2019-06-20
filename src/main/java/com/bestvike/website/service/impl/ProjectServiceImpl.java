package com.bestvike.website.service.impl;

import com.bestvike.website.dao.ViewHouseInfoDao;
import com.bestvike.website.dao.ViewRegionInfoDao;
import com.bestvike.website.data.AssRegBld;
import com.bestvike.website.data.ViewHouseInfo;
import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.service.ProjectService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ViewRegionInfoDao viewRegionInfoDao;
	@Autowired
	private ViewHouseInfoDao viewHouseInfoDao;

	@Override
	public ViewRegionInfo region(String regionId) {
		return viewRegionInfoDao.selectRegion(regionId);
	}

	@Override
	public ViewRegionInfo region(String regionId, String viewType) {
		ViewRegionInfo viewRegionInfo = viewRegionInfoDao.selectRegion(regionId);
		Map<String, Object> salesMap = viewRegionInfoDao.selectRegionSalesData(regionId);
		List<Map<String, Object>> salesData = new ArrayList<>();
		for (String key : salesMap.keySet()) {
			Map<String, Object> sales = new HashMap<>();
			if (null != salesMap.get(key) && ((BigDecimal) salesMap.get(key)).compareTo(BigDecimal.ZERO) > 0) {
				sales.put("value", salesMap.get(key));
				sales.put("name", key);
				salesData.add(sales);
			}
		}
		List<Map<String, Object>> listHouseHoldMap = viewRegionInfoDao.selectRegionHouseHoldData(regionId);
		viewRegionInfo.setSalesData(salesData);
		viewRegionInfo.setListHouseHold(listHouseHoldMap);
		if (StringUtils.isEmpty(viewType) || "salesData".equals(viewType)) {
			// 楼盘图
			List<Map<String, Object>> listCellInfo = viewHouseInfoDao.selectRegionCellList(regionId);
			viewRegionInfo.setListCell(listCellInfo);
			Map<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("bldId", (String) listCellInfo.get(0).get("BLD_ID"));
			parameterMap.put("cellNo", (String) listCellInfo.get(0).get("CELL_NO"));
			List<ViewHouseInfo> listHouseInfo = viewHouseInfoDao.selectHouseInfoList(parameterMap);
			viewRegionInfo.setListHouse(listHouseInfo);
		} else if ("houseHold".equals(viewType)) {
			// 户型图

		} else if ("regionImage".equals(viewType)) {

		} else {
			// 项目相册

		}
		return viewRegionInfo;
	}

	@Override
	public List<ViewHouseInfo> selectCellHouse(String bldId, String cellNo) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("bldId", bldId);
		parameterMap.put("cellNo", cellNo);
		return viewHouseInfoDao.selectHouseInfoList(parameterMap);
	}
}