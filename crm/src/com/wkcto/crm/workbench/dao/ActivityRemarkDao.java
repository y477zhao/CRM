package com.wkcto.crm.workbench.dao;

import java.util.List;

import com.wkcto.crm.workbench.domain.Remark;

public interface ActivityRemarkDao {

	/**
	 * 根据市场活动id删除备注。
	 * @param ids
	 */
	void deleteByActivityIds(String[] ids);

	/**
	 * 根据市场活动id获取关联的备注列表
	 * @param activityId
	 * @return
	 */
	List<Remark> getByActivityId(String activityId);

	/**
	 * 
	 * @param id
	 * @return
	 */
	int deleteById(String id);

	/**
	 * 保存备注
	 * @param ar
	 * @return
	 */
	int save(Remark ar);

	/**
	 * 更新备注
	 * @param ar
	 * @return
	 */
	int update(Remark ar);

}
