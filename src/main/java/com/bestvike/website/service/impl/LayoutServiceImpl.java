package com.bestvike.website.service.impl;

import com.bestvike.website.dao.SysUserDao;
import com.bestvike.website.data.SysUser;
import com.bestvike.website.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayoutServiceImpl implements LayoutService {
    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public List<SysUser> fetchAll() {
        return sysUserDao.selectAll();
    }
}
