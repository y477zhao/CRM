package com.wkcto.crm.settings.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wkcto.crm.settings.domain.Dept;
import com.wkcto.crm.settings.domain.User;
import com.wkcto.crm.settings.service.DeptService;
import com.wkcto.crm.settings.service.UserService;
import com.wkcto.crm.settings.service.impl.DeptServiceImpl;
import com.wkcto.crm.settings.service.impl.UserServiceImpl;
import com.wkcto.crm.utils.DateUtil;
import com.wkcto.crm.utils.MD5;
import com.wkcto.crm.utils.OutJson;
import com.wkcto.crm.utils.TransactionHandler;
import com.wkcto.crm.utils.UUIDGenerator;

public class UserController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if ("/settings/qx/user/create.do".equals(servletPath)) {
			doCreate(request, response);
		} else if ("/settings/qx/user/save.do".equals(servletPath)) {
			doSave(request, response);
		}
	}

	protected void doSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = UUIDGenerator.generate();
		String loginAct = request.getParameter("loginAct");
		String name = request.getParameter("name");
		String loginPwd = MD5.get(request.getParameter("loginPwd"));
		String email = request.getParameter("email");
		String expireTime = request.getParameter("expireTime");
		String lockState = request.getParameter("lockState");
		String deptno = request.getParameter("deptno");
		String allowIps = request.getParameter("allowIps");
		// TODO 这里实现登录功能之后再实现。
		String createBy = "admin"; 
		String createTime = DateUtil.getSysTime();
		
		User user = new User();
		user.setId(id);
		user.setLoginAct(loginAct);
		user.setName(name);
		user.setLoginPwd(loginPwd);
		user.setEmail(email);
		user.setExpireTime(expireTime);
		user.setLockState(lockState);
		user.setDeptno(deptno);
		user.setAllowIps(allowIps);
		user.setCreateBy(createBy);
		user.setCreateTime(createTime);

		UserService userService = (UserService)new TransactionHandler(new UserServiceImpl()).getProxy();
		OutJson.print(response, userService.save(user));
	}

	protected void doCreate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DeptService deptService = (DeptService) new TransactionHandler(new DeptServiceImpl()).getProxy();
		List<Dept> deptList = deptService.getAll();
		OutJson.print(response, deptList);
	}
}
