package com.wkcto.crm.settings.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wkcto.crm.settings.domain.DicType;
import com.wkcto.crm.settings.domain.DicValue;
import com.wkcto.crm.settings.service.DicTypeService;
import com.wkcto.crm.settings.service.DicValueService;
import com.wkcto.crm.settings.service.impl.DicTypeServiceImpl;
import com.wkcto.crm.settings.service.impl.DicValueServiceImpl;
import com.wkcto.crm.utils.OutJson;
import com.wkcto.crm.utils.TransactionHandler;
import com.wkcto.crm.utils.UUIDGenerator;

public class DicValueController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if ("/settings/dictionary/value/create.do".equals(servletPath)) {
			doCreate(request, response);
		} else if ("/settings/dictionary/value/checkValueUnique.do".equals(servletPath)) {
			doCheckValueUnique(request, response);
		} else if ("/settings/dictionary/value/save.do".equals(servletPath)) {
			doSave(request, response);
		}
	}

	protected void doSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = UUIDGenerator.generate();
		String value = request.getParameter("value");
		String text = request.getParameter("text");
		String orderNo = request.getParameter("orderNo");
		String typeCode = request.getParameter("typeCode");
		
		DicValue dv = new DicValue();
		dv.setId(id);
		dv.setValue(value);
		dv.setText(text);
		dv.setOrderNo(orderNo);
		dv.setTypeCode(typeCode);
		
		DicValueService dvs = (DicValueService) new TransactionHandler(new DicValueServiceImpl()).getProxy();
		boolean ok = dvs.save(dv);
		
		if(ok){
			response.sendRedirect(request.getContextPath() + "/settings/dictionary/value/index.jsp");
		}
	}

	protected void doCheckValueUnique(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String typeCode = request.getParameter("typeCode");
		String value = request.getParameter("value");
		DicValueService dvs = (DicValueService) new TransactionHandler(new DicValueServiceImpl()).getProxy();
		OutJson.print(response, dvs.checkValueUnique(typeCode, value));
	}

	protected void doCreate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DicTypeService dts = (DicTypeService) new TransactionHandler(new DicTypeServiceImpl()).getProxy();
		List<DicType> dtList = dts.getAll();
		request.setAttribute("dtList", dtList);
		request.getRequestDispatcher("/settings/dictionary/value/save.jsp").forward(request, response);
	}
}
