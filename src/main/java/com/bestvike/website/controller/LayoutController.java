package com.bestvike.website.controller;

import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.entity.Region;
import com.bestvike.website.service.LayoutService;
import com.bestvike.website.service.ProjectService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lihua on 2016/9/30.
 */
@Controller
public class LayoutController extends BaseController {

	@Autowired
	private LayoutService layoutService;
	@Autowired
	private ProjectService projectService;

	@GetMapping(value = "/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
//		List<Region> listViewRegionInfo = layoutService.selectAllRegions("default");
//		mv.addObject("regions", listViewRegionInfo);
		mv.setViewName("index");
		return mv;
	}

	@GetMapping(value = "/maps")
	public ModelAndView maps() {
		ModelAndView mv = new ModelAndView();
		List<Region> listViewRegionInfo = layoutService.selectAllRegions("default");
		mv.addObject("regions", listViewRegionInfo);
		mv.setViewName("maps");
		return mv;
	}

	@GetMapping(value = "/content/{regionId}")
	public ModelAndView content(@PathVariable String regionId, @RequestParam(required = false) String viewType) {
		ModelAndView mv = new ModelAndView();
		viewType = StringUtils.isEmpty(viewType) ? "salesData" : viewType;
		ViewRegionInfo viewRegionInfo = projectService.region(regionId, viewType);
		mv.addObject("region", viewRegionInfo);
		// presellState -> presellStateName & presellStateClass
		/*String houses = "{\"regionName\": \"测试小区\", \"showCell\": false,"
					+ "\"viewPath\": \"http://222.74.69.146:180/file/group1/M00/00/07/CgoMAV0SJGKAVAGoABBEyaTS-qg876.jpg\","
					+ "\"cells\": [{\"cellNo\": \"001\", \"cellName\": \"1单元\", \"showHouseNum\": 2, \"maxHouseNum\": 2}, {\"cellNo\": \"002\", \"cellName\": \"2单元\", \"showHouseNum\": 3, \"maxHouseNum\": 3}, {\"cellNo\": \"003\", \"cellName\": \"3单元\", \"showHouseNum\": 3, \"maxHouseNum\": 3}],"
					+ "\"floors\": ["
						+ "{\"floorNo\": \"001\", \"floorName\": \"1层\", "
						+ "\"cells\": ["
							+ "{\"showHouseNum\": 2, \"maxHouseNum\": 2, \"houses\": [{\"showName\": \"1-001\", \"presellStateName\": \"已售\", \"presellStateCode\": \"saled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}, {\"showName\": \"1-002\", \"presellStateName\": \"未售\", \"presellStateCode\": \"noSaled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}]},"
							+ "{\"showHouseNum\": 3, \"maxHouseNum\": 3, \"houses\": [{\"showName\": \"1-003\", \"presellStateName\": \"已售\", \"presellStateCode\": \"saled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}, {\"showName\": \"1-004\", \"presellStateName\": \"未售\", \"presellStateCode\": \"noSaled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}, {\"showName\": \"1-004\", \"presellStateName\": \"未售\", \"presellStateCode\": \"noSaled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}, {\"showName\": \"1-004\", \"presellStateName\": \"未售\", \"presellStateCode\": \"noSaled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}, {\"showName\": \"1-004\", \"presellStateName\": \"未售\", \"presellStateCode\": \"noSaled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}, {\"showName\": \"1-004\", \"presellStateName\": \"未售\", \"presellStateCode\": \"noSaled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}, {\"showName\": \"1-004\", \"presellStateName\": \"未售\", \"presellStateCode\": \"noSaled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}]},"
							+ "{\"showHouseNum\": 3, \"maxHouseNum\": 3, \"houses\": [{\"showName\": \"1-003\", \"presellStateName\": \"已售\", \"presellStateCode\": \"saled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}, {\"showName\": \"1-004\", \"presellStateName\": \"未售\", \"presellStateCode\": \"noSaled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}]}"
							+ "]"
						+ "},"
						+ "{\"floorNo\": \"002\", \"floorName\": \"2层\","
						+ "\"cells\": ["
							+ "{\"showHouseNum\": 2, \"maxHouseNum\": 2, \"houses\": [{\"showName\": \"2-001\", \"presellStateName\": \"已售\", \"presellStateCode\": \"saled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}, {\"showName\": \"2-002\", \"presellStateName\": \"未售\", \"presellStateCode\": \"noSaled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}]},"
							+ "{\"showHouseNum\": 3, \"maxHouseNum\": 3, \"houses\": [{\"showName\": \"2-001\", \"presellStateName\": \"已售\", \"presellStateCode\": \"saled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}, {\"showName\": \"2-002\", \"presellStateName\": \"未售\", \"presellStateCode\": \"noSaled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}]},"
							+ "{\"showHouseNum\": 3, \"maxHouseNum\": 3, \"houses\": [{\"showName\": \"2-003\", \"presellStateName\": \"已售\", \"presellStateCode\": \"saled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}, {\"showName\": \"2-004\", \"presellStateName\": \"未售\", \"presellStateCode\": \"noSaled\", \"constructArea\": 100.23, \"ownArea\": 90.23, \"houseUse\": \"仓库\", \"houseHold\": \"敞开式\"}]}"
							+ "]"
						+ "}"
					+ "],"
					+ "\"bldSales\": {\"projectId\": \"11207019000623\",\"bldNo\": \"14\",\"bldName\": \"14#\",\"totalNum\": 68,\"sellNum\": 0,\"noSaledNum\": 68,\"noSaleNum\": 0,\"closeNum\": 0,\"mortgageNum\": 0,\"frozenNum\": 0},"
					+ "\"listRegionBlds\": [{\"projectId\": \"100001\", \"bldNo\": \"0001\", \"bldName\": \"1号楼\", \"location\": \"top:44%;left:22%\"}]"
					+ "}";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ViewRegionInfo viewRegionInfo = objectMapper.readValue(houses, ViewRegionInfo.class);
			mv.addObject("region", viewRegionInfo);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		mv.setViewName(viewType);
		return mv;
	}

	@GetMapping(value = "/content/{regionId}/{projectId}/{bldNo}")
	public ModelAndView content(@PathVariable String regionId, @PathVariable String projectId, @PathVariable String bldNo) {
		ModelAndView mv = new ModelAndView();
		ViewRegionInfo viewRegionInfo = projectService.region(regionId, projectId, bldNo);
		mv.addObject("region", viewRegionInfo);
		mv.addObject("projectId", projectId);
		mv.addObject("bldNo", bldNo);
		mv.setViewName("salesData");
		return mv;
	}

	@GetMapping(value = "/content/{regionId}/{projectId}/{bldNo}/{cellNo}")
	public ModelAndView content(@PathVariable String regionId, @PathVariable String projectId, @PathVariable String bldNo, @PathVariable String cellNo) {
		ModelAndView mv = new ModelAndView();
		ViewRegionInfo viewRegionInfo = projectService.region(regionId, projectId, bldNo, cellNo);
		mv.addObject("region", viewRegionInfo);
		mv.setViewName("salesData");
		return mv;
	}

	@GetMapping(value = "/api/app/maps")
	public ModelAndView appMaps(@RequestParam(required = false) String regionId,
		@RequestParam(required = false) String x, @RequestParam(required = false) String y) {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> data = new HashMap<>();
		if (!StringUtils.isEmpty(regionId)) {
			data.put("regionId", regionId);
		}
		if (!StringUtils.isEmpty(x) && !StringUtils.isEmpty(y)) {
			data.put("x", x);
			data.put("y", y);
		}
		if (!data.isEmpty()) {
			mv.addObject("data", data);
		}
		mv.setViewName("appMaps");
		return mv;
	}
}
