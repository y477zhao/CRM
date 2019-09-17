package com.wkcto.crm.settings.dao;

import java.util.List;

import com.wkcto.crm.settings.domain.DicType;

public interface DicTypeDao {

	/**
	 * 根据字典类型编码获取字典类型对象。
	 * @param code 编码
	 * @return 字典类型对象。
	 */
	DicType getByCode(String code);

	/**
	 * 保存字典类型
	 * @param dt
	 * @return
	 */
	int save(DicType dt);

	/**
	 * 
	 * @return
	 */
	List<DicType> getAll();

}
