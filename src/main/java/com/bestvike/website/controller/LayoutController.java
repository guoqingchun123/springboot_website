package com.bestvike.website.controller;

import com.bestvike.website.data.ViewCorpInfo;
import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.service.LayoutService;
import com.bestvike.website.service.ProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		List<ViewRegionInfo> listViewRegionInfo = layoutService.selectAllRegions("default");
		mv.addObject("regions", listViewRegionInfo);
		mv.setViewName("index");
		return mv;
	}

	@GetMapping(value = "/maps")
	public ModelAndView maps() {
		ModelAndView mv = new ModelAndView();
		List<ViewRegionInfo> listViewRegionInfo = layoutService.selectAllRegions("default");
		mv.addObject("regions", listViewRegionInfo);
		mv.setViewName("maps");
		return mv;
	}

	@GetMapping(value = "/content/{regionId}")
	public ModelAndView content(@PathVariable String regionId) {
		ModelAndView mv = new ModelAndView();
		ViewRegionInfo viewRegionInfo = projectService.region(regionId);
		mv.addObject("region", viewRegionInfo);
		mv.setViewName("content");
		return mv;
	}
}
