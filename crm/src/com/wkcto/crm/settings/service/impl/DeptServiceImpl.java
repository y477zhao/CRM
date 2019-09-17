package com.wkcto.crm.settings.service.impl;

import java.util.List;

import com.wkcto.crm.settings.dao.DeptDao;
import com.wkcto.crm.settings.domain.Dept;
import com.wkcto.crm.settings.service.DeptService;
import com.wkcto.crm.utils.SqlSessionUtil;

public class DeptServiceImpl implements DeptService {

	private DeptDao dd = SqlSessionUtil.getCurrentSqlSession().getMapper(DeptDao.class);
	
	@Override
	public boolean save(Dept d) {
		return dd.save(d) == 1;
	}

	@Override
	public List<Dept> getAll() {
		return dd.getAll();
	}

}
