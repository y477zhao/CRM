package com.wkcto.crm.workbench.service.impl;

import java.util.List;

import com.wkcto.crm.utils.SqlSessionUtil;
import com.wkcto.crm.workbench.dao.TranHistoryDao;
import com.wkcto.crm.workbench.domain.TranHistory;
import com.wkcto.crm.workbench.service.TranHistoryService;

public class TranHistoryServiceImpl implements TranHistoryService {
	
	private TranHistoryDao tranHistoryDao = SqlSessionUtil.getCurrentSqlSession().getMapper(TranHistoryDao.class);

	@Override
	public List<TranHistory> getTranHistoryByTranId(String tranId) {
		
		List<TranHistory> thList = tranHistoryDao.getTranHistoryByTranId(tranId);
		
		return thList;
	}

	
}

























