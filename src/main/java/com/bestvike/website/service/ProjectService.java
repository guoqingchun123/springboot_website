package com.bestvike.website.service;

import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.document.Division;
import com.bestvike.website.entity.BldCells;
import com.bestvike.website.entity.BldView;
import com.bestvike.website.entity.DocFiles;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
public interface ProjectService {

	public ViewRegionInfo region(String regionId);

	public ViewRegionInfo region(String regionId, String projectId, String bldNo);

	public ViewRegionInfo region(String regionId, String projectId, String bldNo, String cellNo);

	public BldView building(String regionId, String projectId, String bldNo);

	public BldView building(String regionId, String projectId, String bldNo, String cellNo);

	public ViewRegionInfo region(String regionId, String viewType);

	public ViewRegionInfo layout(String regionId);

	public ViewRegionInfo images(String regionId);

	public Map<String, Object> pageRegions(String keywords, int pageNo, int pageSize, String divisionCode, String price, String houseHold, String sort);

	public List<Division> queryDivision();

	public Map<String, Object> queryRegionSales(String regionId);

	public List<DocFiles> queryRegionDocs(String regionId, String type);

	public List<BldCells> queryRegionBldCells(String regionId);

	public List<BldCells> queryRegionBldCells(String regionId, String houseShow);

	public Map<String, Object> pageHouses(String projectId, String bldNo, String cellNo, int pageNo, int pageSize);

	public Map<String, Object> cellFloorSummary(String projectId, String bldNo, String cellNo);

	public Map<String, Object> cellFloorSummary(String projectId, String bldNo, String cellNo, String houseShow);

	public <T> T selectAppVersion(String versionId);

	public void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception;
}