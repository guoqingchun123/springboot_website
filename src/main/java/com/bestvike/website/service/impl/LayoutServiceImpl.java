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
		// 月交易量
		List<MonthData> monthDatas = viewRegionInfoDao.selectMonthData();
		if (monthDatas != null && monthDatas.size() > 0) {
			String[] months = new String[monthDatas.size()];
			BigDecimal[] residences = new BigDecimal[monthDatas.size()];
			BigDecimal[] businesses = new BigDecimal[monthDatas.size()];
			BigDecimal[] matings = new BigDecimal[monthDatas.size()];
			BigDecimal[] residenceStocks = new BigDecimal[monthDatas.size()];
			BigDecimal[] businessStocks = new BigDecimal[monthDatas.size()];
			BigDecimal[] matingStocks = new BigDecimal[monthDatas.size()];
			BigDecimal[] residenceTotals = new BigDecimal[monthDatas.size()];
			BigDecimal[] businessTotals = new BigDecimal[monthDatas.size()];
			BigDecimal[] matingTotals = new BigDecimal[monthDatas.size()];
			BigDecimal[] residencePrices = new BigDecimal[monthDatas.size()];
			BigDecimal[] businessPrices = new BigDecimal[monthDatas.size()];
			BigDecimal[] matingPrices = new BigDecimal[monthDatas.size()];
			for (int i = 0; i < monthDatas.size(); i++) {
				MonthData monthData = monthDatas.get(i);
				months[i] = monthData.getDataMonth();
				residences[i] = monthData.getResidenceArea();
				businesses[i] = monthData.getBusinessNum();
				matings[i] = monthData.getMatingArea();
				residenceStocks[i] = monthData.getResidenceStockArea();
				businessStocks[i] = monthData.getBusinessStockArea();
				matingStocks[i] = monthData.getMatingStockArea();
				residenceTotals[i] = monthData.getResidenceArea().add(monthData.getResidenceStockArea());
				businessTotals[i] = monthData.getBusinessStockArea().add(monthData.getBusinessStockArea());
				matingTotals[i] = monthData.getMatingStockArea().add(monthData.getMatingStockArea());
				residencePrices[i] = monthData.getResidencePrice();
				businessPrices[i] = monthData.getBusinessPrice();
				matingPrices[i] = monthData.getMatingPrice();
			}
			resultMap.put("months", months);
			resultMap.put("residences", residences);
			resultMap.put("businesses", businesses);
			resultMap.put("matings", matings);
			resultMap.put("residenceStocks", residenceStocks);
			resultMap.put("businessStocks", businessStocks);
			resultMap.put("matingStocks", matingStocks);
			resultMap.put("residenceTotals", residenceTotals);
			resultMap.put("businessTotals", businessTotals);
			resultMap.put("matingTotals", matingTotals);
			resultMap.put("residencePrices", residencePrices);
			resultMap.put("businessPrices", businessPrices);
			resultMap.put("matingPrices", matingPrices);
		}

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