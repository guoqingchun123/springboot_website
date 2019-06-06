package com.bestvike.website.controller;

import com.bestvike.website.data.SysUser;
import com.bestvike.website.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by lihua on 2016/9/30.
 */
@Controller
public class LayoutController extends BaseController {
    @Autowired
    private LayoutService layoutService;

    @GetMapping(value="/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        List<SysUser> sysUserList = layoutService.fetchAll();
        mv.addObject("users", sysUserList);
        mv.setViewName("index");
        return mv;
    }
}
