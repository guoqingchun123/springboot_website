package com.bestvike.website.dao;

import com.bestvike.website.data.ViewDivisionInfo;
import com.bestvike.website.data.ViewHouseInfo;
import java.util.List;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ViewHouseInfoDao extends Mapper<ViewHouseInfo> {

	public List<ViewDivisionInfo> selectDivisions();
}