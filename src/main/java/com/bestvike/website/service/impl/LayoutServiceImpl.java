package com.bestvike.website.service.impl;

import com.bestvike.website.dao.ViewProjectInfoDao;
import com.bestvike.website.dao.ViewRegionInfoDao;
import com.bestvike.website.data.ViewPresalecard;
import com.bestvike.website.entity.DivisionTrade;
import com.bestvike.website.entity.MonthData;
import com.bestvike.website.entity.Region;
import com.bestvike.website.entity.Trade;
import com.bestvike.website.service.LayoutService;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
		// 月供给及销售情况
		List<Map<String, Object>> monthArea = viewRegionInfoDao.selectMonthArea();
		resultMap.put("monthArea", monthArea);

		// 月销售均价
		List<Map<String, Object>> monthAvgPrice = viewRegionInfoDao.selectMonthAvgPrice();
		resultMap.put("monthAvgPrice", monthAvgPrice);

		// 月库存量
		List<Map<String, Object>> monthStocks = viewRegionInfoDao.selectMonthStocks();
		resultMap.put("monthStocks", monthStocks);

		// 今日交易流水
		PageInfo<Trade> tradePage = PageHelper.startPage(1, 15).doSelectPageInfo(new ISelect() {
			@Override
			public void doSelect() {
				viewRegionInfoDao.selectTodayTrade();
			}
		});
		resultMap.put("todayTrades", tradePage.getList());
		// 按区查询三月内交易情况
		List<DivisionTrade> divisionTrades = viewRegionInfoDao.selectDivisionTrade();
		resultMap.put("divisionTrades", divisionTrades);

		return resultMap;
	}
}