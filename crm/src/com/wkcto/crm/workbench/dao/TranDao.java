package com.wkcto.crm.workbench.dao;

import java.util.List;
import java.util.Map;

import com.wkcto.crm.workbench.domain.Tran;

public interface TranDao {

	/**
	 * 保存交易
	 * @param tran
	 */
	void save(Tran tran);

	int save1(Tran t);

	List<Tran> list();

	Tran detail(String id);

	int changeStage(Tran t);

	int getTotal();

	List<Map<String, Object>> getChart();

}
