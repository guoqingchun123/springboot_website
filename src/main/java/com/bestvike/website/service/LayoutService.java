package com.bestvike.website.service;

import com.bestvike.website.data.ViewPresalecard;
import com.bestvike.website.entity.Region;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface LayoutService {

	public List<Region> selectAllRegions(String order);

	public List<Region> selectRegionByKeywords(String keywords);

	public List<ViewPresalecard> selectPerBaseInfoByProjectNo(String projectNo);
}