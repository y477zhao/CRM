package com.wkcto.crm.workbench.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wkcto.crm.settings.domain.User;
import com.wkcto.crm.settings.service.UserService;
import com.wkcto.crm.settings.service.impl.UserServiceImpl;
import com.wkcto.crm.utils.Const;
import com.wkcto.crm.utils.DateUtil;
import com.wkcto.crm.utils.ExcelReader;
import com.wkcto.crm.utils.ExcelWriter;
import com.wkcto.crm.utils.OutJson;
import com.wkcto.crm.utils.TransactionHandler;
import com.wkcto.crm.utils.UUIDGenerator;
import com.wkcto.crm.vo.PaginationVO;
import com.wkcto.crm.workbench.domain.Activity;
import com.wkcto.crm.workbench.domain.Remark;
import com.wkcto.crm.workbench.service.ActivityRemarkService;
import com.wkcto.crm.workbench.service.ActivityService;
import com.wkcto.crm.workbench.service.impl.ActivityRemarkServiceImpl;
import com.wkcto.crm.workbench.service.impl.ActivityServiceImpl;

public class ActivityController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if ("/workbench/activity/create.do".equals(servletPath)) {
			doCreate(request, response);
		} else if ("/workbench/activity/save.do".equals(servletPath)) {
			doSave(request, response);
		} else if ("/workbench/activity/page.do".equals(servletPath)) {
			doPage(request, response);
		} else if ("/workbench/activity/delete.do".equals(servletPath)) {
			doDel(request, response);
		} else if ("/workbench/activity/edit.do".equals(servletPath)) {
			doEdit(request, response);
		} else if ("/workbench/activity/update.do".equals(servletPath)) {
			doUpdate(request, response);
		} else if ("/workbench/activity/detail.do".equals(servletPath)) {
			doDetail(request, response);
		} else if ("/workbench/activity/listRemark.do".equals(servletPath)) {
			doListRemark(request, response);
		} else if ("/workbench/activity/delRemark.do".equals(servletPath)) {
			doDelRemark(request, response);
		} else if ("/workbench/activity/saveRemark.do".equals(servletPath)) {
			doSaveRemark(request, response);
		} else if ("/workbench/activity/updateRemark.do".equals(servletPath)) {
			doUpdateRemark(request, response);
		} else if ("/workbench/activity/exportAll.do".equals(servletPath)) {
			doExportAll(request, response);
		} else if ("/workbench/activity/exportChked.do".equals(servletPath)) {
			doExportChked(request, response);
		} else if ("/workbench/activity/import.do".equals(servletPath)) {
			doImport(request, response);
		}
	}

	protected void doImport(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 第一步：apache commons-fileupload文件上传
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD);
		factory.setRepository(new File(this.getServletContext().getRealPath("tmp")));
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		String excelPath = null;
		try {
			List<FileItem> fileItems = fileUpload.parseRequest(request);
			FileItem fileItem = fileItems.get(0);
			excelPath = this.getServletContext().getRealPath("files") + "/" + fileItem.getName();
			fileItem.write(new File(excelPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 第二步：apache POI读取excel文件获取List集合
		ExcelReader<Activity> er = new ExcelReader<>();
		List<Activity> activityList = er.getDataListFromExcel(Activity.class, excelPath);
		
		// 第三步：保存数据(mybatis)
		ActivityService as = (ActivityService) new TransactionHandler(new ActivityServiceImpl()).getProxy();
		OutJson.print(response, as.saves(activityList));
	}
	
	protected void doExportChked(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置响应的内容类型
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition",
				"attachment;filename=activity-" + System.currentTimeMillis() + ".xlsx");
		
		// 获取工作簿对象
		String[] ids = request.getParameterValues("id");
		ActivityService as = (ActivityService) new TransactionHandler(new ActivityServiceImpl()).getProxy();
		List<Activity> activityList = as.getByIds(ids);
		ExcelWriter<Activity> ew = new ExcelWriter<>();
		XSSFWorkbook workbook = ew.getWorkbook(activityList, "市场活动", Activity.class);

		// 将excel文件响应到浏览器客户端。
		workbook.write(response.getOutputStream());
	}
	
	protected void doExportAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置响应的内容类型
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition",
				"attachment;filename=activity-" + System.currentTimeMillis() + ".xlsx");

		// 获取工作簿对象
		ActivityService as = (ActivityService) new TransactionHandler(new ActivityServiceImpl()).getProxy();
		List<Activity> activityList = as.getAll();
		ExcelWriter<Activity> ew = new ExcelWriter<>();
		XSSFWorkbook workbook = ew.getWorkbook(activityList, "市场活动", Activity.class);

		// 将excel文件响应到浏览器客户端。
		workbook.write(response.getOutputStream());
	}

	protected void doUpdateRemark(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String noteContent = request.getParameter("noteContent");
		String editBy = ((User) request.getSession().getAttribute(Const.SESSION_USER)).getLoginAct();
		String editTime = DateUtil.getSysTime();
		String editFlag = "1";

		Remark ar = new Remark();
		ar.setId(id);
		ar.setNoteContent(noteContent);
		ar.setEditBy(editBy);
		ar.setEditTime(editTime);
		ar.setEditFlag(editFlag);

		ActivityRemarkService ars = (ActivityRemarkService) new TransactionHandler(new ActivityRemarkServiceImpl())
				.getProxy();
		boolean ok = ars.update(ar);

		Map<String, Object> jsonMap = new HashMap<>();
		if (ok) {
			jsonMap.put("success", true);
			jsonMap.put("activityRemark", ar);
		} else {
			jsonMap.put("success", false);
		}
		OutJson.print(response, jsonMap);
	}

	protected void doSaveRemark(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = UUIDGenerator.generate();
		String noteContent = request.getParameter("noteContent");
		String activityId = request.getParameter("activityId");
		String createBy = ((User) request.getSession().getAttribute(Const.SESSION_USER)).getLoginAct();
		String createTime = DateUtil.getSysTime();
		String editFlag = "0";

		Remark ar = new Remark();
		ar.setId(id);
		ar.setNoteContent(noteContent);
		ar.setActivityId(activityId);
		ar.setCreateBy(createBy);
		ar.setEditFlag(editFlag);
		ar.setCreateTime(createTime);

		ActivityRemarkService ars = (ActivityRemarkService) new TransactionHandler(new ActivityRemarkServiceImpl())
				.getProxy();
		boolean saveSuccess = ars.save(ar);

		Map<String, Object> jsonMap = new HashMap<>();
		if (saveSuccess) {
			jsonMap.put("success", true);
			jsonMap.put("activityRemark", ar);
		} else {
			jsonMap.put("success", false);
		}
		OutJson.print(response, jsonMap);
	}

	protected void doDelRemark(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ActivityRemarkService ars = (ActivityRemarkService) new TransactionHandler(new ActivityRemarkServiceImpl())
				.getProxy();
		OutJson.print(response, ars.deleteById(id));
	}

	protected void doListRemark(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String activityId = request.getParameter("id");
		ActivityRemarkService ars = (ActivityRemarkService) new TransactionHandler(new ActivityRemarkServiceImpl())
				.getProxy();
		List<Remark> arList = ars.getByActivityId(activityId);
		OutJson.print(response, arList);
	}

	protected void doDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ActivityService as = (ActivityService) new TransactionHandler(new ActivityServiceImpl()).getProxy();
		Activity activity = as.getById2(id);
		request.setAttribute("activity", activity);
		request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request, response);
	}

	protected void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String owner = request.getParameter("owner");
		String name = request.getParameter("name");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String cost = request.getParameter("cost");
		String description = request.getParameter("description");
		String editBy = ((User) request.getSession().getAttribute(Const.SESSION_USER)).getLoginAct();
		String editTime = DateUtil.getSysTime();

		Activity activity = new Activity();
		activity.setId(id);
		activity.setOwner(owner);
		activity.setName(name);
		activity.setStartDate(startDate);
		activity.setEndDate(endDate);
		activity.setCost(cost);
		activity.setDescription(description);
		activity.setEditBy(editBy);
		activity.setEditTime(editTime);

		ActivityService as = (ActivityService) new TransactionHandler(new ActivityServiceImpl()).getProxy();
		OutJson.print(response, as.update(activity));
	}

	protected void doEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ActivityService as = (ActivityService) new TransactionHandler(new ActivityServiceImpl()).getProxy();
		Map<String, Object> jsonMap = as.getById(id);
		OutJson.print(response, jsonMap);
	}

	protected void doDel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 市场活动id
		String[] ids = request.getParameterValues("id");
		// 调用service删除
		ActivityService as = (ActivityService) new TransactionHandler(new ActivityServiceImpl()).getProxy();
		OutJson.print(response, as.deleteByIds(ids));
	}

	protected void doPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取查询条件
		String name = request.getParameter("name");
		String owner = request.getParameter("owner");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
		Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
		// 封装到Map集合
		Map<String, Object> conditionMap = new HashMap<>();
		conditionMap.put("name1", name);
		conditionMap.put("owner1", owner);
		conditionMap.put("startDate1", startDate);
		conditionMap.put("endDate1", endDate);
		conditionMap.put("startIndex1", (pageNo - 1) * pageSize);
		conditionMap.put("pageSize1", pageSize);
		// 调用service，返回Map数据
		ActivityService as = (ActivityService) new TransactionHandler(new ActivityServiceImpl()).getProxy();
		PaginationVO<Activity> pageVO = as.getPageByCondition(conditionMap);
		// 响应JSON
		OutJson.print(response, pageVO);
	}

	protected void doSave(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = UUIDGenerator.generate();
		String owner = request.getParameter("owner");
		String name = request.getParameter("name");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String cost = request.getParameter("cost");
		String description = request.getParameter("description");
		String createBy = ((User) request.getSession().getAttribute(Const.SESSION_USER)).getLoginAct();
		String createTime = DateUtil.getSysTime();

		Activity activity = new Activity();
		activity.setId(id);
		activity.setOwner(owner);
		activity.setName(name);
		activity.setStartDate(startDate);
		activity.setEndDate(endDate);
		activity.setCost(cost);
		activity.setDescription(description);
		activity.setCreateBy(createBy);
		activity.setCreateTime(createTime);

		ActivityService as = (ActivityService) new TransactionHandler(new ActivityServiceImpl()).getProxy();
		OutJson.print(response, as.save(activity));

	}

	protected void doCreate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = (UserService) new TransactionHandler(new UserServiceImpl()).getProxy();
		List<User> userList = userService.getAll();
		OutJson.print(response, userList);
	}
}
