package com.bestvike.website.service;

import com.bestvike.website.data.ViewProjectinfo;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {

	public ViewProjectinfo project(String projectId);
}