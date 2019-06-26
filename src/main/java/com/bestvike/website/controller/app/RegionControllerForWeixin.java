package com.bestvike.website.controller.app;

import com.bestvike.website.controller.BaseController;
import com.bestvike.website.document.Division;
import com.bestvike.website.entity.BldCells;
import com.bestvike.website.entity.DocFiles;
import com.bestvike.website.entity.FloorSummary;
import com.bestvike.website.entity.Region;
import com.bestvike.website.service.LayoutService;
import com.bestvike.website.service.ProjectService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Floor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	private LayoutService layoutService;
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

	@GetMapping(value = "/mapRegions")
	public List<Region> mapRegions(@RequestParam(required = false) String order) {
		List<Region> listRegionInfo = layoutService.selectAllRegions(order);
		return listRegionInfo;
	}

	@GetMapping(value = "/regionBldCells/{regionId}")
	public List<BldCells> regionBldCells(@PathVariable String regionId) {
		return projectService.queryRegionBldCells(regionId);
	}

	@GetMapping(value = "/houses")
	public Map<String, Object> houses(@RequestParam String projectId, @RequestParam String bldNo, @RequestParam String cellNo,
		@RequestParam int pageNo, @RequestParam int pageSize) {
		return projectService.pageHouses(projectId, bldNo, cellNo, pageNo, pageSize);
	}

	@GetMapping(value = "/cellHouses")
	public List<FloorSummary> cellHouses(@RequestParam String projectId, @RequestParam String bldNo, @RequestParam String cellNo) {
		return projectService.cellHouses(projectId, bldNo, cellNo);
	}
}