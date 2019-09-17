package com.wkcto.crm.workbench.service;

import com.wkcto.crm.workbench.domain.Clue;
import com.wkcto.crm.workbench.domain.Tran;

public interface ClueService {

	/**
	 * 保存线索
	 * @param clue
	 * @return
	 */
	boolean save(Clue clue);

	/**
	 * 根据id获取线索
	 * @param id
	 * @return
	 */
	Clue getById(String id);

	/**
	 * 线索转换
	 * @param clueId
	 * @param createBy
	 */
	void convert(String clueId, String createBy, Tran tran);

}
