package com.bestvike.website.service.impl;

import com.bestvike.website.dao.ViewDivisionInfoDao;
import com.bestvike.website.dao.ViewHouseInfoDao;
import com.bestvike.website.dao.ViewRegionInfoDao;
import com.bestvike.website.data.ViewDivisionInfo;
import com.bestvike.website.data.ViewHouseInfo;
import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.document.Division;
import com.bestvike.website.entity.BldCells;
import com.bestvike.website.entity.BldSales;
import com.bestvike.website.entity.Cell;
import com.bestvike.website.entity.DocFile;
import com.bestvike.website.entity.DocFiles;
import com.bestvike.website.entity.FloorSummary;
import com.bestvike.website.entity.House;
import com.bestvike.website.entity.HouseHoldSales;
import com.bestvike.website.entity.MonthSales;
import com.bestvike.website.entity.PageBean;
import com.bestvike.website.entity.Region;
import com.bestvike.website.entity.RegionBlds;
import com.bestvike.website.service.ProjectService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ViewDivisionInfoDao viewDivisionInfoDao;
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
		if (null != salesMap) {
			for (String key : salesMap.keySet()) {
				Map<String, Object> sales = new HashMap<>();
				if (null != salesMap.get(key) && ((BigDecimal) salesMap.get(key)).compareTo(BigDecimal.ZERO) > 0) {
					sales.put("value", salesMap.get(key));
					sales.put("name", key);
					salesData.add(sales);
				}
			}
		}
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("regionId", regionId);
		List<HouseHoldSales> listHouseHold = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		viewRegionInfo.setSalesData(salesData);
		viewRegionInfo.setListHouseHold(listHouseHold);
		if (StringUtils.isEmpty(viewType) || "salesData".equals(viewType)) {
			// 楼盘图
//			List<RegionCell> listCellInfo = viewHouseInfoDao.selectRegionCellList(regionId);
//			viewRegionInfo.setListCell(listCellInfo);
			// 查询小区楼栋列表
			List<RegionBlds> listRegionBlds = viewRegionInfoDao.selectRegionBlds(regionId);
			viewRegionInfo.setListRegionBlds(listRegionBlds);
			List<BldCells> listBldCells = queryRegionBldCells(regionId);
			viewRegionInfo.setListBldCells(listBldCells);
			if (listBldCells != null && listBldCells.size() > 0
				&& listBldCells.get(0).getListCell() != null && listBldCells.get(0).getListCell().size() > 0) {
				parameterMap.put("projectId", listBldCells.get(0).getProjectId());
				parameterMap.put("bldNo", listBldCells.get(0).getBldNo());
//				parameterMap.put("cellNo", listBldCells.get(0).getListCell().get(0).getCellNo());
				List<ViewHouseInfo> listHouseInfo = viewHouseInfoDao.selectHouseInfoList(parameterMap);
				viewRegionInfo.setListHouse(listHouseInfo);
				BldSales bldSales = viewHouseInfoDao.selectBldSalesData(parameterMap);
				viewRegionInfo.setBldSales(bldSales);
			}
		} else if ("houseHold".equalsIgnoreCase(viewType)) {
			List<DocFiles> listDocFiles = queryRegionDocs(regionId, viewType);
			viewRegionInfo.setListDocFiles(listDocFiles);
			// 统计小区月销售量
			parameterMap.clear();
			parameterMap.put("regionId", regionId);
			parameterMap.put("preSaleDate", viewRegionInfo.getPreSaleDate());
			List<MonthSales> listRegionSales = viewRegionInfoDao.selectRegionMonthSales(parameterMap);
			viewRegionInfo.setListRegionSales(listRegionSales);
		} else {
			List<DocFiles> listDocFiles = queryRegionDocs(regionId, viewType);
			viewRegionInfo.setListDocFiles(listDocFiles);
		}
		return viewRegionInfo;
	}

	@Override
	public Map<String, Object> selectBldHouses(String projectId, String bldNo) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("projectId", projectId);
		parameterMap.put("bldNo", bldNo);
		List<ViewHouseInfo> listViewHouseInfo = viewHouseInfoDao.selectHouseInfoList(parameterMap);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("listHouse", listViewHouseInfo);
		BldSales bldSales = viewHouseInfoDao.selectBldSalesData(parameterMap);
		resultMap.put("bldSales", bldSales);
		return resultMap;
	}

	public Map<String, Object> pageRegions(String keywords, int pageNo, int pageSize, String divisionCode, String price, String houseHold, String sort) {
		PageInfo<Region> simpleRegionPage = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(new ISelect() {
			@Override
			public void doSelect() {
				Map<String, Object> paramterMap = new HashMap<>();
				if (!StringUtils.isEmpty(keywords)) {
					paramterMap.put("keywords", "%" + keywords + "%");
				}
				if (!StringUtils.isEmpty(divisionCode)) {
					paramterMap.put("divisionCode", divisionCode);
				}
				if (!StringUtils.isEmpty(price)) {
					paramterMap.put("price", price);
				}
				if (!StringUtils.isEmpty(houseHold)) {
					paramterMap.put("houseHold", "%" + houseHold + "%");
				}
				if (!StringUtils.isEmpty(sort)) {
					String querySort = sort.replaceAll("avgPrice", "avg_price").replaceAll("preSaleDate", "pre_sale_date");
					paramterMap.put("sort", querySort);
				}
				viewRegionInfoDao.selectRegionByParameter(paramterMap);
			}
		});
		Map<String, Object> result = new HashMap<>();
		result.put("list", simpleRegionPage.getList());
		PageBean pageBean = new PageBean();
		pageBean.setPageNo(simpleRegionPage.getPageNum());
		pageBean.setPageSize(simpleRegionPage.getPageSize());
		pageBean.setTotalPage(simpleRegionPage.getPages());
		pageBean.setTotalSize(simpleRegionPage.getTotal());
		result.put("page", pageBean);
		return result;
	}

	public List<Division> queryDivision() {
		List<Division> listDivision = new ArrayList<>();
		List<ViewDivisionInfo> listViewDivision = viewDivisionInfoDao.selectAll();
		for (ViewDivisionInfo viewDivisionInfo : listViewDivision) {
			Division division = new Division();
			division.setCode(viewDivisionInfo.getDivisionCode());
			division.setName(viewDivisionInfo.getDivisionName());
			listDivision.add(division);
		}
		return listDivision;
	}

	/**
	 * 查询小区销售统计信息及logos
	 *
	 * @param regionId
	 * @return
	 */
	public Map<String, Object> queryRegionSales(String regionId) {
		Map<String, Object> result = new HashMap<>();
		DocFiles docFiles = mongoTemplate.findOne(Query.query(Criteria.
			where("keyId").is(regionId).
			and("fileType").is("regionImage").and("docType").is("aerialView")), DocFiles.class);
		if (docFiles != null) {
			List<String> regionLogos = new ArrayList<>();
			for (DocFile docFile : docFiles.getImageList()) {
				regionLogos.add(docFile.getViewUrl());
			}
			result.put("logos", regionLogos);
		}
		// 查询住宅销售情况
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("regionId", regionId);
		parameterMap.put("houseUse", "01");
		List<HouseHoldSales> residence = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		result.put("residence", residence);
		// 查询配套销售情况
		parameterMap.put("houseUse", "99");
		List<HouseHoldSales> mating = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		result.put("mating", mating);
		return result;
	}

	/**
	 * @param regionId
	 * @return
	 */
	public List<DocFiles> queryRegionDocs(String regionId, String type) {
		if (!StringUtils.isEmpty(type)) {
			switch (type) {
				case "houseHold":
					// 户型图
					List<DocFiles> docFiles = mongoTemplate.find(Query.query(Criteria.
						where("keyId").is(regionId).
						and("fileType").is("houseHold")), DocFiles.class);
					return docFiles;
				case "regionImage":
					// 鸟瞰图
					// 户型图
					docFiles = mongoTemplate.find(Query.query(Criteria.
						where("keyId").is(regionId).
						and("fileType").is("regionImage")), DocFiles.class);
					return docFiles;
			}
		}
		return null;
	}

	@Override
	public List<BldCells> queryRegionBldCells(String regionId) {
		List<BldCells> listBldCells = viewRegionInfoDao.selectBlds(regionId);
		for (BldCells bldCells : listBldCells) {
			List<Cell> listCell = viewRegionInfoDao.selectBldCells(bldCells);
			bldCells.setListCell(listCell);
		}
		return listBldCells;
	}

	@Override
	public Map<String, Object> pageHouses(String projectId, String bldNo, String cellNo, int pageNo, int pageSize) {
		PageInfo<House> simpleHousePage = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(new ISelect() {
			@Override
			public void doSelect() {
				Map<String, Object> paramterMap = new HashMap<>();
				if (!StringUtils.isEmpty(projectId)) {
					paramterMap.put("projectId", projectId);
				}
				if (!StringUtils.isEmpty(bldNo)) {
					paramterMap.put("bldNo", bldNo);
				}
				if (!StringUtils.isEmpty(cellNo)) {
					paramterMap.put("cellNo", cellNo);
				}
				viewHouseInfoDao.selectHouseByParameter(paramterMap);
			}
		});
		Map<String, Object> result = new HashMap<>();
		result.put("list", simpleHousePage.getList());
		PageBean pageBean = new PageBean();
		pageBean.setPageNo(simpleHousePage.getPageNum());
		pageBean.setPageSize(simpleHousePage.getPageSize());
		pageBean.setTotalPage(simpleHousePage.getPages());
		pageBean.setTotalSize(simpleHousePage.getTotal());
		result.put("page", pageBean);
		return result;
	}

	@Override
	public List<FloorSummary> floorSummary(String projectId, String bldNo, String cellNo) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("projectId", projectId);
		parameterMap.put("bldNo", bldNo);
		parameterMap.put("cellNo", cellNo);
		return viewHouseInfoDao.selectFloorSummary(parameterMap);
	}
}