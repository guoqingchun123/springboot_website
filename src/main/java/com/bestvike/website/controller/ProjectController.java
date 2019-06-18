package com.bestvike.website.controller;

import com.bestvike.website.data.ViewPresalecard;
import com.bestvike.website.data.ViewProjectInfo;
import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.service.LayoutService;
import com.bestvike.website.service.ProjectService;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lihua on 2016/9/30.
 */
@RestController
public class ProjectController extends BaseController {

	@Autowired
	private LayoutService layoutService;

	@Autowired
	private ProjectService projectService;

	@GetMapping(value = "/regions")
	public List<ViewRegionInfo> regions(@RequestParam(required = false) String order) {
		List<ViewRegionInfo> listRegionInfo = layoutService.selectAllRegions(order);
		return listRegionInfo;
	}

	/**
	 * 查询项目详情
	 *
	 * @param regionId
	 * @return
	 */
	@GetMapping(value = "/region/{regionId}")
	public ViewRegionInfo project(@PathVariable String regionId) {
		return projectService.region(regionId);
	}

	@GetMapping(value = "/perBaseInfos/{projectNo}")
	public Map<String, Object> perBaseInfos(@PathVariable String projectNo) {
		List<ViewPresalecard> perBaseInfos = layoutService.selectPerBaseInfoByProjectNo(projectNo);
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
				List<ViewProjectInfo> listViewProjectInfo = layoutService.selectProjectByKeywords(keywords);
				paramterMap.put("content", listViewProjectInfo);
				paramterMap.put("code", "0");
				paramterMap.put("type", "success");
			} else {
//				List<ViewProjectInfo> listViewProjectInfo = layoutService.selectAllProject("default");
//				paramterMap.put("content", listViewProjectInfo);
				paramterMap.put("code", "0");
				paramterMap.put("type", "success");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return paramterMap;
	}
}
