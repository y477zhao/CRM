package com.wkcto.crm.workbench.dao;

import java.util.List;

import com.wkcto.crm.workbench.domain.TranHistory;

public interface TranHistoryDao {

	/**
	 * 保存交易历史
	 * @param th
	 */
	void save(TranHistory th);

	int save1(TranHistory th);

	List<TranHistory> getTranHistoryByTranId(String tranId);

}
