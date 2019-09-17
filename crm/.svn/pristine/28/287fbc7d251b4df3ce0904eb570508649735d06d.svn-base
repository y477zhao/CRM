package com.wkcto.crm.settings.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wkcto.crm.settings.domain.DicType;
import com.wkcto.crm.settings.service.DicTypeService;
import com.wkcto.crm.settings.service.impl.DicTypeServiceImpl;
import com.wkcto.crm.utils.OutJson;
import com.wkcto.crm.utils.TransactionHandler;

/**
 * 字典类型模块下的控制器
 * 
 * @author Administrator
 *
 */
public class DicTypeController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if ("/settings/dictionary/type/checkCodeUnique.do".equals(servletPath)) {
			doCheckCodeUnique(request, response);
		} else if ("/settings/dictionary/type/save.do".equals(servletPath)) {
			doSave(request, response);
		}
	}

	protected void doSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		DicType dt = new DicType();
		dt.setCode(code);
		dt.setName(name);
		dt.setDescription(description);
		
		DicTypeService dts = (DicTypeService) new TransactionHandler(new DicTypeServiceImpl()).getProxy();
		boolean ok = dts.save(dt);
		
		if(ok){
			response.sendRedirect(request.getContextPath() + "/settings/dictionary/type/index.jsp");
		}
	}

	protected void doCheckCodeUnique(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		DicTypeService dts = (DicTypeService) new TransactionHandler(new DicTypeServiceImpl()).getProxy();
		OutJson.print(response, dts.checkCodeUnique(code));
	}

}
