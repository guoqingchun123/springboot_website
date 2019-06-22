package com.bestvike.website.service.impl;

import com.bestvike.website.dao.ViewHouseInfoDao;
import com.bestvike.website.dao.ViewRegionInfoDao;
import com.bestvike.website.data.ViewHouseInfo;
import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.document.Division;
import com.bestvike.website.document.Region;
import com.bestvike.website.entity.HouseHoldSales;
import com.bestvike.website.entity.PageBean;
import com.bestvike.website.entity.RegionCell;
import com.bestvike.website.entity.SimpleRegion;
import com.bestvike.website.service.ProjectService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ViewRegionInfoDao viewRegionInfoDao;
	@Autowired
	private ViewHouseInfoDao viewHouseInfoDao;
	@Autowired
	private MongoTemplate mongoTemplate;

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
		List<HouseHoldSales> listHouseHold = viewRegionInfoDao.selectRegionHouseHoldData(regionId);
		viewRegionInfo.setSalesData(salesData);
		viewRegionInfo.setListHouseHold(listHouseHold);
		if (StringUtils.isEmpty(viewType) || "salesData".equals(viewType)) {
			// 楼盘图
			List<RegionCell> listCellInfo = viewHouseInfoDao.selectRegionCellList(regionId);
			viewRegionInfo.setListCell(listCellInfo);
			Map<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("projectId", listCellInfo.get(0).getProjectId());
			parameterMap.put("bldNo", listCellInfo.get(0).getBldNo());
			parameterMap.put("cellNo", listCellInfo.get(0).getCellNo());
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
	public List<ViewHouseInfo> selectCellHouse(String projectId, String bldNo, String cellNo) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("projectId", projectId);
		parameterMap.put("bldNo", bldNo);
		parameterMap.put("cellNo", cellNo);
		return viewHouseInfoDao.selectHouseInfoList(parameterMap);
	}

	public Map<String, Object> pageRegions(String keywords, int pageNo, int pageSize) {
		PageInfo<SimpleRegion> simpleRegionPage = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(new ISelect() {
			@Override
			public void doSelect() {
				Map<String, Object> paramterMap = new HashMap<>();
				if (!StringUtils.isEmpty(keywords)) {
					paramterMap.put("keywords", "%" + keywords + "%");
				}
				viewRegionInfoDao.selectRegionByKeywords(paramterMap);
			}
		});
		Map<String, Object> result = new HashMap<>();
		result.put("list", simpleRegionPage.getList());
		PageBean pageBean = new PageBean();
		pageBean.setPageNo(simpleRegionPage.getPageNum());
		pageBean.setPageSize(simpleRegionPage.getPageSize());
		pageBean.setTotalPage(simpleRegionPage.getPages());
		pageBean.setTotalSize(simpleRegionPage.getSize());
		result.put("page", pageBean);
		return result;
	}

	public List<Division> queryDivision() {
		Region region = new Region();
		region.setRegionId(UUID.randomUUID().toString());
		region.setRegionName("小眺望小区");
		mongoTemplate.save(region);
		return mongoTemplate.findAll(Division.class);
	}
}