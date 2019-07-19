package com.bestvike.website.controller.app;

import com.bestvike.website.controller.BaseController;
import com.bestvike.website.document.Division;
import com.bestvike.website.entity.BldCells;
import com.bestvike.website.entity.DocFiles;
import com.bestvike.website.service.ProjectService;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lihua on 2016/9/30.
 */
@RestController
@RequestMapping(value = "/api/app")
public class RegionControllerForApp extends BaseController {

	@Autowired
	private ProjectService projectService;

	@GetMapping(value = "/regions")
	public Map<String, Object> regions(@RequestParam(required = false) String keywords,
		@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam(required = false) String divisionCode,
		@RequestParam(required = false) String price, @RequestParam(required = false) String houseHold,
		@RequestParam(required = false) String sort) {
		return projectService.pageRegions(keywords, pageNo, pageSize, divisionCode, price, houseHold, sort);
	}

	@GetMapping(value = "/divisions")
	public List<Division> divisions() {
		return projectService.queryDivision();
	}

	@GetMapping(value = "/regionSales/{regionId}")
	public Map<String, Object> regionSalesData(@PathVariable String regionId) {
		return projectService.queryRegionSales(regionId);
	}

	@GetMapping(value = "/regionDocs/{regionId}")
	public List<DocFiles> regionDocs(@PathVariable String regionId, @RequestParam String type) {
		return projectService.queryRegionDocs(regionId, type);
	}

	@GetMapping(value = "/regionBldCells/{regionId}")
	public List<BldCells> regionBldCells(@PathVariable String regionId) {
		return projectService.queryRegionBldCells(regionId);
	}

	@GetMapping(value = "/regionBldCells")
	public List<BldCells> regionBldCells(@RequestParam String regionId, @RequestParam(required = false) String houseShow) {
		return projectService.queryRegionBldCells(regionId, houseShow);
	}

	@GetMapping(value = "/houses")
	public Map<String, Object> houses(@RequestParam String projectId, @RequestParam String bldNo, @RequestParam String cellNo,
		@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam(required = false) String houseShow) {
		return projectService.pageHouses(projectId, bldNo, cellNo, pageNo, pageSize, houseShow);
	}

	/**
	 * 查询楼层统计数据
	 *
	 * @param projectId
	 * @param bldNo
	 * @param cellNo
	 * @return
	 */
	@GetMapping(value = "/cellFloorSummary")
	public Map<String, Object> cellFloorSummary(@RequestParam String projectId, @RequestParam String bldNo, @RequestParam String cellNo,
		@RequestParam(required = false) String houseShow) {
		return projectService.cellFloorSummary(projectId, bldNo, cellNo, houseShow);
	}

	@GetMapping(value = "/version/{versionId}")
	public <T> T selectAppVersion(@PathVariable String versionId) {
		return projectService.selectAppVersion(versionId);
	}

	@GetMapping(value = "/download")
	public void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		projectService.download(httpServletRequest, httpServletResponse);
	}
}