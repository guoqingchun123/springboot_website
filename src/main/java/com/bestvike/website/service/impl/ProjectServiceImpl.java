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
import com.bestvike.website.entity.BldView;
import com.bestvike.website.entity.Cell;
import com.bestvike.website.entity.CellSummary;
import com.bestvike.website.entity.DocFile;
import com.bestvike.website.entity.DocFiles;
import com.bestvike.website.entity.Floor;
import com.bestvike.website.entity.FloorSummary;
import com.bestvike.website.entity.House;
import com.bestvike.website.entity.HouseHoldSales;
import com.bestvike.website.entity.MonthData;
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

	/**
	 * 刷新楼栋数据
	 *
	 * @param regionId
	 * @param projectId
	 * @param bldNo
	 * @return
	 */
	@Override
	public ViewRegionInfo region(String regionId, String projectId, String bldNo) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("projectId", projectId);
		parameters.put("bldNo", bldNo);
		BldView bldView = viewHouseInfoDao.selectBldView(parameters);
		List<Cell> listCell = viewRegionInfoDao.selectBldCells(parameters);
		bldView.setCells(listCell);
		if (!StringUtils.isEmpty(bldView.getViewPath())) {
			// 楼栋下有单元标记，以单元显示房屋，并提供单元选择刷新功能
			Cell cell = listCell.get(0);
			ViewRegionInfo viewRegionInfo = region(regionId, projectId, bldNo, cell.getCellNo());
			viewRegionInfo.setCellNo(cell.getCellNo());
			return viewRegionInfo;
		}
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

		DocFiles docFiles = mongoTemplate.findOne(Query.query(Criteria.
			where("keyId").is(regionId).
			and("fileType").is("regionImage").and("docType").is("aerialView")), DocFiles.class);
		if (docFiles != null) {
			List<String> regionLogos = new ArrayList<>();
			for (DocFile docFile : docFiles.getImageList()) {
				regionLogos.add(docFile.getViewUrl());
			}
			viewRegionInfo.setRegionLogos(regionLogos);
		}

		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("regionId", regionId);
		List<HouseHoldSales> listHouseHold = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		viewRegionInfo.setSalesData(salesData);
		viewRegionInfo.setListHouseHold(listHouseHold);
		// 查询小区楼栋列表
		List<RegionBlds> listRegionBlds = viewRegionInfoDao.selectRegionBlds(regionId);
		viewRegionInfo.setListRegionBlds(listRegionBlds);
		// 增加按楼按单元显示房屋
		if (listRegionBlds != null && listRegionBlds.size() > 0) {
			parameterMap.put("projectId", projectId);
			parameterMap.put("bldNo", bldNo);
			// 查询楼栋楼层列表
			List<Floor> listFloor = viewRegionInfoDao.selectBldFloors(parameterMap);
			for (Floor floor : listFloor) {
				// 查询楼层单元信息
				parameterMap.put("floorNo", floor.getFloorNo());
				List<Cell> listFloorCell = viewRegionInfoDao.selectFloorCells(parameterMap);
				if (BigDecimal.valueOf(8).compareTo(bldView.getCellFloorNum()) < 0) {
					// 每层每单元户数大于8， 不按单元显示，房屋都放到单元上
					Cell cell = listFloorCell.get(0);
					List<ViewHouseInfo> listHouse = viewHouseInfoDao.selectBldHouse(parameterMap);
					cell.setHouses(listHouse);
					bldView.setShowCell(false);
				} else {
					for (Cell cell : listFloorCell) {
						parameterMap.put("cellNo", cell.getCellNo());
						List<ViewHouseInfo> listHouse = viewHouseInfoDao.selectFloorCellHouses(parameterMap);
						cell.setHouses(listHouse);
						// 防止遍历过程中串单元
						parameterMap.remove("cellNo");
					}
					bldView.setShowCell(true);
				}
				floor.setCells(listFloorCell);
				floor.setShowCell(true);
			}
			bldView.setFloors(listFloor);
			// 查询楼栋销售情况
			BldSales bldSales = viewHouseInfoDao.selectBldSalesData(parameterMap);
			bldView.setBldSales(bldSales);
			viewRegionInfo.setBldView(bldView);
		}
		return viewRegionInfo;
	}

	/**
	 * 刷新单元数据
	 * @param regionId
	 * @param projectId
	 * @param bldNo
	 * @param cellNo
	 * @return
	 */
	@Override
	public ViewRegionInfo region(String regionId, String projectId, String bldNo, String cellNo) {
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

		DocFiles docFiles = mongoTemplate.findOne(Query.query(Criteria.
			where("keyId").is(regionId).
			and("fileType").is("regionImage").and("docType").is("aerialView")), DocFiles.class);
		if (docFiles != null) {
			List<String> regionLogos = new ArrayList<>();
			for (DocFile docFile : docFiles.getImageList()) {
				regionLogos.add(docFile.getViewUrl());
			}
			viewRegionInfo.setRegionLogos(regionLogos);
		}

		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("regionId", regionId);
		List<HouseHoldSales> listHouseHold = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		viewRegionInfo.setSalesData(salesData);
		viewRegionInfo.setListHouseHold(listHouseHold);
		// 查询小区楼栋列表
		List<RegionBlds> listRegionBlds = viewRegionInfoDao.selectRegionBlds(regionId);
		viewRegionInfo.setListRegionBlds(listRegionBlds);
		// 增加按楼按单元显示房屋
		if (listRegionBlds != null && listRegionBlds.size() > 0) {
			parameterMap.put("projectId", projectId);
			parameterMap.put("bldNo", bldNo);
			BldView bldView = viewHouseInfoDao.selectBldView(parameterMap);
			// 查询楼栋单元列表
			List<Cell> listCell = viewRegionInfoDao.selectBldCells(parameterMap);
			bldView.setCells(listCell);
			// 查询楼栋单元楼层列表
			parameterMap.put("cellNo", cellNo);
			List<Floor> listFloor = viewRegionInfoDao.selectBldFloors(parameterMap);
			for (Floor floor : listFloor) {
				// 查询楼层单元信息
				parameterMap.put("floorNo", floor.getFloorNo());
				List<Cell> listFloorCell = viewRegionInfoDao.selectFloorCells(parameterMap);
				// 只有一个单元
				if (listFloorCell != null && listFloorCell.size() > 0) {
					Cell cell = listFloorCell.get(0);
					List<ViewHouseInfo> listHouse = viewHouseInfoDao.selectBldHouse(parameterMap);
					cell.setHouses(listHouse);
				}
				bldView.setShowCell(false);
				floor.setCells(listFloorCell);
				floor.setShowCell(false);
			}
			bldView.setFloors(listFloor);
			// 查询楼栋销售情况
			BldSales bldSales = viewHouseInfoDao.selectBldSalesData(parameterMap);
			bldView.setBldSales(bldSales);
			viewRegionInfo.setBldView(bldView);
		}
		return viewRegionInfo;
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

		DocFiles docFiles = mongoTemplate.findOne(Query.query(Criteria.
			where("keyId").is(regionId).
			and("fileType").is("regionImage").and("docType").is("aerialView")), DocFiles.class);
		if (docFiles != null) {
			List<String> regionLogos = new ArrayList<>();
			for (DocFile docFile : docFiles.getImageList()) {
				regionLogos.add(docFile.getViewUrl());
			}
			viewRegionInfo.setRegionLogos(regionLogos);
		}

		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("regionId", regionId);
		List<HouseHoldSales> listHouseHold = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		viewRegionInfo.setSalesData(salesData);
		viewRegionInfo.setListHouseHold(listHouseHold);
		if (StringUtils.isEmpty(viewType) || "salesData".equals(viewType)) {
			// 查询小区楼栋列表
			List<RegionBlds> listRegionBlds = viewRegionInfoDao.selectRegionBlds(regionId);
			viewRegionInfo.setListRegionBlds(listRegionBlds);
			// 增加按楼按单元显示房屋
			if (listRegionBlds != null && listRegionBlds.size() > 0) {
				String projectId = listRegionBlds.get(0).getProjectId();
				String bldNo = listRegionBlds.get(0).getBldNo();
				parameterMap.put("projectId", projectId);
				parameterMap.put("bldNo", bldNo);
				BldView bldView = viewHouseInfoDao.selectBldView(parameterMap);
				// 查询楼栋单元列表
				List<Cell> listCell = viewRegionInfoDao.selectBldCells(parameterMap);
				bldView.setCells(listCell);
				// 查询楼栋楼层列表
				List<Floor> listFloor = viewRegionInfoDao.selectBldFloors(parameterMap);
				for (Floor floor : listFloor) {
					// 查询楼层单元信息
					parameterMap.put("floorNo", floor.getFloorNo());
					List<Cell> listFloorCell = viewRegionInfoDao.selectFloorCells(parameterMap);
					if (BigDecimal.valueOf(8).compareTo(bldView.getCellFloorNum()) < 0) {
						// 每层每单元户数大于8， 不按单元显示，房屋都放到单元上
						Cell cell = listFloorCell.get(0);
						List<ViewHouseInfo> listHouse = viewHouseInfoDao.selectBldHouse(parameterMap);
						cell.setHouses(listHouse);
						bldView.setShowCell(false);
					} else {
						for (Cell cell : listFloorCell) {
							parameterMap.put("cellNo", cell.getCellNo());
							List<ViewHouseInfo> listHouse = viewHouseInfoDao.selectFloorCellHouses(parameterMap);
							cell.setHouses(listHouse);
							parameterMap.remove("cellNo");
						}
						bldView.setShowCell(true);
					}
					floor.setCells(listFloorCell);
					floor.setShowCell(true);
				}
				bldView.setFloors(listFloor);
				// 查询楼栋销售情况
				BldSales bldSales = viewHouseInfoDao.selectBldSalesData(parameterMap);
				bldView.setBldSales(bldSales);
				viewRegionInfo.setBldView(bldView);
			}
		} else {
			List<DocFiles> listDocFiles = queryRegionDocs(regionId, viewType);
			viewRegionInfo.setListDocFiles(listDocFiles);
			// 统计小区月销售量
			parameterMap.clear();
			parameterMap.put("regionId", regionId);
			parameterMap.put("preSaleDate", viewRegionInfo.getPreSaleDate());
			List<MonthData> listRegionSales = viewRegionInfoDao.selectRegionMonthSale(parameterMap);
			viewRegionInfo.setListRegionSales(listRegionSales);
		}
		return viewRegionInfo;
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
			division.setX(viewDivisionInfo.getX());
			division.setY(viewDivisionInfo.getY());
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
		// 查询各种房屋类型的销售均价
//		List<HousePrice> housePrices = viewRegionInfoDao.selectHousePrices(regionId);
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
			Map<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("projectId", bldCells.getProjectId());
			parameterMap.put("bldNo", bldCells.getBldNo());
			List<Cell> listCell = viewRegionInfoDao.selectBldCells(parameterMap);
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
	public Map<String, Object> cellFloorSummary(String projectId, String bldNo, String cellNo) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("projectId", projectId);
		parameterMap.put("bldNo", bldNo);
		parameterMap.put("cellNo", cellNo);
		List<FloorSummary> listFloorSummary = viewHouseInfoDao.selectFloorSummary(parameterMap);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("listFloorSummary", listFloorSummary);
		for (FloorSummary floorSummary : listFloorSummary) {
			parameterMap.put("floorNo", floorSummary.getFloorNo());
			List<House> listHouse = viewHouseInfoDao.selectHouseByParameter(parameterMap);
			floorSummary.setRooms(listHouse);
		}
		CellSummary cellSummary = viewHouseInfoDao.selectCellSummary(parameterMap);
		resultMap.put("cellSummary", cellSummary);
		cellSummary.setSellColor("dd6a62");
		cellSummary.setNosaledColor("959595");
		cellSummary.setNosaleColor("00dd02");
		ViewRegionInfo viewRegionInfo = viewRegionInfoDao.selectRegionByProjectId(projectId);
		List<String> imageList = new ArrayList<>();
		imageList.add(viewRegionInfo.getViewPath());
		resultMap.put("imageList", imageList);
		return resultMap;
	}
}