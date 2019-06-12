package com.bestvike.website.service;

import com.bestvike.website.data.ArcCorpInfo;
import com.bestvike.website.data.ArcProjectCoordinate;
import com.bestvike.website.data.ArcProjectInfo;
import com.bestvike.website.data.PerBaseInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LayoutService {

	public List<ArcProjectCoordinate> selectAllProject();

	public List<ArcProjectCoordinate> selectProjectByKeywords(String keywords);

	public ArcProjectInfo selectProject(String projectNo);

	public List<PerBaseInfo> selectPerBaseInfoByProjectNo(String projectNo);

	public ArcCorpInfo selectCorpInfo(String corpNo);
}