package com.wkcto.crm.settings.service;

import java.util.List;
import java.util.Map;

import com.wkcto.crm.settings.domain.DicValue;

public interface DicValueService {

	/**
	 * 根据字典类型编码和字典值检查字典值的唯一性。
	 * @param typeCode
	 * @param value
	 * @return
	 */
	boolean checkValueUnique(String typeCode, String value);

	/**
	 * 
	 * @param dv
	 * @return
	 */
	boolean save(DicValue dv);

	/**
	 * 获取所有的字典值
	 * @return
	 */
	Map<String, List<DicValue>> getAll();

}
