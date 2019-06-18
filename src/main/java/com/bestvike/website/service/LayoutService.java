package com.bestvike.website.service;

import com.bestvike.website.data.ViewCorpInfo;
import com.bestvike.website.data.ViewPresalecard;
import com.bestvike.website.data.ViewProjectInfo;
import com.bestvike.website.data.ViewRegionInfo;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface LayoutService {

	public List<ViewRegionInfo> selectAllRegions(String order);

	public List<ViewProjectInfo> selectProjectByKeywords(String keywords);

	public ViewProjectInfo selectProject(String projectNo);

	public List<ViewPresalecard> selectPerBaseInfoByProjectNo(String projectNo);

	public ViewCorpInfo selectCorpInfo(String corpNo);
}