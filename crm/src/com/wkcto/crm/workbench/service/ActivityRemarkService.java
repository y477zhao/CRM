package com.wkcto.crm.workbench.service;

import java.util.List;

import com.wkcto.crm.workbench.domain.Remark;

public interface ActivityRemarkService {

	/**
	 * 根据市场活动id获取关联的备注信息。
	 * @param activityId
	 * @return
	 */
	List<Remark> getByActivityId(String activityId);

	/**
	 * 根据备注id删除备注信息。
	 * @param id
	 * @return
	 */
	boolean deleteById(String id);

	/**
	 * 保存备注
	 * @param ar
	 * @return
	 */
	boolean save(Remark ar);

	/**
	 * 更新备注
	 * @param ar
	 * @return
	 */
	boolean update(Remark ar);

}
