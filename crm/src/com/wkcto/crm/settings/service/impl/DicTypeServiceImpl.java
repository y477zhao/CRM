package com.wkcto.crm.settings.service.impl;

import java.util.List;

import com.wkcto.crm.settings.dao.DicTypeDao;
import com.wkcto.crm.settings.domain.DicType;
import com.wkcto.crm.settings.service.DicTypeService;
import com.wkcto.crm.utils.SqlSessionUtil;

public class DicTypeServiceImpl implements DicTypeService {

	private DicTypeDao dtd = SqlSessionUtil.getCurrentSqlSession().getMapper(DicTypeDao.class);
	
	@Override
	public boolean checkCodeUnique(String code) {
		return dtd.getByCode(code) == null;
		/*
		DicType dt = dtd.getByCode(code);
		if(dt == null){
			return true;
		}
		return false;
		*/
	}

	@Override
	public boolean save(DicType dt) {
		return dtd.save(dt) == 1;
	}

	@Override
	public List<DicType> getAll() {
		return dtd.getAll();
	}

}
