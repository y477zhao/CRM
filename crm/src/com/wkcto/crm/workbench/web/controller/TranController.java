package com.wkcto.crm.workbench.web.controller;

import java.io.IOException;
import java.util.HashMap;
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
import com.wkcto.crm.utils.ServiceFactory;
import com.wkcto.crm.utils.UUIDGenerator;
import com.wkcto.crm.workbench.domain.Activity;
import com.wkcto.crm.workbench.domain.Contacts;
import com.wkcto.crm.workbench.domain.Tran;
import com.wkcto.crm.workbench.domain.TranHistory;
import com.wkcto.crm.workbench.service.ActivityService;
import com.wkcto.crm.workbench.service.ContactsService;
import com.wkcto.crm.workbench.service.CustomerService;
import com.wkcto.crm.workbench.service.TranHistoryService;
import com.wkcto.crm.workbench.service.TranService;
import com.wkcto.crm.workbench.service.impl.ActivityServiceImpl;
import com.wkcto.crm.workbench.service.impl.ContactsServiceImpl;
import com.wkcto.crm.workbench.service.impl.CustomerServiceImpl;
import com.wkcto.crm.workbench.service.impl.TranHistoryServiceImpl;
import com.wkcto.crm.workbench.service.impl.TranServiceImpl;

public class TranController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		if ("/workbench/transaction/add.do".equals(servletPath)) {
			add(request, response);
		} else if ("/workbench/transaction/getActivityByName.do".equals(servletPath)) {
			getActivityByName(request, response);
		} else if ("/workbench/transaction/getContactsByName.do".equals(servletPath)) {
			getContactsByName(request, response);
		} else if ("/workbench/transaction/getCustomerNameByName.do".equals(servletPath)) {
			getCustomerNameByName(request, response);
		} else if ("/workbench/transaction/save.do".equals(servletPath)) {
			save(request, response);
		} else if ("/workbench/transaction/list.do".equals(servletPath)) {
			list(request, response);
		} else if ("/workbench/transaction/detail.do".equals(servletPath)) {
			detail(request, response);
		} else if ("/workbench/transaction/getTranHistoryByTranId.do".equals(servletPath)) {
			getTranHistoryByTranId(request, response);
		} else if ("/workbench/transaction/changeStage.do".equals(servletPath)) {
			changeStage(request, response);
		} else if ("/workbench/transaction/getChart.do".equals(servletPath)) {
			getChart(request, response);
		}
	}

	private void getChart(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("进入到   取得统计图数据   操作");
		
		TranService ts = (TranService) ServiceFactory.getService(new TranServiceImpl());
		
		//{"total":100,"dataList":[{"value":"?","name":"?"},{},{}]}
		Map<String,Object> map = ts.getChart();
		
		OutJson.print(response,map);
		
	}

	private void changeStage(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("进入到   更改交易阶段   操作");
		
		//取得表单参数
		String tranId = request.getParameter("tranId");
		String money = request.getParameter("money");
		String expectedDate = request.getParameter("expectedDate");
		String stage = request.getParameter("stage");
		String editBy = ((User)request.getSession().getAttribute(Const.SESSION_USER)).getName();
		String editTime = DateUtil.getSysTime();
		
		Tran t = new Tran();
		t.setId(tranId);
		t.setMoney(money);
		t.setExpectedDate(expectedDate);
		t.setStage(stage);
		t.setEditBy(editBy);
		t.setEditTime(editTime);
		
		TranService ts = (TranService) ServiceFactory.getService(new TranServiceImpl());
		
		boolean flag = ts.changeStage(t);

		Map<String,Object> map = new HashMap<String,Object>();
		
		if(flag){
			
			map.put("success",true);
			map.put("stage",stage);
			map.put("editBy",editBy);
			map.put("editTime",editTime);
			
			Map<String,String> pMap = (Map<String, String>) this.getServletContext().getAttribute("pMap");
			
			map.put("possibility",pMap.get(stage));
			
		}else{
			
			map.put("success",false);
			
		}
		
		OutJson.print(response,map);
		
		
	}

	private void getTranHistoryByTranId(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("进入到   根据交易id取得该交易下对应的所有交易历史   操作");
		
		//接收交易id
		String tranId = request.getParameter("tranId");
		
		TranHistoryService ths = (TranHistoryService) ServiceFactory.getService(new TranHistoryServiceImpl());
		
		List<TranHistory> thList = ths.getTranHistoryByTranId(tranId);
		
		Map<String,String> pMap = (Map<String,String>)this.getServletContext().getAttribute("pMap");
		
		for(TranHistory th:thList){
			
			String stage = th.getStage();
			
			String possibility = pMap.get(stage);
			
			th.setPossibility(possibility);
			
		}
		
		OutJson.print(response, thList);
		
		
	}

	private void detail(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		System.out.println("进入到   根据id查询单条详细信息   操作");
		
		//接收需要展现详细信息的id
		String id = request.getParameter("id");
		
		TranService ts = (TranService) ServiceFactory.getService(new TranServiceImpl());
		Tran t = ts.detail(id);
		request.setAttribute("tran",t);
		request.getRequestDispatcher("/workbench/transaction/detail.jsp").forward(request, response);
		
	}

	private void list(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		System.out.println("进入到   查询交易列表   操作");
		
		/*
		 * 展现列表都需要：
		 * 	id
		 * 	name
		 * 	customerId
		 *  stage
		 *  type
		 *  owner
		 *  source
		 *  contactsId
		 *  
		 *  
		 *  基础表 tbl_tran
		 *  需要展现 客户名字 tbl_customer
		 *  	 所有者  tbl_user
		 *      联系人  tbl_contacts
		 *  
		 */
		
		TranService ts = (TranService) ServiceFactory.getService(new TranServiceImpl());
		List<Tran> tList = ts.list();
		
		request.setAttribute("tList",tList);
		
		request.getRequestDispatcher("/workbench/transaction/index.jsp").forward(request, response);
		
	}

	private void save(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		System.out.println("进入到   添加交易信息   操作");
		
		//接收表单数据
		String id = UUIDGenerator.generate();
		String owner = request.getParameter("owner");
		String name = request.getParameter("name");
		String money = request.getParameter("money");
		String expectedDate = request.getParameter("expectedDate");
		String customerName = request.getParameter("accountName");//传递到业务层进行处理
		String stage = request.getParameter("stage");
		String type = request.getParameter("type");
		String source = request.getParameter("source");
		String activityId = request.getParameter("activityId");
		String contactsId = request.getParameter("contactsId");
		String description = request.getParameter("description");
		String contactSummary = request.getParameter("contactSummary");
		String nextContactTime = request.getParameter("nextContactTime");
		String createBy = ((User)request.getSession().getAttribute(Const.SESSION_USER)).getName();
		String createTime = DateUtil.getSysTime();
		
		Tran t = new Tran();
		/*
		 * 注意：customerName不能set
		 * 在业务层，处理完customer相关信息后，一并处理
		 */
		/*
		 * 在实际项目开发中，
		 * 将来使用的都是框架技术
		 * 对于表现层（控制器）的框架
		 * 	struts(form)/struts2/springmvc
		 * 	使用框架技术会自动的将表单中上传的参数保存到t对象中
		 * 我们现在没有框架，只能一个一个的set进去
		 * 	
		 */
		t.setId(id);
		t.setOwner(owner);
		t.setName(name);
		t.setMoney(money);
		t.setExpectedDate(expectedDate);
		//t.setCustomerId(customerId); 	//业务层处理
		t.setStage(stage);
		t.setType(type);
		t.setSource(source);
		t.setActivityId(activityId);
		t.setContactsId(contactsId);
		t.setDescription(description);
		t.setContactSummary(contactSummary);
		t.setNextContactTime(nextContactTime);
		t.setCreateBy(createBy);
		t.setCreateTime(createTime);
		
		System.out.println(t);
		
		TranService ts = (TranService) ServiceFactory.getService(new TranServiceImpl());
		
		boolean flag = ts.save(t,customerName);
		
		if(flag){
			
			response.sendRedirect(request.getContextPath() + "/workbench/transaction/list.do");
			
		}
		
	}

	private void getCustomerNameByName(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("进入到   根据用户输入的部分客户名字查询相应的所有客户名字   操作");
		
		//接收客户输入的部分的名字
		String name = request.getParameter("name");
		
		CustomerService cs = (CustomerService) ServiceFactory.getService(new CustomerServiceImpl());
		
		List<String> sList = cs.getCustomerNameByName(name);
		
		OutJson.print(response, sList);
		
	}

	private void getContactsByName(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("进入到   根据名称模糊查询联系人   操作");
		
		String cname = request.getParameter("cname");
		
		ContactsService cs = (ContactsService) ServiceFactory.getService(new ContactsServiceImpl());
		
		List<Contacts> cList = cs.getContactsByName(cname);
		
		OutJson.print(response, cList);
		
	}

	private void getActivityByName(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("进入到   根据名称模糊查询市场活动   操作");
		
		String aname = request.getParameter("aname");
		
		ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
		
		List<Activity> aList = as.getActivityByName(aname);
		
		OutJson.print(response, aList);
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		
		System.out.println("进入到   跳转到交易添加页   操作");
		
		UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
		
		List<User> uList = us.getAll();
		
		request.setAttribute("uList",uList);
		
		request.getRequestDispatcher("/workbench/transaction/save.jsp").forward(request, response);
		
		
	}

}



























