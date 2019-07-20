package com.bestvike.website.service.impl;

import com.bestvike.website.dao.ViewRegionInfoDao;
import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private ViewRegionInfoDao viewRegionInfoDao;

    @Override
    public List<ViewRegionInfo> selectInitVideo() {
        return viewRegionInfoDao.selectInitVideo();
    }

    @Override
    public List<String> fetchVideoNo(String regionId) {
        ViewRegionInfo viewRegionInfo = viewRegionInfoDao.selectByPrimaryKey(regionId);
        return Arrays.asList(viewRegionInfo.getVideoNo().split(","));
    }
}
