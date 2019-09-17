package com.wkcto.crm.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wkcto.crm.utils.Const;

public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String servletPath = request.getServletPath();
		if ("/welcome.do".equals(servletPath) || Const.LOGIN_JSP_PATH.equals(servletPath)
				|| "/login.do".equals(servletPath)) {
			chain.doFilter(request, response);
		} else {
			
			// 获取当前session，获取不到则返回null，不会新建session对象。
			// HttpSession session = request.getSession(false);
			
			// 获取当前session，获取不到则新建session对象。
			// HttpSession session = request.getSession(true);
			// HttpSession session = request.getSession();
			
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute(Const.SESSION_USER) != null){
				chain.doFilter(request, response);
			} else {
				response.sendRedirect(request.getContextPath());
			}
		}

	}

}
