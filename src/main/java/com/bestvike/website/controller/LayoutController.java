package com.bestvike.website.controller;

import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.entity.Region;
import com.bestvike.website.service.LayoutService;
import com.bestvike.website.service.ProjectService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		mv.setViewName(viewType);
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
