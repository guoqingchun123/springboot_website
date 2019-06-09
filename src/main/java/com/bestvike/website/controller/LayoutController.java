package com.bestvike.website.controller;

import com.bestvike.website.data.ArcCorpInfo;
import com.bestvike.website.data.ArcProjectCoordinate;
import com.bestvike.website.data.ArcProjectInfo;
import com.bestvike.website.data.PerBaseInfo;
import com.bestvike.website.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by lihua on 2016/9/30.
 */
@Controller
public class LayoutController extends BaseController {

	@Autowired
	private LayoutService layoutService;

	@GetMapping(value = "/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		List<ArcProjectCoordinate> listArcProjectCoordinate = layoutService.selectAllProject();
		mv.addObject("projects", listArcProjectCoordinate);
		mv.setViewName("index");
		return mv;
	}

	@GetMapping(value = "/content/{projectNo}")
	public ModelAndView content(@PathVariable String projectNo) {
		ModelAndView mv = new ModelAndView();
		ArcProjectInfo arcProjectInfo = layoutService.selectProject(projectNo);
		mv.addObject("project", arcProjectInfo);
		if (arcProjectInfo != null && !StringUtils.isEmpty(arcProjectInfo.getCorpno())) {
			ArcCorpInfo arcCorpInfo = layoutService.selectCorpInfo(arcProjectInfo.getCorpno());
			mv.addObject("corp", arcCorpInfo);
		}

		mv.setViewName("content");
		return mv;
	}
}
