package com.wkcto.crm.workbench.service;

import java.util.List;
import java.util.Map;

public interface ClueActivityRelationService {

	/**
	 * 根据线索id获取市场活动列表。
	 * @param clueId
	 * @return
	 */
	List<Map<String, String>> getActivityByClueId(String clueId);

	/**
	 * 根据id删除一条关系数据。
	 * @param id
	 * @return
	 */
	boolean deleteById(String id);

	/**
	 * 保存线索和市场活动的关系。
	 * @param clueId
	 * @param activityIds
	 * @return
	 */
	boolean saves(String clueId, String[] activityIds);

}
