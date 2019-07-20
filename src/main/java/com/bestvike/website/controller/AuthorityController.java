package com.bestvike.website.controller;

import com.bestvike.commons.redis.Cache;
import com.bestvike.website.data.SUser;
import com.bestvike.website.data.ViewRegionInfo;
import com.bestvike.website.service.LayoutService;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.bestvike.website.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lihua on 2017/2/21.
 */
@Controller
public class AuthorityController extends BaseController {

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private LayoutService layoutService;
    @Autowired
    private VideoService videoService;

	@Autowired
	private Cache cache;

	/**
	 * 大数据屏数据返回
	 *
	 * @return
	 */
	@GetMapping(value = "/console/{token}")
	public ModelAndView console(@PathVariable String token) {
		ModelAndView mv = new ModelAndView();
		if (StringUtils.isEmpty(token)) {
			mv.setViewName("login");
			return mv;
		}

		if (null != cache.get(token, SUser.class)) {
			Map<String, Object> data = layoutService.selectConsoleData();
			mv.addObject("data", data);
			mv.addObject("token", token);
			mv.setViewName("console");
			return mv;
		}

		mv.setViewName("login");
		return mv;
	}

	/**
	 * 校验跳转视频监控页面
	 * @param token
	 * @return
	 */
	@GetMapping(value = "/video/{token}")
	public ModelAndView video(@PathVariable String token) {
		ModelAndView mv = new ModelAndView();
		if (StringUtils.isEmpty(token)) {
			mv.setViewName("login");
			return mv;
		}
		if (null != cache.get(token, SUser.class)) {
		    //初始查询小区信息
            List<ViewRegionInfo> list = videoService.selectInitVideo();
			mv.addObject("data", list);
			mv.addObject("token", token);
			mv.setViewName("video");
			return mv;
		}

		mv.setViewName("login");
		return mv;
	}
}
