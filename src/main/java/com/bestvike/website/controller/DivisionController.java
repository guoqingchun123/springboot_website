package com.bestvike.website.controller;

import com.bestvike.website.data.ViewDivisionInfo;
import com.bestvike.website.service.DivisionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lihua on 2016/9/30.
 */
@RestController
public class DivisionController extends BaseController {

	@Autowired
	private DivisionService divisionService;

	@GetMapping(value = "/divisions")
	public List<ViewDivisionInfo> divisions() {
		List<ViewDivisionInfo> listViewDivisioninfo = divisionService.selectDivisions();
		return listViewDivisioninfo;
	}
}
