package com.bestvike.website.controller.app;

import com.bestvike.website.controller.BaseController;
import com.bestvike.website.document.Division;
import com.bestvike.website.service.ProjectService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lihua on 2016/9/30.
 */
@RestController
@RequestMapping(value = "/api/weixin")
public class RegionControllerForWeixin extends BaseController {

	@Autowired
	private ProjectService projectService;

	@GetMapping(value = "/regions")
	public Map<String, Object> regions(@RequestParam(required = false) String keywords,
		@RequestParam int pageNo, @RequestParam int pageSize) {
		return projectService.pageRegions(keywords, pageNo, pageSize);
	}

	@GetMapping(value = "/divisions")
	public List<Division> divisions() {
		return projectService.queryDivision();
	}
}