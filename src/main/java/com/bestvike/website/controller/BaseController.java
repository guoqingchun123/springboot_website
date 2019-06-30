package com.bestvike.website.controller;

import com.bestvike.commons.support.RestError;
import com.bestvike.commons.support.RestStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lihua on 2016/8/27.
 */
public class BaseController {
	protected Log logger = LogFactory.getLog(this.getClass());

	@Value("${app.error.prefix:}")
	protected String prefix;
	@Value("${app.instance.code:}")
	protected String appCode;

	/**
	 * 其他未处理的异常
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<RestError> handleException(Exception e) {
		logger.error(e);
		logger.error(e.getCause());
		RestError restError = RestError.build(this.appCode, this.prefix, RestStatus.INTERNAL_SERVER_ERROR, RestStatus.INTERNAL_SERVER_ERROR.getCode(), "后台繁忙，请稍后再试", e.getMessage());
		return new ResponseEntity<>(restError, RestStatus.INTERNAL_SERVER_ERROR.getStatus());
	}
}
