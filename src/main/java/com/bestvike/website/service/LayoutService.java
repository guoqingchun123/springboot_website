package com.bestvike.website.service;

import com.bestvike.website.data.ViewPresalecard;
import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.entity.SimpleRegion;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface LayoutService {

	public List<SimpleRegion> selectAllRegions(String order);

	public List<SimpleRegion> selectRegionByKeywords(String keywords);

	public List<ViewPresalecard> selectPerBaseInfoByProjectNo(String projectNo);
}