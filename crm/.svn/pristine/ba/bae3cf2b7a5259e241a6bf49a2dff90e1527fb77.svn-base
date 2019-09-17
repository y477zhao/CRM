package com.wkcto.crm.workbench.service;

import java.util.List;
import java.util.Map;

import com.wkcto.crm.vo.PaginationVO;
import com.wkcto.crm.workbench.domain.Activity;

public interface ActivityService {

	/**
	 * 保存市场活动
	 * @param activity
	 * @return
	 */
	boolean save(Activity activity);

	/**
	 * 根据查询条件获取分页数据。
	 * @param conditionMap
	 * @return
	 */
	PaginationVO<Activity> getPageByCondition(Map<String, Object> conditionMap);

	/**
	 * 删除市场活动
	 * @param ids
	 * @return
	 */
	boolean deleteByIds(String[] ids);

	/**
	 * 根据市场活动id获取市场活动信息。
	 * @param id
	 * @return
	 */
	Map<String, Object> getById(String id);

	/**
	 * 更新市场活动
	 * @param activity
	 * @return
	 */
	boolean update(Activity activity);

	/**
	 * 根据市场活动id获取市场活动对象，返回的owner是name形式。
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
	 * 根据id获取市场活动列表
	 * @param ids
	 * @return
	 */
	List<Activity> getByIds(String[] ids);

	/**
	 * 保存多个市场活动
	 * @param activityList
	 * @return
	 */
	boolean saves(List<Activity> activityList);

	/**
	 * 
	 * @param activityName
	 * @param clueId
	 * @return
	 */
	List<Activity> getByNameAndClueId(String activityName, String clueId);

	List<Activity> getActivityByName(String aname);

}
