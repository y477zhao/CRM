package com.wkcto.crm.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import com.wkcto.crm.utils.MD5;
import com.wkcto.crm.utils.OutJson;
import com.wkcto.crm.utils.TransactionHandler;

public class LoginController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 获取登录信息
		String loginAct = request.getParameter("loginAct");
		String loginPwd = MD5.get(request.getParameter("loginPwd"));
		String clientIp = request.getRemoteAddr(); // 获取客户端IP地址。
		
		Map<String,Object> jsonMap = new HashMap<>();
		try {
			// 调用service登录
			UserService userService = (UserService)new TransactionHandler(new UserServiceImpl()).getProxy();
			// 调用的是代理对象的登录方法。
			User user = userService.login(loginAct, loginPwd, clientIp);
			// 将用户绑定到session域
			request.getSession().setAttribute(Const.SESSION_USER, user);
			// 程序执行到这里表示该用户一定是登录成功了，应该继续判断他是否选择了十天内免登录
			String flag = request.getParameter("flag");
			if("1".equals(flag)){
				// 创建小饼干Cookie(aCookie存储账号，bCookie存储密码)
				Cookie cookie1 = new Cookie("a" , loginAct);
				Cookie cookie2 = new Cookie("b" , loginPwd); 
				// 设置以上两个Cookie的有效期
				cookie1.setMaxAge(60 * 60 * 24 * 10);
				cookie2.setMaxAge(60 * 60 * 24 * 10);
				// 设置Cookie的关联路径
				cookie1.setPath(request.getContextPath()); //cookie1.setPath("www.baidu.com");
				cookie2.setPath(request.getContextPath());
				// 将Cookie发送到浏览器客户端
				response.addCookie(cookie1);
				response.addCookie(cookie2);
			}
			jsonMap.put("success", true);
		} catch (LoginException e) {
			jsonMap.put("success", false);
			jsonMap.put("errorMsg", e.getMessage());
		}
		OutJson.print(response, jsonMap);
	}
	
}
