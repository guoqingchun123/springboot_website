package com.bestvike.website.dao;

import com.bestvike.website.data.ViewDivisioninfo;
import com.bestvike.website.data.ViewHouseinfo;
import java.util.List;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ViewHouseinfoDao extends Mapper<ViewHouseinfo> {

	public List<ViewDivisioninfo> selectDivisions();
}