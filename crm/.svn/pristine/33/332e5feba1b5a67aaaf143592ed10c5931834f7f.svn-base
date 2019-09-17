package com.wkcto.crm.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wkcto.crm.exceptions.LoginException;
import com.wkcto.crm.settings.domain.User;
import com.wkcto.crm.settings.service.UserService;
import com.wkcto.crm.settings.service.impl.UserServiceImpl;
import com.wkcto.crm.utils.Const;
import com.wkcto.crm.utils.TransactionHandler;

public class WelcomeController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 最终两个走向：工作台或者登录页
		Cookie[] cookies = request.getCookies();
		String loginAct = null;
		String loginPwd = null;
		if(cookies != null){
			for(Cookie cookie : cookies){
				if("a".equals(cookie.getName())){
					loginAct = cookie.getValue();
				} else if ("b".equals(cookie.getName())){
					loginPwd = cookie.getValue();
				}
			}
		}
		
		if(loginAct != null && loginPwd != null){
			UserService userService = (UserService)new TransactionHandler(new UserServiceImpl()).getProxy();
			try {
				User user = userService.login(loginAct, loginPwd, request.getRemoteAddr());
				// 登录成功
				request.getSession().setAttribute(Const.SESSION_USER, user);
				// 工作台
				response.sendRedirect(request.getContextPath() + Const.WORKBENCH_INDEX_PATH);
			} catch (LoginException e) {
				// 登录失败
				e.printStackTrace();
				response.sendRedirect(request.getContextPath() + Const.LOGIN_JSP_PATH);
			}
		} else {
			// 没有Cookie
			response.sendRedirect(request.getContextPath() + Const.LOGIN_JSP_PATH);
		}
	}
	
}


















