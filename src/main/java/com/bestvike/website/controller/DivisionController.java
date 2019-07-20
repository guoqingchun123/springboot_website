package com.bestvike.website.controller;

import com.bestvike.website.data.ViewDivisionInfo;
import com.bestvike.website.service.DivisionService;
import java.util.List;

import com.bestvike.website.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lihua on 2016/9/30.
 */
@RestController
public class DivisionController extends BaseController {

	@Autowired
	private DivisionService divisionService;
	@Autowired
	private VideoService videoService;

	@GetMapping(value = "/divisions")
	public List<ViewDivisionInfo> divisions() {
		List<ViewDivisionInfo> listViewDivisioninfo = divisionService.selectDivisions();
		return listViewDivisioninfo;
	}

	/**
	 * 根据小区编号获取监控点编号
	 * @param regionId
	 * @return
	 */
	@GetMapping(value = "/videoNos/{regionId}")
	public List<String> fetchVideoNo(@PathVariable String regionId) {
		return videoService.fetchVideoNo(regionId);
	}
}
