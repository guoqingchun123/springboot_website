package com.bestvike.website.dao;

import com.bestvike.website.data.SysUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface SysUserDao extends Mapper<SysUser> {
}
