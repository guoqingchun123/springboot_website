package com.bestvike.website.dao;

import com.bestvike.website.data.ViewFloorCell;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface ViewFloorCellDao extends Mapper<ViewFloorCell> {
    List<ViewFloorCell> selectFloorCells(Map<String, Object> parameters);

}