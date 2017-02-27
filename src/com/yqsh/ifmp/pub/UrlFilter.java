package com.yqsh.ifmp.pub;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.yqsh.ifmp.util.IPUtils;

public class UrlFilter implements Filter {

	private static final Logger log = Logger.getLogger(UrlFilter.class);
	
	private String checkIp = "";
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String names = headerNames.nextElement();
			String header = request.getHeader(names);
			log.debug("------" + names +"->" + header+"------");
		}
		int contentlength = request.getContentLength();
		if (contentlength > 0) {
			chain.doFilter(req, res);
		}else{
			log.warn("提交数据长度为：" + contentlength);
			response.sendRedirect("/");
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
}
