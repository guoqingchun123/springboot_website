package com.bestvike.website.service.impl;

import com.bestvike.website.dao.ViewProjectInfoDao;
import com.bestvike.website.dao.ViewRegionInfoDao;
import com.bestvike.website.data.ViewPresalecard;
import com.bestvike.website.entity.MonthData;
import com.bestvike.website.entity.Region;
import com.bestvike.website.entity.Trade;
import com.bestvike.website.service.LayoutService;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LayoutServiceImpl implements LayoutService {

	@Autowired
	private ViewRegionInfoDao viewRegionInfoDao;
	@Autowired
	private ViewProjectInfoDao viewProjectInfoDao;

	@Override
	public List<Region> selectAllRegions(String order) {
		Map<String, Object> maps = new HashMap<>();
		maps.put("order", order);
		return viewRegionInfoDao.selectAllRegions(maps);
	}

	@Override
	public List<Region> selectRegionByKeywords(String keywords) {
		Map<String, Object> maps = new HashMap<>();
		if (!StringUtils.isEmpty(keywords)) {
			maps.put("keywords", "%" + keywords + "%");
		}
		return viewRegionInfoDao.selectRegionByParameter(maps);
	}

	@Override
	public List<ViewPresalecard> selectPerBaseInfoByProjectNo(String projectNo) {
		return viewProjectInfoDao.selectPerBaseInfoByProjectNo(projectNo);
	}

	/**
	 * 获取大数据屏显示数据
	 * @return
	 */
	@Override
	public Map<String, Object> selectConsoleData() {
		Map<String, Object> resultMap = new HashMap<>();
		// 在售项目数
		BigDecimal regionNum = viewRegionInfoDao.selectRegionNum();
		if (regionNum == null) {
			regionNum = BigDecimal.valueOf(0);
		}
		resultMap.put("regionNum", new DecimalFormat("###,##0").format(regionNum));
		// 总体存量面积
		BigDecimal stockArea = viewRegionInfoDao.selectStockArea();
		if (stockArea == null) {
			stockArea = BigDecimal.valueOf(0);
		}
		resultMap.put("stockArea", new DecimalFormat("###,##0.00").format(stockArea));
		// 昨日交易量
		BigDecimal yesterdayTradingArea = viewRegionInfoDao.selectYesterdayTradingArea();
		if (yesterdayTradingArea == null) {
			yesterdayTradingArea = BigDecimal.valueOf(0);
		}
		resultMap.put("yesterdayTradingArea", new DecimalFormat("###,##0").format(yesterdayTradingArea));
		// 今日交易量
		BigDecimal todayTradingArea = viewRegionInfoDao.selectTodayTradingArea();
		if (todayTradingArea == null) {
			todayTradingArea = BigDecimal.valueOf(0);
		}
		resultMap.put("todayTradingArea", new DecimalFormat("###,##0").format(todayTradingArea));
		// 月交易量
		List<MonthData> monthData = viewRegionInfoDao.selectMonthData();
		resultMap.put("monthData", monthData);
		// 今日交易流水
		List<Trade> todayTrades = viewRegionInfoDao.selectTodayTrade();
		resultMap.put("todayTrades", todayTrades);
		return resultMap;
	}
}