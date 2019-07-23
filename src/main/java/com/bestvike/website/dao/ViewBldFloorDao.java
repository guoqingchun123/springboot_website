package com.bestvike.website.dao;

import com.bestvike.website.data.ViewBldFloor;
import com.bestvike.website.data.ViewRegBld;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface ViewBldFloorDao extends Mapper<ViewBldFloor> {
    List<ViewBldFloor> selectBldFloors(Map<String, Object> parameters);

}