package com.wkcto.crm.workbench.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wkcto.crm.settings.dao.UserDao;
import com.wkcto.crm.settings.domain.User;
import com.wkcto.crm.utils.SqlSessionUtil;
import com.wkcto.crm.vo.PaginationVO;
import com.wkcto.crm.workbench.dao.ActivityDao;
import com.wkcto.crm.workbench.dao.ActivityRemarkDao;
import com.wkcto.crm.workbench.domain.Activity;
import com.wkcto.crm.workbench.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {

	private ActivityDao ad = SqlSessionUtil.getCurrentSqlSession().getMapper(ActivityDao.class);
	private ActivityRemarkDao ard = SqlSessionUtil.getCurrentSqlSession().getMapper(ActivityRemarkDao.class);
	private UserDao userDao = SqlSessionUtil.getCurrentSqlSession().getMapper(UserDao.class);
	
	
	@Override
	public boolean save(Activity activity) {
		return ad.save(activity) == 1;
	}

	@Override
	public PaginationVO<Activity> getPageByCondition(Map<String, Object> conditionMap) {
		PaginationVO<Activity> pageVO = new PaginationVO<>();
		pageVO.setTotal(ad.getTotalByCondition(conditionMap));
		pageVO.setDataList(ad.getDataListByCondition(conditionMap));
		return pageVO;
	}

	@Override
	public boolean deleteByIds(String[] ids) {
		// 先删除备注
		ard.deleteByActivityIds(ids);
		// 再删除市场活动
		return ad.deleteByIds(ids) == ids.length;
	}

	@Override
	public Map<String, Object> getById(String id) {
		Map<String,Object> jsonMap = new HashMap<>();
		List<User> userList = userDao.getAll();
		jsonMap.put("userList", userList);
		Activity activity = ad.getById(id);
		jsonMap.put("activity", activity);
		return jsonMap;
	}

	@Override
	public boolean update(Activity activity) {
		return ad.update(activity) == 1;
	}

	@Override
	public Activity getById2(String id) {
		return ad.getById2(id);
	}

	@Override
	public List<Activity> getAll() {
		return ad.getAll();
	}

	@Override
	public List<Activity> getByIds(String[] ids) {
		return ad.getByIds(ids);
	}

	@Override
	public boolean saves(List<Activity> activityList) {
		return ad.saves(activityList) == activityList.size();
	}

	@Override
	public List<Activity> getByNameAndClueId(String activityName, String clueId) {
		return ad.getByNameAndClueId(activityName, clueId);
	}

	@Override
	public List<Activity> getActivityByName(String aname) {
		
		List<Activity> aList = ad.getActivityByName(aname);
		
		return aList;
	}

}
















