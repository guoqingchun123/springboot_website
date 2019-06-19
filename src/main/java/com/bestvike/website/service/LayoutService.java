package com.bestvike.website.service;

import com.bestvike.website.data.ViewPresalecard;
import com.bestvike.website.data.ViewRegionInfo;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface LayoutService {

	public List<ViewRegionInfo> selectAllRegions(String order);

	public List<ViewRegionInfo> selectRegionByKeywords(String keywords);

	public List<ViewPresalecard> selectPerBaseInfoByProjectNo(String projectNo);
}