package com.servasoft.crm.settings.service.impl;

import java.util.List;

import com.servasoft.crm.settings.dao.DicTypeDao;
import com.servasoft.crm.settings.domain.DicType;
import com.servasoft.crm.settings.service.DicTypeService;
import com.servasoft.crm.utils.SqlSessionUtil;

public class DicTypeServiceImpl implements DicTypeService{
	private DicTypeDao dtd =  SqlSessionUtil.getCurrentSqlSession().getMapper(DicTypeDao.class);
	
	@Override
	public boolean checkCodeUnique(String code) {
		/*
		DicType dt = dtd.getByCode(code);
		if(dt == null){
			return true;
		}
		return false;
		*/
		return dtd.getByCode(code) == null;
	}

	@Override
	public boolean save(DicType dt) {
		return dtd.save(dt) == 1;
	}

	@Override
	public List<DicType> listAll() {
		return dtd.listAll();
	}

	@Override
	public boolean deleteByCodes(String[] codes) {
		return dtd.deleteByCodes(codes) == codes.length;
	}
	
}
