package com.bestvike.website.service.impl;

import com.bestvike.commons.exception.ServiceException;
import com.bestvike.website.dao.ViewProjectInfoDao;
import com.bestvike.website.dao.ViewRegionInfoDao;
import com.bestvike.website.data.ViewPresalecard;
import com.bestvike.website.entity.Lastest;
import com.bestvike.website.entity.Region;
import com.bestvike.website.entity.RegionTrade;
import com.bestvike.website.service.LayoutService;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LayoutServiceImpl implements LayoutService {
	protected Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	private ViewRegionInfoDao viewRegionInfoDao;
	@Autowired
	private ViewProjectInfoDao viewProjectInfoDao;

	@Override
	public List<Region> selectAllRegions(String order) {
		Map<String, Object> maps = new HashMap<>();
		maps.put("order", order);
		java.util.Date date = new java.util.Date();
		maps.put("nowDate",new Date(date.getTime()));
		return viewRegionInfoDao.selectAllRegions(maps);
	}

	@Override
	public List<Region> selectRegionByKeywords(String keywords) {
		Map<String, Object> maps = new HashMap<>();
		if (!StringUtils.isEmpty(keywords)) {
			maps.put("keywords", "%" + keywords + "%");
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = null;
		try {
			date = simpleDateFormat.format(new java.util.Date());
		} catch (Exception e) {
			logger.info("时间转换失败");
			throw new ServiceException("90", "时间转换失败");
		}
		maps.put("nowDate",date);
		return viewRegionInfoDao.selectRegionByParameter(maps);
	}

	@Override
	public List<ViewPresalecard> selectPerBaseInfoByProjectNo(String projectNo) {
		return viewProjectInfoDao.selectPerBaseInfoByProjectNo(projectNo);
	}

	/**
	 * 获取大数据屏显示数据
	 *
	 * @return
	 */
	@Override
	public Map<String, Object> selectConsoleData() {
		Map<String, Object> resultMap = new HashMap<>();
		// 截止当前住宅、商品房套数面积
		Lastest lastest = viewRegionInfoDao.selectLatest();
		resultMap.put("lastest", lastest);
		// 月供给及销售情况
		List<Map<String, Object>> monthArea = viewRegionInfoDao.selectMonthArea();
		resultMap.put("monthArea", monthArea);

		// 月销售均价
		List<Map<String, Object>> monthAvgPrice = viewRegionInfoDao.selectMonthAvgPrice();
		resultMap.put("monthAvgPrice", monthAvgPrice);

		// 月库存量
		List<Map<String, Object>> monthStocks = viewRegionInfoDao.selectMonthStocks();
		resultMap.put("monthStocks", monthStocks);

//		// 按区查询三月内交易情况
//		List<DivisionTrade> divisionTrades = viewRegionInfoDao.selectDivisionTrade();
//		resultMap.put("divisionTrades", divisionTrades);

		// 查询小区及30日内交易量
		Map<String, Object> parameterMap = new HashMap<>();
		List<RegionTrade> regionTrades = viewRegionInfoDao.selectRegionTrade(parameterMap);
		Map<String, BigDecimal[]> regionMap = new HashMap<>();
		for (RegionTrade regionTrade : regionTrades) {
			BigDecimal[] positions = new BigDecimal[2];
			positions[0] = regionTrade.getX();
			positions[1] = regionTrade.getY();
			regionMap.put(regionTrade.getRegionName(), positions);
		}
		resultMap.put("regionMap", regionMap);
		resultMap.put("regionTrades", regionTrades);
		return resultMap;
	}

	@Override
	public List<RegionTrade> selectRegionTrades(String dataType) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("dataType", dataType);
		return viewRegionInfoDao.selectRegionTrade(parameterMap);
	}
}