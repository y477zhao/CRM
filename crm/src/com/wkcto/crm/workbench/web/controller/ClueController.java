package com.wkcto.crm.workbench.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wkcto.crm.settings.domain.User;
import com.wkcto.crm.settings.service.UserService;
import com.wkcto.crm.settings.service.impl.UserServiceImpl;
import com.wkcto.crm.utils.Const;
import com.wkcto.crm.utils.DateUtil;
import com.wkcto.crm.utils.OutJson;
import com.wkcto.crm.utils.TransactionHandler;
import com.wkcto.crm.utils.UUIDGenerator;
import com.wkcto.crm.workbench.domain.Activity;
import com.wkcto.crm.workbench.domain.Clue;
import com.wkcto.crm.workbench.domain.Tran;
import com.wkcto.crm.workbench.service.ActivityService;
import com.wkcto.crm.workbench.service.ClueActivityRelationService;
import com.wkcto.crm.workbench.service.ClueService;
import com.wkcto.crm.workbench.service.impl.ActivityServiceImpl;
import com.wkcto.crm.workbench.service.impl.ClueActivityRelationServiceImpl;
import com.wkcto.crm.workbench.service.impl.ClueServiceImpl;

public class ClueController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if ("/workbench/clue/create.do".equals(servletPath)) {
			doCreate(request, response);
		} else if ("/workbench/clue/save.do".equals(servletPath)) {
			doSave(request, response);
		} else if ("/workbench/clue/detail.do".equals(servletPath)) {
			doDetail(request, response);
		} else if ("/workbench/clue/listActivity.do".equals(servletPath)) {
			doListActivity(request, response);
		} else if ("/workbench/clue/unbund.do".equals(servletPath)) {
			doUnbund(request, response);
		} else if ("/workbench/clue/getActivityByClueIdAndName.do".equals(servletPath)) {
			doGetActivityByClueIdAndName(request, response);
		} else if ("/workbench/clue/bund.do".equals(servletPath)) {
			doBund(request, response);
		} else if ("/workbench/clue/convert.do".equals(servletPath)) {
			doConvert(request, response);
		}
	}

	protected void doConvert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String clueId = request.getParameter("id");
		String flag = request.getParameter("flag");
		Tran tran = null;
		if("1".equals(flag)){
			String money = request.getParameter("money");
			String name = request.getParameter("name");
			String expectedDate = request.getParameter("expectedDate");
			String stage = request.getParameter("stage");
			String activityId = request.getParameter("activityId");
			// 创建交易
			tran = new Tran();
			tran.setMoney(money);
			tran.setName(name);
			tran.setExpectedDate(expectedDate);
			tran.setStage(stage);
			tran.setActivityId(activityId);
		}
		ClueService clueService = (ClueService)new TransactionHandler(new ClueServiceImpl()).getProxy();
		String createBy = ((User)request.getSession().getAttribute(Const.SESSION_USER)).getLoginAct();
		try {
			clueService.convert(clueId, createBy, tran);
			// 成功了
			response.sendRedirect(request.getContextPath() + "/workbench/clue/index.jsp");
		} catch (Exception e) {
			// 失败了
			e.printStackTrace();
		}
	}
	
	protected void doBund(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String clueId = request.getParameter("clueId");
		String[] activityIds = request.getParameterValues("activityId");
		// 保存关系
		ClueActivityRelationService cars = (ClueActivityRelationService) new TransactionHandler(
				new ClueActivityRelationServiceImpl()).getProxy();
		OutJson.print(response, cars.saves(clueId, activityIds));
	}
	
	protected void doGetActivityByClueIdAndName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String activityName = request.getParameter("activityName");
		String clueId = request.getParameter("clueId");
		ActivityService as = (ActivityService)new TransactionHandler(new ActivityServiceImpl()).getProxy();
		List<Activity> activityList = as.getByNameAndClueId(activityName, clueId);
		OutJson.print(response, activityList);
	}
	
	protected void doUnbund(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ClueActivityRelationService cars = (ClueActivityRelationService) new TransactionHandler(
				new ClueActivityRelationServiceImpl()).getProxy();
		OutJson.print(response, cars.deleteById(id));
	}

	protected void doListActivity(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String clueId = request.getParameter("clueId");
		ClueActivityRelationService cars = (ClueActivityRelationService) new TransactionHandler(
				new ClueActivityRelationServiceImpl()).getProxy();
		List<Map<String, String>> activityList = cars.getActivityByClueId(clueId);
		OutJson.print(response, activityList);
	}

	protected void doDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ClueService clueService = (ClueService) new TransactionHandler(new ClueServiceImpl()).getProxy();
		Clue clue = clueService.getById(id);
		request.setAttribute("clue", clue);
		request.getRequestDispatcher("/workbench/clue/detail.jsp").forward(request, response);
	}

	protected void doSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = UUIDGenerator.generate();
		String owner = request.getParameter("owner");
		String company = request.getParameter("company");
		String appellation = request.getParameter("appellation");
		String fullname = request.getParameter("fullname");
		String job = request.getParameter("job");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String website = request.getParameter("website");
		String mphone = request.getParameter("mphone");
		String state = request.getParameter("state");
		String source = request.getParameter("source");
		String description = request.getParameter("description");
		String contactSummary = request.getParameter("contactSummary");
		String nextContactTime = request.getParameter("nextContactTime");
		String address = request.getParameter("address");
		String createBy = ((User) request.getSession().getAttribute(Const.SESSION_USER)).getLoginAct();
		String createTime = DateUtil.getSysTime();

		Clue clue = new Clue();
		clue.setId(id);
		clue.setOwner(owner);
		clue.setCompany(company);
		clue.setAppellation(appellation);
		clue.setFullname(fullname);
		clue.setJob(job);
		clue.setEmail(email);
		clue.setPhone(phone);
		clue.setWebsite(website);
		clue.setMphone(mphone);
		clue.setState(state);
		clue.setSource(source);
		clue.setDescription(description);
		clue.setContactSummary(contactSummary);
		clue.setNextContactTime(nextContactTime);
		clue.setAddress(address);
		clue.setCreateBy(createBy);
		clue.setCreateTime(createTime);

		ClueService clueService = (ClueService) new TransactionHandler(new ClueServiceImpl()).getProxy();
		OutJson.print(response, clueService.save(clue));
	}

	protected void doCreate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = (UserService) new TransactionHandler(new UserServiceImpl()).getProxy();
		List<User> userList = userService.getAll();
		OutJson.print(response, userList);
	}
}
