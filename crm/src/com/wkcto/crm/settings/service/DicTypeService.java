package com.wkcto.crm.settings.service;

import java.util.List;

import com.wkcto.crm.settings.domain.DicType;

public interface DicTypeService {

	/**
	 * 检查code的唯一性
	 * @param code 编码
	 * @return true表示编码不存在可以使用；false表示编码已存在不能使用。
	 */
	boolean checkCodeUnique(String code);

	/**
	 * 保存字典类型
	 * @param dt
	 * @return
	 */
	boolean save(DicType dt);

	/**
	 * 
	 * @return
	 */
	List<DicType> getAll();

}
