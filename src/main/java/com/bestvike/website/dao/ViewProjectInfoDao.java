package com.bestvike.website.dao;

import com.bestvike.website.data.ViewPresalecard;
import com.bestvike.website.data.ViewProjectInfo;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ViewProjectInfoDao extends Mapper<ViewProjectInfo> {

	public ViewProjectInfo selectProjectByProjectNo(String projectNo);
	public List<ViewPresalecard> selectPerBaseInfoByProjectNo(String projectNo);
}