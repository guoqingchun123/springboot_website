package com.bestvike.website.service;

import com.bestvike.website.data.ViewHouseInfo;
import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.document.Division;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {

	public ViewRegionInfo region(String regionId);

	public ViewRegionInfo region(String regionId, String viewType);

	public List<ViewHouseInfo> selectCellHouse(String bldId, String cellNo);

	public Map<String, Object> pageRegions(String keywords, int pageNo, int pageSize);

	public List<Division> queryDivision();
}