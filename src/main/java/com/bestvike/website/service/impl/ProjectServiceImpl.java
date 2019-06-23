package com.bestvike.website.service.impl;

import com.bestvike.website.dao.ViewHouseInfoDao;
import com.bestvike.website.dao.ViewRegionInfoDao;
import com.bestvike.website.data.ViewHouseInfo;
import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.document.Division;
import com.bestvike.website.entity.BldCells;
import com.bestvike.website.entity.Cell;
import com.bestvike.website.entity.FloorSummary;
import com.bestvike.website.entity.House;
import com.bestvike.website.entity.HouseHoldSales;
import com.bestvike.website.entity.PageBean;
import com.bestvike.website.entity.RegionCell;
import com.bestvike.website.entity.Region;
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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("regionId", regionId);
		List<HouseHoldSales> listHouseHold = viewRegionInfoDao.selectRegionHouseHoldData(parameterMap);
		viewRegionInfo.setSalesData(salesData);
		viewRegionInfo.setListHouseHold(listHouseHold);
		if (StringUtils.isEmpty(viewType) || "salesData".equals(viewType)) {
			// 楼盘图
			List<RegionCell> listCellInfo = viewHouseInfoDao.selectRegionCellList(regionId);
			viewRegionInfo.setListCell(listCellInfo);
			parameterMap = new HashMap<>();
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

	public Map<String, Object> pageRegions(String keywords, int pageNo, int pageSize, String divisonCode, String price, String houseHold, String sort) {
		PageInfo<Region> simpleRegionPage = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(new ISelect() {
			@Override
			public void doSelect() {
				Map<String, Object> paramterMap = new HashMap<>();
				if (!StringUtils.isEmpty(keywords)) {
					paramterMap.put("keywords", "%" + keywords + "%");
				}
				if (!StringUtils.isEmpty(divisonCode)) {
					paramterMap.put("divisonCode", divisonCode);
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
		com.bestvike.website.document.Region region = new com.bestvike.website.document.Region();
		region.setRegionId(UUID.randomUUID().toString());
		region.setRegionName("小眺望小区");
		mongoTemplate.save(region);
		return mongoTemplate.findAll(Division.class);
	}

	/**
	 * 查询小区销售统计信息及logos
	 *
	 * @param regionId
	 * @return
	 */
	public Map<String, Object> queryRegionSales(String regionId) {
		Map<String, Object> result = new HashMap<>();
		List<String> regionLogos = new ArrayList<>();
		regionLogos.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1262922132,88008920&fm=26&gp=0.jpg");
		regionLogos.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1262922132,88008920&fm=26&gp=0.jpg");
		result.put("logos", regionLogos);
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
	public List<Map<String, Object>> queryRegionDocs(String regionId, String type) {
		List<Map<String, Object>> listResult = new ArrayList<>();
		if (!StringUtils.isEmpty(type)) {
			switch (type) {
				case "houseHold":
					// 户型图
					Map<String, Object> houseHold = new HashMap<>();
					houseHold.put("docType", "roomHold");
					houseHold.put("docName", "户型图");
					List<Map> houseHoldList = mongoTemplate.find(Query.query(Criteria.
						where("keyId").is(regionId).
						and("fileType").is("houseHold").
						and("subType").is("roomHold")), Map.class, "log_file");
					houseHold.put("imageList", houseHoldList);
					listResult.add(houseHold);
					// 样板间
					Map<String, Object> prototypeRoom = new HashMap<>();
					prototypeRoom.put("docType", "prototypeRoom");
					prototypeRoom.put("docName", "样板间");
					List<Map> prototypeRoomList = mongoTemplate.find(Query.query(Criteria.
						where("keyId").is(regionId).
						and("fileType").is("houseHold").
						and("subType").is("prototypeRoom")), Map.class, "log_file");
					prototypeRoom.put("imageList", prototypeRoomList);
					listResult.add(prototypeRoom);
					break;
				case "regionImage":
					// 鸟瞰图
					Map<String, Object> aerialView = new HashMap<>();
					aerialView.put("docType", "aerialView");
					aerialView.put("docName", "小区鸟瞰图");
					List<Map> aerialViewList = mongoTemplate.find(Query.query(Criteria.
						where("keyId").is(regionId).
						and("fileType").is("regionImage").
						and("subType").is("aerialView")), Map.class, "log_file");
					aerialView.put("imageList", aerialViewList);
					listResult.add(aerialView);
					// 施工现场
					Map<String, Object> constructionSite = new HashMap<>();
					constructionSite.put("docType", "constructionSite");
					constructionSite.put("docName", "施工现场");
					List<Map> constructionSiteList = mongoTemplate.find(Query.query(Criteria.
						where("keyId").is(regionId).
						and("fileType").is("regionImage").
						and("subType").is("constructionSite")), Map.class, "log_file");
					constructionSite.put("imageList", constructionSiteList);
					listResult.add(constructionSite);
					break;
			}
		}
		return listResult;
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