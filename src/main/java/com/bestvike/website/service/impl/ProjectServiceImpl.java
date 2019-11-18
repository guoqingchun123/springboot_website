package com.bestvike.website.service.impl;

import com.bestvike.commons.exception.ServiceException;
import com.bestvike.website.dao.AppVersionDao;
import com.bestvike.website.dao.ViewBldFloorDao;
import com.bestvike.website.dao.ViewDivisionInfoDao;
import com.bestvike.website.dao.ViewFloorCellDao;
import com.bestvike.website.dao.ViewHouseInfoDao;
import com.bestvike.website.dao.ViewRegionInfoDao;
import com.bestvike.website.data.AppVersion;
import com.bestvike.website.data.ViewBldFloor;
import com.bestvike.website.data.ViewDivisionInfo;
import com.bestvike.website.data.ViewFloorCell;
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
import com.bestvike.website.entity.HouseHoldSale;
import com.bestvike.website.entity.HousePrice;
import com.bestvike.website.entity.MonthData;
import com.bestvike.website.entity.PageBean;
import com.bestvike.website.entity.PriceShow;
import com.bestvike.website.entity.Region;
import com.bestvike.website.entity.RegionBlds;
import com.bestvike.website.entity.RegionHouseSale;
import com.bestvike.website.service.ProjectService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class ProjectServiceImpl implements ProjectService {
	protected Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private ViewDivisionInfoDao viewDivisionInfoDao;
	@Autowired
	private ViewRegionInfoDao viewRegionInfoDao;
	@Autowired
	private ViewHouseInfoDao viewHouseInfoDao;
	@Autowired
	private ViewBldFloorDao viewBldFloorDao;
	@Autowired
	private ViewFloorCellDao viewFloorCellDao;
	@Autowired
	private MongoTemplate mongoTemplate;
	@Autowired
	private AppVersionDao appVersionDao;

	@Value("${app.updateUrl}")
	private String updateUrl;

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
	public ViewRegionInfo region(String regionId, String projectId, String bldNo, String floorNo) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("projectId", projectId);
		parameters.put("bldNo", bldNo);
		parameters.put("regionId", regionId);
		BldView bldView = viewHouseInfoDao.selectBldView(parameters);
		List<Cell> listCell = viewRegionInfoDao.selectBldCells(parameters);
		bldView.setCells(listCell);
		// 查询楼栋销售情况
		BldSales bldSales = viewHouseInfoDao.selectBldSalesData(parameters);
		bldView.setBldSales(bldSales);
		//查询楼栋下分层
		List<ViewBldFloor> bldFloors = viewBldFloorDao.selectBldFloors(parameters);
		if (bldFloors.size()>0) {
			String floor2;
			if (floorNo.equals("null")) {
				floor2 = bldFloors.get(0).getFloorNo();
			} else {
				floor2 = floorNo;

			}
			parameters.put("floorNo", floor2);
			List<ViewFloorCell> viewFloorCells = viewFloorCellDao.selectFloorCells(parameters);

			if (viewFloorCells.size() > 0) {
				// 楼栋下有单元标记，以单元显示房屋，并提供单元选择刷新功能
				ViewRegionInfo viewRegionInfo = region(regionId, projectId, bldNo,floor2, viewFloorCells.get(0).getCellNo());
				return viewRegionInfo;
			} else {
				ViewRegionInfo viewRegionInfo = viewRegionInfoDao.selectRegion(regionId);
				viewRegionInfo.setFloorNo(floor2);
				viewRegionInfo.setCellNo("null");
				viewRegionInfo.setBldFloors(bldFloors);
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
				viewRegionInfo.setSalesData(salesData);
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
				return viewRegionInfo;
			}
		}
		/*if (!StringUtils.isEmpty(bldView.getViewPath())) {
			// 楼栋下有单元标记，以单元显示房屋，并提供单元选择刷新功能
			Cell cell = listCell.get(0);
			ViewRegionInfo viewRegionInfo = region(regionId, projectId, bldNo, cell.getCellNo());
			viewRegionInfo.setCellNo(cell.getCellNo());
			return viewRegionInfo;
		}*/
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
		viewRegionInfo.setSalesData(salesData);

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
		List<HouseHoldSale> listHouseHold = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		viewRegionInfo.setListHouseHold(listHouseHold);
		// 查询小区楼栋列表
		List<RegionBlds> listRegionBlds = viewRegionInfoDao.selectRegionBlds(regionId);
		viewRegionInfo.setListRegionBlds(listRegionBlds);
		// 查询楼栋销售情况
		parameterMap.put("projectId", projectId);
		parameterMap.put("bldNo", bldNo);
		viewRegionInfo.setBldView(bldView);
		// 查询各种房屋类型的销售均价
		List<PriceShow> priceShows = selectPriceShow(regionId);
		if (priceShows.size() > 0) {
			viewRegionInfo.setPriceShows(priceShows);
		}
		return viewRegionInfo;
	}

	/**
	 * 刷新单元数据
	 *
	 * @param regionId
	 * @param projectId
	 * @param bldNo
	 * @param cellNo
	 * @return
	 */
	@Override
	public ViewRegionInfo region(String regionId, String projectId, String bldNo,String floorNo, String cellNo) {
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
		List<HouseHoldSale> listHouseHold = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
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
			/*parameterMap.put("cellNo", cellNo);
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
			bldView.setFloors(listFloor);*/
			// 查询楼栋销售情况
			parameterMap.put("floorNo", floorNo);
			parameterMap.put("cellNo", cellNo);
			BldSales bldSales = viewHouseInfoDao.selectBldSalesData(parameterMap);
			bldView.setBldSales(bldSales);
			viewRegionInfo.setBldView(bldView);
		}

		// 查询各种房屋类型的销售均价
		List<PriceShow> priceShows = selectPriceShow(regionId);
		if (priceShows.size() > 0) {
			viewRegionInfo.setPriceShows(priceShows);
		}

		viewRegionInfo.setFloorNo(floorNo);
		viewRegionInfo.setCellNo(cellNo);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("projectId", projectId);
		parameters.put("bldNo", bldNo);
		parameters.put("regionId", regionId);
		List<ViewBldFloor> bldFloors = viewBldFloorDao.selectBldFloors(parameters);
		parameters.put("floorNo", floorNo);
		List<ViewFloorCell> viewFloorCells = viewFloorCellDao.selectFloorCells(parameters);
		viewRegionInfo.setBldFloors(bldFloors);
		viewRegionInfo.setViewFloorCells(viewFloorCells);
		return viewRegionInfo;
	}

	@Override
	public BldView building(String regionId, String projectId, String bldNo) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("regionId", regionId);
		parameterMap.put("projectId", projectId);
		parameterMap.put("bldNo", bldNo);
		BldView bldView = viewHouseInfoDao.selectBldView(parameterMap);
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
		return bldView;
	}

	@Override
	public BldView building(String regionId, String projectId, String bldNo,String floorNo, String cellNo) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("regionId", regionId);
		parameterMap.put("projectId", projectId);
		parameterMap.put("bldNo", bldNo);
		parameterMap.put("floorNo", floorNo);
		BldView bldView = viewHouseInfoDao.selectBldView(parameterMap);

		List<Cell> listCell = viewRegionInfoDao.selectBldCells(parameterMap);
		bldView.setCells(listCell);
		// 查询楼栋单元楼层列表
		parameterMap.put("cellNo", cellNo);
		//只有一层
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
		return bldView;
	}

	@Override
	public ViewRegionInfo region(String regionId, String viewType) {
		ViewRegionInfo viewRegionInfo = viewRegionInfoDao.selectRegion(regionId);
		Map<String, Object> parameterMap = new HashMap<>();
		// 饼图数据start
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
		viewRegionInfo.setSalesData(salesData);
		// 饼图数据end
		// 轮播图数据start
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
		// 轮播图数据end
		// 鸟瞰定位图start
		// 查询每栋楼的坐标
		List<RegionBlds> listRegionBlds = viewRegionInfoDao.selectRegionBlds(regionId);
		viewRegionInfo.setListRegionBlds(listRegionBlds);
		// 鸟瞰定位图end
		// 户型销售情况start delete by jiankai
//		parameterMap.put("regionId", regionId);
//		List<HouseHoldSale> listHouseHold = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
//		viewRegionInfo.setListHouseHold(listHouseHold);
		// 户型销售情况end
		// 初始进入小区详情， 不显示楼盘图， delete by ljk
//		if (StringUtils.isEmpty(viewType) || "salesData".equals(viewType)) {
//			// 查询小区楼栋列表
//			List<RegionBlds> listRegionBlds = viewRegionInfoDao.selectRegionBlds(regionId);
//			viewRegionInfo.setListRegionBlds(listRegionBlds);
//			// 增加按楼按单元显示房屋
//			if (listRegionBlds != null && listRegionBlds.size() > 0) {
//				String projectId = listRegionBlds.get(0).getProjectId();
//				String bldNo = listRegionBlds.get(0).getBldNo();
//				parameterMap.put("projectId", projectId);
//				parameterMap.put("bldNo", bldNo);
//				BldView bldView = viewHouseInfoDao.selectBldView(parameterMap);
//				// 查询楼栋单元列表
//				List<Cell> listCell = viewRegionInfoDao.selectBldCells(parameterMap);
//				bldView.setCells(listCell);
//				// 查询楼栋楼层列表
//				List<Floor> listFloor = viewRegionInfoDao.selectBldFloors(parameterMap);
//				for (Floor floor : listFloor) {
//					// 查询楼层单元信息
//					parameterMap.put("floorNo", floor.getFloorNo());
//					List<Cell> listFloorCell = viewRegionInfoDao.selectFloorCells(parameterMap);
//					if (BigDecimal.valueOf(8).compareTo(bldView.getCellFloorNum()) < 0) {
//						// 每层每单元户数大于8， 不按单元显示，房屋都放到单元上
//						Cell cell = listFloorCell.get(0);
//						List<ViewHouseInfo> listHouse = viewHouseInfoDao.selectBldHouse(parameterMap);
//						cell.setHouses(listHouse);
//						bldView.setShowCell(false);
//					} else {
//						for (Cell cell : listFloorCell) {
//							parameterMap.put("cellNo", cell.getCellNo());
//							List<ViewHouseInfo> listHouse = viewHouseInfoDao.selectFloorCellHouses(parameterMap);
//							cell.setHouses(listHouse);
//							parameterMap.remove("cellNo");
//						}
//						bldView.setShowCell(true);
//					}
//					floor.setCells(listFloorCell);
//					floor.setShowCell(true);
//				}
//				bldView.setFloors(listFloor);
//				// 查询楼栋销售情况
//				BldSales bldSales = viewHouseInfoDao.selectBldSalesData(parameterMap);
//				bldView.setBldSales(bldSales);
//				viewRegionInfo.setBldView(bldView);
//			}
//		}

		// 查询住宅销售情况
		parameterMap.clear();
		parameterMap.put("regionId", regionId);
		parameterMap.put("houseUse", "01");
		List<HouseHoldSale> residence = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		viewRegionInfo.setResidence(residence);

		//查询住宅销售汇总
		RegionHouseSale residenceCollects = viewRegionInfoDao.selectRegionHouseSaleData(parameterMap);
		viewRegionInfo.setResidenceCollects(residenceCollects == null ? new RegionHouseSale() : residenceCollects);

		// 查询商用销售情况
		parameterMap.put("houseUse", "88");
		List<HouseHoldSale> business = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		viewRegionInfo.setBusiness(business);

		//查询商用销售汇总
		RegionHouseSale businessCollects = viewRegionInfoDao.selectRegionHouseSaleData(parameterMap);
		viewRegionInfo.setBusinessCollects(businessCollects == null ? new RegionHouseSale() : businessCollects);

		// 查询配套销售情况
		parameterMap.put("houseUse", "99");
		List<HouseHoldSale> mating = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		viewRegionInfo.setMating(mating);

		//查询配套销售汇总
		RegionHouseSale matingCollects = viewRegionInfoDao.selectRegionHouseSaleData(parameterMap);
		viewRegionInfo.setMatingCollects(matingCollects == null ? new RegionHouseSale() : matingCollects);

		// 查询各种房屋类型的销售均价
		List<PriceShow> priceShows = selectPriceShow(regionId);
		if (priceShows.size() > 0) {
			viewRegionInfo.setPriceShows(priceShows);
		}
		return viewRegionInfo;
	}

	@Override
	public ViewRegionInfo layout(String regionId) {
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
		List<HouseHoldSale> listHouseHold = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		viewRegionInfo.setSalesData(salesData);
		viewRegionInfo.setListHouseHold(listHouseHold);
		List<DocFiles> listDocFiles = queryRegionDocs(regionId, "houseHold");
		viewRegionInfo.setListDocFiles(listDocFiles);
		// 统计小区月销售量
		// parameterMap.put("regionId", regionId);
		parameterMap.put("preSaleDate", viewRegionInfo.getPreSaleDate());
		List<MonthData> listRegionSales = viewRegionInfoDao.selectRegionMonthSale(parameterMap);
		viewRegionInfo.setListRegionSales(listRegionSales);
		// 查询住宅销售情况
		parameterMap.clear();
		parameterMap.put("regionId", regionId);
		parameterMap.put("houseUse", "01");
		List<HouseHoldSale> residence = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		viewRegionInfo.setResidence(residence);

		//查询住宅销售汇总
		RegionHouseSale residenceCollects = viewRegionInfoDao.selectRegionHouseSaleData(parameterMap);
		viewRegionInfo.setResidenceCollects(residenceCollects == null ? new RegionHouseSale() : residenceCollects);

		// 查询商用销售情况
		parameterMap.put("houseUse", "88");
		List<HouseHoldSale> business = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		viewRegionInfo.setBusiness(business);

		//查询商用销售汇总
		RegionHouseSale businessCollects = viewRegionInfoDao.selectRegionHouseSaleData(parameterMap);
		viewRegionInfo.setBusinessCollects(businessCollects == null ? new RegionHouseSale() : businessCollects);

		// 查询配套销售情况
		parameterMap.put("houseUse", "99");
		List<HouseHoldSale> mating = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		viewRegionInfo.setMating(mating);

		//查询配套销售汇总
		RegionHouseSale matingCollects = viewRegionInfoDao.selectRegionHouseSaleData(parameterMap);
		viewRegionInfo.setMatingCollects(matingCollects == null ? new RegionHouseSale() : matingCollects);

		// 查询各种房屋类型的销售均价
		List<PriceShow> priceShows = selectPriceShow(regionId);
		if (priceShows.size() > 0) {
			viewRegionInfo.setPriceShows(priceShows);
		}
		return viewRegionInfo;
	}

	@Override
	public ViewRegionInfo images(String regionId) {
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
		List<HouseHoldSale> listHouseHold = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		viewRegionInfo.setSalesData(salesData);
		viewRegionInfo.setListHouseHold(listHouseHold);
		List<DocFiles> listDocFiles = queryRegionDocs(regionId, "regionImage");
		viewRegionInfo.setListDocFiles(listDocFiles);
		// 统计小区月销售量
		// parameterMap.put("regionId", regionId);
		parameterMap.put("preSaleDate", viewRegionInfo.getPreSaleDate());
		List<MonthData> listRegionSales = viewRegionInfoDao.selectRegionMonthSale(parameterMap);
		viewRegionInfo.setListRegionSales(listRegionSales);
		// 查询各种房屋类型的销售均价
		List<PriceShow> priceShows = selectPriceShow(regionId);
		if (priceShows.size() > 0) {
			viewRegionInfo.setPriceShows(priceShows);
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
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date = null;
				try {
					date = simpleDateFormat.format(new Date());
				} catch (Exception e) {
					logger.info("时间转换失败");
					throw new ServiceException("90", "时间转换失败");
				}
				paramterMap.put("nowDate",date);
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
		List<HouseHoldSale> residence = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		result.put("residence", residence);

		//查询住宅销售汇总
		RegionHouseSale houseHoldHouseSale = viewRegionInfoDao.selectRegionHouseSaleData(parameterMap);
		if (null != houseHoldHouseSale) {
			result.put("residenceCollects", houseHoldHouseSale);
		} else {
			houseHoldHouseSale = new RegionHouseSale();
			result.put("residenceCollects", houseHoldHouseSale);
		}

		// 查询商用销售情况
		parameterMap.put("houseUse", "88");
		List<HouseHoldSale> business = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		result.put("business", business);

		//查询商用销售汇总
		RegionHouseSale businessCollects = viewRegionInfoDao.selectRegionHouseSaleData(parameterMap);
		if (null != businessCollects) {
			result.put("businessCollects", businessCollects);
		} else {
			businessCollects = new RegionHouseSale();
			result.put("businessCollects", businessCollects);
		}

		// 查询配套销售情况
		parameterMap.put("houseUse", "99");
		List<HouseHoldSale> mating = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		result.put("mating", mating);

		//查询配套销售汇总
		RegionHouseSale matingCollects = viewRegionInfoDao.selectRegionHouseSaleData(parameterMap);
		result.put("matingCollects", matingCollects);
		// 查询各种房屋类型的销售均价
		List<PriceShow> priceShows = selectPriceShow(regionId);
		if (priceShows.size() > 0) {
			result.put("priceShows", priceShows);
		}
		return result;
	}

	/**
	 * 取销售价格
	 *
	 * @param regionId
	 * @return
	 */
	public List<PriceShow> selectPriceShow(String regionId) {
		List<PriceShow> priceShows = new ArrayList<>();
		Map<String, Object> priceMap = viewRegionInfoDao.selectPriceMap(regionId);
		if (null != priceMap && !priceMap.isEmpty()) {
			List<HousePrice> housePrices = new ArrayList<>();
			String houseShow = "";
			if (priceMap.containsKey("RESIDENCE_PRICE") && ((BigDecimal) priceMap.get("RESIDENCE_PRICE")).compareTo(BigDecimal.ZERO) > 0) {
				HousePrice housePrice = new HousePrice();
				houseShow += "住宅";
				housePrice.setHouseHold("住宅");
				housePrice.setPrice(((BigDecimal) priceMap.get("RESIDENCE_PRICE")).setScale(2, BigDecimal.ROUND_HALF_UP));
				housePrice.setArea(((BigDecimal) priceMap.get("RESIDENCE_AREA")).setScale(2, BigDecimal.ROUND_HALF_UP));
				housePrices.add(housePrice);
			}
			if (priceMap.containsKey("BUSINESS_PRICE") && ((BigDecimal) priceMap.get("BUSINESS_PRICE")).compareTo(BigDecimal.ZERO) > 0) {
				if (StringUtils.isEmpty(houseShow)) {
					houseShow += "商业";
				} else {
					houseShow += "/商业";
				}
				HousePrice housePrice = new HousePrice();
				housePrice.setHouseHold("商业");
				housePrice.setPrice(((BigDecimal) priceMap.get("BUSINESS_PRICE")).setScale(2, BigDecimal.ROUND_HALF_UP));
				housePrice.setArea(((BigDecimal) priceMap.get("BUSINESS_AREA")).setScale(2, BigDecimal.ROUND_HALF_UP));
				housePrices.add(housePrice);
			}
			if (!StringUtils.isEmpty(houseShow)) {
				PriceShow priceShow = new PriceShow();
				priceShow.setHouseShow(houseShow);
				priceShow.setHousePrices(housePrices);
				priceShows.add(priceShow);
			}
			housePrices = new ArrayList<>();
			if (priceMap.containsKey("CARPORT_PRICE") && ((BigDecimal) priceMap.get("CARPORT_PRICE")).compareTo(BigDecimal.ZERO) > 0) {
				HousePrice housePrice = new HousePrice();
				housePrice.setHouseHold("车位");
				housePrice.setPrice(((BigDecimal) priceMap.get("CARPORT_PRICE")).setScale(2, BigDecimal.ROUND_HALF_UP));
				housePrice.setArea(((BigDecimal) priceMap.get("CARPORT_AREA")).setScale(2, BigDecimal.ROUND_HALF_UP));
				housePrices.add(housePrice);
			}
			if (priceMap.containsKey("WAREHOUSE_PRICE") && ((BigDecimal) priceMap.get("WAREHOUSE_PRICE")).compareTo(BigDecimal.ZERO) > 0) {
				HousePrice housePrice = new HousePrice();
				housePrice.setHouseHold("仓库");
				housePrice.setPrice(((BigDecimal) priceMap.get("WAREHOUSE_PRICE")).setScale(2, BigDecimal.ROUND_HALF_UP));
				housePrice.setArea(((BigDecimal) priceMap.get("WAREHOUSE_AREA")).setScale(2, BigDecimal.ROUND_HALF_UP));
				housePrices.add(housePrice);
			}
			if (housePrices.size() > 0) {
				PriceShow priceShow = new PriceShow();
				priceShow.setHouseShow("配套");
				priceShow.setHousePrices(housePrices);
				priceShows.add(priceShow);
			}
		}
		return priceShows;
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
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("regionId", regionId);
		List<BldCells> listBldCells = viewRegionInfoDao.selectBlds(parameterMap);
		for (BldCells bldCells : listBldCells) {
			parameterMap.put("projectId", bldCells.getProjectId());
			parameterMap.put("bldNo", bldCells.getBldNo());
			List<Cell> listCell = viewRegionInfoDao.selectBldCells(parameterMap);
			bldCells.setListCell(listCell);
		}
		return listBldCells;
	}

	/**
	 * 根据房屋类型过滤楼栋单元
	 * @param regionId
	 * @param houseShow
	 * @return
	 */
	@Override
	public List<BldCells> queryRegionBldCells(String regionId, String houseShow) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("regionId", regionId);
		if (!StringUtils.isEmpty(houseShow)) {
			parameterMap.put("houseShow", houseShow);
		}
		List<BldCells> listBldCells = viewRegionInfoDao.selectBlds(parameterMap);
		for (BldCells bldCells : listBldCells) {
			parameterMap.put("projectId", bldCells.getProjectId());
			parameterMap.put("bldNo", bldCells.getBldNo());
			List<Cell> listCell = viewRegionInfoDao.selectBldCells(parameterMap);
			bldCells.setListCell(listCell);
		}
		return listBldCells;
	}

