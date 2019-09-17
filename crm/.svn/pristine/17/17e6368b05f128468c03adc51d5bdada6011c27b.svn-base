package com.wkcto.crm.workbench.service.impl;

import java.util.List;

import com.wkcto.crm.utils.SqlSessionUtil;
import com.wkcto.crm.workbench.dao.ActivityRemarkDao;
import com.wkcto.crm.workbench.domain.Remark;
import com.wkcto.crm.workbench.service.ActivityRemarkService;

public class ActivityRemarkServiceImpl implements ActivityRemarkService {

	private ActivityRemarkDao ard = SqlSessionUtil.getCurrentSqlSession().getMapper(ActivityRemarkDao.class);
	
	@Override
	public List<Remark> getByActivityId(String activityId) {
		return ard.getByActivityId(activityId);
	}

	@Override
	public boolean deleteById(String id) {
		return ard.deleteById(id) == 1;
	}

	@Override
	public boolean save(Remark ar) {
		return ard.save(ar) == 1;
	}

	@Override
	public boolean update(Remark ar) {
		return ard.update(ar) == 1;
	}

}
