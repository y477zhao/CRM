package com.wkcto.crm.workbench.dao;

import java.util.List;
import java.util.Map;

import com.wkcto.crm.workbench.domain.ClueActivityRelation;

public interface ClueActivityRelationDao {

	/**
	 * 根据线索id获取市场活动列表。
	 * @param clueId
	 * @return
	 */
	List<Map<String, String>> getActivityByClueId(String clueId);

	/**
	 * 根据id删除一条关系。
	 * @param id
	 * @return
	 */
	int deleteById(String id);

	/**
	 * 保存关系
	 * @param carList
	 * @return
	 */
	int saves(List<ClueActivityRelation> carList);

	/**
	 * 根据线索id获取关联的市场活动id
	 * @param clueId
	 * @return
	 */
	List<String> getActivityIdByClueId(String clueId);

	/**
	 * 根据线索id删除关系。
	 * @param clueId
	 */
	void deleteByClueId(String clueId);

}