//	@Override
//	public Map<String, Object> pageHouses(String projectId, String bldNo, String cellNo, int pageNo, int pageSize) {
//		PageInfo<House> simpleHousePage = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(new ISelect() {
//			@Override
//			public void doSelect() {
//				Map<String, Object> paramterMap = new HashMap<>();
//				if (!StringUtils.isEmpty(projectId)) {
//					paramterMap.put("projectId", projectId);
//				}
//				if (!StringUtils.isEmpty(bldNo)) {
//					paramterMap.put("bldNo", bldNo);
//				}
//				if (!StringUtils.isEmpty(cellNo)) {
//					paramterMap.put("cellNo", cellNo);
//				}
//				viewHouseInfoDao.selectHouseByParameter(paramterMap);
//			}
//		});
//		Map<String, Object> result = new HashMap<>();
//		result.put("list", simpleHousePage.getList());
//		PageBean pageBean = new PageBean();
//		pageBean.setPageNo(simpleHousePage.getPageNum());
//		pageBean.setPageSize(simpleHousePage.getPageSize());
//		pageBean.setTotalPage(simpleHousePage.getPages());
//		pageBean.setTotalSize(simpleHousePage.getTotal());
//		result.put("page", pageBean);
//		return result;
//	}

	@Override
	public Map<String, Object> pageHouses(String projectId, String bldNo, String cellNo, int pageNo, int pageSize, String houseShow) {
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
				if (!StringUtils.isEmpty(houseShow)) {
					paramterMap.put("houseShow", houseShow);
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

//	@Override
//	public Map<String, Object> cellFloorSummary(String projectId, String bldNo, String cellNo) {
//		Map<String, Object> parameterMap = new HashMap<>();
//		parameterMap.put("projectId", projectId);
//		parameterMap.put("bldNo", bldNo);
//		parameterMap.put("cellNo", cellNo);
//		List<FloorSummary> listFloorSummary = viewHouseInfoDao.selectFloorSummary(parameterMap);
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("listFloorSummary", listFloorSummary);
//		for (FloorSummary floorSummary : listFloorSummary) {
//			parameterMap.put("floorNo", floorSummary.getFloorNo());
//			List<House> listHouse = viewHouseInfoDao.selectHouseByParameter(parameterMap);
//			floorSummary.setRooms(listHouse);
//		}
//		CellSummary cellSummary = viewHouseInfoDao.selectCellSummary(parameterMap);
//		resultMap.put("cellSummary", cellSummary);
//		cellSummary.setSellColor("dd6a62");
//		cellSummary.setNosaledColor("959595");
//		cellSummary.setNosaleColor("00dd02");
//		ViewRegionInfo viewRegionInfo = viewRegionInfoDao.selectRegionByProjectId(projectId);
//		List<String> imageList = new ArrayList<>();
//		imageList.add(viewRegionInfo.getViewPath());
//		resultMap.put("imageList", imageList);
//		return resultMap;
//	}

	/**
	 * 根据房屋类型过滤单元楼层
	 * @param projectId
	 * @param bldNo
	 * @param cellNo
	 * @param houseShow
	 * @return
	 */
	@Override
	public Map<String, Object> cellFloorSummary(String projectId, String bldNo, String cellNo, String houseShow) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("projectId", projectId);
		parameterMap.put("bldNo", bldNo);
		parameterMap.put("cellNo", cellNo);
		if (!StringUtils.isEmpty(houseShow)) {
			parameterMap.put("houseShow", houseShow);
		}
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

	@Override
	public <T> T selectAppVersion(String versionId) {
		// 查询当前版本以后是否有需要强制更新版本
		AppVersion appVersion = appVersionDao.selectAppVersion(versionId);
		if (null != appVersion) {
			appVersion.setUpdateUrl(updateUrl);
			return (T) appVersion;
		}
		return (T) "{}";
	}

	@Override
	public void download(HttpServletRequest httpServletRequest, HttpServletResponse response) throws Exception {
		InputStream fileStream = (InputStream) new FileInputStream("D:\\cffg.apk");
		response.setContentType("application/vnd.android.package-archive");
		response.setHeader("content-disposition", "attachment; filename=cffg.apk");
		if (fileStream instanceof FileInputStream) {
			response.setContentLength(((FileInputStream) fileStream).available());
		}

		ServletOutputStream os = response.getOutputStream();
		byte[] buf = new byte[4096];

		int readLength;
		while ((readLength = fileStream.read(buf)) != -1) {
			os.write(buf, 0, readLength);
		}

		os.flush();
		fileStream.close();
	}
}