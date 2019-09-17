package com.wkcto.crm.settings.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wkcto.crm.settings.dao.DicTypeDao;
import com.wkcto.crm.settings.dao.DicValueDao;
import com.wkcto.crm.settings.domain.DicType;
import com.wkcto.crm.settings.domain.DicValue;
import com.wkcto.crm.settings.service.DicValueService;
import com.wkcto.crm.utils.SqlSessionUtil;

public class DicValueServiceImpl implements DicValueService {

	private DicValueDao dvd = SqlSessionUtil.getCurrentSqlSession().getMapper(DicValueDao.class);
	private DicTypeDao dtd = SqlSessionUtil.getCurrentSqlSession().getMapper(DicTypeDao.class);
	
	@Override
	public boolean checkValueUnique(String typeCode, String value) {
		return dvd.getByTypeCodeAndValue(typeCode , value) == null;
	}

	@Override
	public boolean save(DicValue dv) {
		return dvd.save(dv) == 1;
	}

	@Override
	public Map<String, List<DicValue>> getAll() {
		
		// 获取所有的字典类型
		List<DicType> dtList = dtd.getAll();
		
		Map<String, List<DicValue>> dvMap = new HashMap<>();
		for(DicType dt : dtList){
			String code = dt.getCode();
			List<DicValue> dvList = dvd.getByTypeCode(code);
			dvMap.put(code + "List", dvList);
		}
		
		return dvMap;
	}

}



















