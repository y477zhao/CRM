package com.wkcto.crm.settings.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wkcto.crm.settings.domain.Dept;
import com.wkcto.crm.settings.service.DeptService;
import com.wkcto.crm.settings.service.impl.DeptServiceImpl;
import com.wkcto.crm.utils.OutJson;
import com.wkcto.crm.utils.TransactionHandler;

public class DeptController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if ("/settings/dept/save.do".equals(servletPath)) {
			doSave(request, response);
		}
	}

	protected void doSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String manager = request.getParameter("manager");
		String phone = request.getParameter("phone");
		String description = request.getParameter("description");
		Dept d = new Dept();
		d.setNo(no);
		d.setName(name);
		d.setManager(manager);
		d.setPhone(phone);
		d.setDescription(description);
		DeptService ds = (DeptService)new TransactionHandler(new DeptServiceImpl()).getProxy();
		OutJson.print(response, ds.save(d));
	}
}




















