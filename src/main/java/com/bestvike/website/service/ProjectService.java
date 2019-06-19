package com.bestvike.website.service;

import com.bestvike.website.data.ViewRegionInfo;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {

	public ViewRegionInfo region(String regionId);

	public ViewRegionInfo region(String regionId, String viewType);
}