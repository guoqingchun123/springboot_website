package com.bestvike.website.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public final class RequestUtil {

	public RequestUtil() {
	}

	public static Map<String, String> headers(HttpServletRequest request) {
		Map<String, String> map = new HashMap();
		Enumeration headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;
	}
}
