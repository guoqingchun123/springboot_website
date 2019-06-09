package com.bestvike.website.dao;

import com.bestvike.website.data.ArcProjectCoordinate;
import com.bestvike.website.data.ArcProjectInfo;
import com.bestvike.website.data.PerBaseInfo;
import java.util.List;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ArcProjectCoordinateDao extends Mapper<ArcProjectCoordinate> {

	public List<ArcProjectCoordinate> selectAllProject();

	public ArcProjectInfo selectProjectByProjectNo(String projectNo);

	public List<PerBaseInfo> selectPerBaseInfoByProjectNo(String projectNo);
}