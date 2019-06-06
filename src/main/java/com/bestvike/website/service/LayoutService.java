package com.bestvike.website.service;

import com.bestvike.website.data.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LayoutService {
    public List<SysUser> fetchAll();
}
