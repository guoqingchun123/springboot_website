package com.bestvike.website.service;

import com.bestvike.website.data.ViewRegionInfo;

import java.util.List;

public interface VideoService {
    List<ViewRegionInfo> selectInitVideo();
    List<String> fetchVideoNo(String regionId);
}
