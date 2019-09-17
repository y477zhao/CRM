package com.wkcto.crm.settings.dao;

import java.util.List;

import com.wkcto.crm.settings.domain.DicValue;

public interface DicValueDao {

	/**
	 * 根据字典类型编码和字典值获取字典值对象。
	 * @param typeCode
	 * @param value
	 * @return
	 */
	DicValue getByTypeCodeAndValue(String typeCode, String value);

	/**
	 * 
	 * @param dv
	 * @return
	 */
	int save(DicValue dv);

	/**
	 * 根据字典类型编码获取对应的字典值列表。
	 * @param code
	 * @return
	 */
	List<DicValue> getByTypeCode(String code);

}
