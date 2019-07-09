package com.bestvike.website.dao;

import com.bestvike.website.data.ViewBldFloor;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ViewFloorCellDao extends Mapper<ViewBldFloor> {

}