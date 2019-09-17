package com.wkcto.crm.workbench.dao;

import java.util.List;

import com.wkcto.crm.workbench.domain.Remark;

public interface ContactsRemarkDao {

	/**
	 * 保存联系人备注
	 * @param remarkList
	 */
	void saves(List<Remark> remarkList);

}
