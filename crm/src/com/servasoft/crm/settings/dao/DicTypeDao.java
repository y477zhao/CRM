package com.servasoft.crm.settings.dao;

import java.util.List;

import com.servasoft.crm.settings.domain.DicType;

public interface DicTypeDao {

	/**
	 * 根据字典类型编码获取字典类型对象
	 * @param code
	 * @return 字典类型编码对象
	 */
	DicType getByCode(String code);

	int save(DicType dt);

	List<DicType> listAll();

	int deleteByCodes(String[] codes);

}
