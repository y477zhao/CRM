package com.servasoft.crm.settings.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.servasoft.crm.settings.domain.DicType;
import com.servasoft.crm.settings.service.DicTypeService;
import com.servasoft.crm.settings.service.impl.DicTypeServiceImpl;
import com.servasoft.crm.utils.TransactionHandler;

public class DicTypeController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if ("/settings/dictionary/type/checkCodeUnique.do".equals(servletPath)) {
			doCheckCodeUnique(request, response);
		} else if ("/settings/dictionary/type/save.do".equals(servletPath)) {
			doSave(request, response);
		} else if ("/settings/dictionary/type/list.do".equals(servletPath)) {
			doListAll(request, response);
		} else if ("/settings/dictionary/type/delete.do".equals(servletPath)) {
			doDeleteByCodes(request, response);//doDelete为HttpServlet中已存在的方法
		}

	}

	private void doDeleteByCodes(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String[] codes = request.getParameterValues("code");
		System.out.println(codes);
		DicTypeService dts = (DicTypeService)new TransactionHandler(new DicTypeServiceImpl()).getProxy();
		boolean ok = dts.deleteByCodes(codes);
		System.out.println(ok);

		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("success", ok);

		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(jsonMap);

		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().print(json);
		
	}
	
	private void doListAll(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		DicTypeService dts = (DicTypeService)new TransactionHandler(new DicTypeServiceImpl()).getProxy();
		Map<String, List<DicType>> jsonMap = new HashMap<>();
		jsonMap.put("dataList", dts.listAll());
		
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(jsonMap);
		System.out.println(json);
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().print(json);
		
		
	}
	private void doSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		//System.out.println(code);

		DicType dt = new DicType();
		dt.setCode(code);
		dt.setName(name);
		dt.setDescription(description);

		DicTypeService dts = (DicTypeService) new TransactionHandler(new DicTypeServiceImpl()).getProxy();
		boolean ok = dts.save(dt);
		if (ok) {
			response.sendRedirect(request.getContextPath() + "/settings/dictionary/type/index.jsp");
		}

	}

	private void doCheckCodeUnique(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收从save.jsp传过来的参数
		String code = request.getParameter("code");

		// 调用service
		// 一个服务(一张表)对应一个service
		DicTypeService dts = (DicTypeService) new TransactionHandler(new DicTypeServiceImpl()).getProxy();
		boolean ok = dts.checkCodeUnique(code);

		// 以下代码响应Json
		// ------------------------------------------------------
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("success", ok);

		// 使用jackson插件将map转换成json格式的String
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(jsonMap);

		// 设置响应内容类型为text/json之后，前端的：dataType : "json"可以省略。
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().print(json);
		// ------------------------------------------------------------

	}

}
