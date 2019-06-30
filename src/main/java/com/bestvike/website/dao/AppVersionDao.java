package com.bestvike.website.dao;

import com.bestvike.website.data.AppVersion;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AppVersionDao extends Mapper<AppVersion> {
	public AppVersion selectAppVersion(String versionId);
}