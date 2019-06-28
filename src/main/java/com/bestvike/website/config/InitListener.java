package com.bestvike.website.config;

import java.util.Map;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebListener
public class InitListener implements ServletContextListener {

	protected Log logger = LogFactory.getLog(this.getClass());

	@Value("${app.instance.code}")
	private String appCode;
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		WebApplicationContextUtils.getRequiredWebApplicationContext(sce.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
		Const.legends = mongoTemplate.findAll(Map.class, "Legends");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
