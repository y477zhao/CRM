package com.wkcto.crm.workbench.dao;

import java.util.List;
import java.util.Map;

import com.wkcto.crm.workbench.domain.Activity;

public interface ActivityDao {

	/**
	 * 保存市场活动
	 * @param activity
	 * @return
	 */
	int save(Activity activity);

	/**
	 * 获取“符合查询条件”的总记录条数。
	 * @param conditionMap
	 * @return
	 */
	Long getTotalByCondition(Map<String, Object> conditionMap);

	/**
	 * 获取“符合查询条件”的“当前页”数据。
	 * @param conditionMap
	 * @return
	 */
	List<Activity> getDataListByCondition(Map<String, Object> conditionMap);

	/**
	 * 根据id删除市场活动
	 * @param ids
	 * @return
	 */
	int deleteByIds(String[] ids);

	/**
	 * 根据id获取市场活动，返回的owner是id形式。
	 * @param id
	 * @return
	 */
	Activity getById(String id);

	/**
	 * 
	 * @param activity
	 * @return
	 */
	int update(Activity activity);

	/**
	 * 根据id获取市场活动，返回的owner是name形式。
	 * @param id
	 * @return
	 */
	Activity getById2(String id);

	/**
	 * 获取所有的市场活动
	 * @return
	 */
	List<Activity> getAll();

	/**
	 * 
	 * @param ids
	 * @return
	 */
	List<Activity> getByIds(String[] ids);

	/**
	 * 
	 * @param activityList
	 * @return
	 */
	int saves(List<Activity> activityList);

	/**
	 * 
	 * @param activityName
	 * @param clueId
	 * @return
	 */
	List<Activity> getByNameAndClueId(String activityName, String clueId);

	List<Activity> getActivityByName(String aname);

}
