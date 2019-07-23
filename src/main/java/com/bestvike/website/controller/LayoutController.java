package com.bestvike.website.controller;

import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.entity.BldView;
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
	@GetMapping(value = "/fragments-regions")
	public ModelAndView regions(@RequestParam(required = false) String keywords,
								@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam(required = false) String divisionCode,
								@RequestParam(required = false) String price, @RequestParam(required = false) String houseHold,
								@RequestParam(required = false) String sort) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("fragments/index :: regions");

		Map<String, Object> resultMap = projectService.pageRegions(keywords, pageNo, pageSize, divisionCode, price, houseHold, sort);
		mv.addObject("data", resultMap);
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

	@GetMapping(value = "/sales/{regionId}")
	public ModelAndView content(@PathVariable String regionId, @RequestParam(required = false) String viewType) {
		ModelAndView mv = new ModelAndView();
		viewType = StringUtils.isEmpty(viewType) ? "sales" : viewType;
		ViewRegionInfo viewRegionInfo = projectService.region(regionId, viewType);
		mv.addObject("region", viewRegionInfo);
		mv.setViewName(viewType);
		return mv;
	}

	@GetMapping(value = "/layout/{regionId}")
	public ModelAndView layout(@PathVariable String regionId) {
		ModelAndView mv = new ModelAndView();
		ViewRegionInfo viewRegionInfo = projectService.layout(regionId);
		mv.addObject("region", viewRegionInfo);
		mv.setViewName("layout");
		return mv;
	}

	@GetMapping(value = "/images/{regionId}")
	public ModelAndView images(@PathVariable String regionId) {
		ModelAndView mv = new ModelAndView();
		ViewRegionInfo viewRegionInfo = projectService.images(regionId);
		mv.addObject("region", viewRegionInfo);
		mv.setViewName("images");
		return mv;
	}

	@GetMapping(value = "/sales/{regionId}/{projectId}/{bldNo}/{floorNo}")
	public ModelAndView content(@PathVariable String regionId, @PathVariable String projectId, @PathVariable String bldNo, @PathVariable String floorNo) {
		ModelAndView mv = new ModelAndView();
		ViewRegionInfo viewRegionInfo = projectService.region(regionId, projectId, bldNo, floorNo);
		mv.addObject("region", viewRegionInfo);
		mv.addObject("projectId", projectId);
		mv.addObject("bldNo", bldNo);
		if (!StringUtils.isEmpty(viewRegionInfo.getFloorNo())) {
			mv.addObject("floorNo", viewRegionInfo.getFloorNo());
		}
		if (!StringUtils.isEmpty(viewRegionInfo.getCellNo())) {
			mv.addObject("cellNo", viewRegionInfo.getCellNo());
		}
		mv.setViewName("sales");
		return mv;
	}

	@GetMapping(value = "/sales/{regionId}/{projectId}/{bldNo}/{floorNo}/{cellNo}")
	public ModelAndView content(@PathVariable String regionId, @PathVariable String projectId, @PathVariable String bldNo, @PathVariable String floorNo, @PathVariable String cellNo) {
		ModelAndView mv = new ModelAndView();
		ViewRegionInfo viewRegionInfo = projectService.region(regionId, projectId, bldNo, floorNo, cellNo);
		mv.addObject("region", viewRegionInfo);
		mv.addObject("projectId", projectId);
		mv.addObject("bldNo", bldNo);
		mv.addObject("floorNo", floorNo);
		mv.addObject("cellNo", cellNo);
		mv.setViewName("sales");
		return mv;
	}

	@GetMapping(value = "/building/{regionId}/{projectId}/{bldNo}")
	public ModelAndView contentBuilding(@PathVariable String regionId, @PathVariable String projectId, @PathVariable String bldNo) {
		ModelAndView mv = new ModelAndView();
		BldView bldView = projectService.building(regionId, projectId, bldNo);
		mv.addObject("building", bldView);
		mv.setViewName("fragments/sales :: building");
		return mv;
	}

	@GetMapping(value = "/building/{regionId}/{projectId}/{bldNo}/{floorNo}/{cellNo}")
	public ModelAndView contentCell(@PathVariable String regionId, @PathVariable String projectId, @PathVariable String bldNo, @PathVariable String floorNo, @PathVariable String cellNo) {
		ModelAndView mv = new ModelAndView();
		BldView bldView = projectService.building(regionId, projectId, bldNo, floorNo, cellNo);
		mv.addObject("building", bldView);
		mv.setViewName("fragments/sales :: building");
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

	/**
	 * 后台进入登录页面
	 * @return
	 */
	@GetMapping(value = "/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}

	@GetMapping(value = "/demo/init")
	public ModelAndView demoInit() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("demo");
		return mv;
	}

	@GetMapping(value = "/demo/content")
	public ModelAndView demoContent() throws InterruptedException {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("demo-content :: layout");
		mv.addObject("data", "hello!");
		Thread.sleep(3000);
		return mv;
	}
}
