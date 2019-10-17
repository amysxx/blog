package com.blog.util.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.blog.util.config.PropertiesFactory;
import com.blog.util.thread.CategoryThread;
import com.blog.util.thread.HtmlThread;
import com.blog.util.thread.LinkCommitThread;
import com.blog.util.thread.TagThread;

/**
 * @desc	系统监听器
 * @author	wlh
 */
public class SystemListener implements ServletContextListener{
	
	private static ServletContext context;
	
	public static ServletContext getContext(){
		return context;
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		
	}
	
	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
		initConfig(event);
//		initThread(event);
	}
	
	private void initThread(ServletContextEvent event) {
		new Thread(new HtmlThread()).start();
		new Thread(new CategoryThread()).start();
		new Thread(new TagThread()).start();
		new Thread(new LinkCommitThread()).start();
	}
	
	private void initConfig(ServletContextEvent event) {
		// 读取主配置文件  这里可以把properties和xml配置文件统一放在一个xml中，遍历加载
		PropertiesFactory.addConfiguration("sysconfig.properties");
		PropertiesFactory.addConfiguration("log4j.properties");
	}
	
}
