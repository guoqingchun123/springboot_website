package com.bestvike.website.controller;

import com.bestvike.website.data.ArcProjectCoordinate;
import com.bestvike.website.data.PerBaseInfo;
import com.bestvike.website.service.LayoutService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lihua on 2016/9/30.
 */
@RestController
public class ProjectController extends BaseController {

	@Autowired
	private LayoutService layoutService;


	@GetMapping(value = "/projects")
	public List<ArcProjectCoordinate> projects() {
		List<ArcProjectCoordinate> listArcProjectCoordinate = layoutService.selectAllProject();
		return listArcProjectCoordinate;
	}

	@GetMapping(value = "/perBaseInfos/{projectNo}")
	public Map<String, Object> perBaseInfos(@PathVariable String projectNo) {
		List<PerBaseInfo> perBaseInfos = layoutService.selectPerBaseInfoByProjectNo(projectNo);
		Map<String, Object> paramterMap = new HashMap<>();
		paramterMap.put("code", "0");
		paramterMap.put("msg", "查询成功");
		paramterMap.put("count", perBaseInfos.size());
		paramterMap.put("data", perBaseInfos);
		return paramterMap;
	}
}
