package com.bestvike.website.controller;

import com.bestvike.website.data.ArcProjectCoordinate;
import com.bestvike.website.data.PerBaseInfo;
import com.bestvike.website.data.ViewProjectinfo;
import com.bestvike.website.service.LayoutService;
import com.bestvike.website.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihua on 2016/9/30.
 */
@RestController
public class ProjectController extends BaseController {

	@Autowired
	private LayoutService layoutService;

	@Autowired
	private ProjectService projectService;

	@GetMapping(value = "/projects")
	public List<ArcProjectCoordinate> projects(@RequestParam(required = false) String order) {
		List<ArcProjectCoordinate> listArcProjectCoordinate = layoutService.selectAllProject(order);
		return listArcProjectCoordinate;
	}

	/**
	 * 查询项目详情
	 * @param projectId
	 * @return
	 */
	@GetMapping(value = "/project/{projectId}")
	public ViewProjectinfo project(@PathVariable String projectId) {
		return projectService.project(projectId);
	}

	@PostMapping(value = "/alterCoo")
	public int alterCoo(@RequestParam String projectId, @RequestParam(required = false) String submitCoo) {

		return layoutService.updateProjectByKeywords(projectId, submitCoo);
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

	@GetMapping(value = "/auto-projects")
	public Map<String, Object> autoProjects(@RequestParam String keywords) {
		logger.info("检索条件：" + keywords);
		Map<String, Object> paramterMap = new HashMap<>();
		try {
			keywords = URLDecoder.decode(keywords, "UTF-8");
			if (!StringUtils.isEmpty(keywords)) {
				keywords = "%" + keywords + "%";
				List<ArcProjectCoordinate> listArcProjectCoordinate = layoutService.selectProjectByKeywords(keywords);
				paramterMap.put("content", listArcProjectCoordinate);
				paramterMap.put("code", "0");
				paramterMap.put("type", "success");
			} else {
				List<ArcProjectCoordinate> listArcProjectCoordinate = layoutService.selectAllProject("default");
				paramterMap.put("content", listArcProjectCoordinate);
				paramterMap.put("code", "0");
				paramterMap.put("type", "success");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return paramterMap;
	}
}
