package com.servasoft.crm.settings.service;

import java.util.List;

import com.servasoft.crm.settings.domain.DicType;

public interface DicTypeService {
	/**
	 * 检查code的唯一性
	 * @param code
	 * @return true表示编码不存在可以使用；false表示编码已存在不能使用。
	 */
	boolean checkCodeUnique(String code);

	/**
	 * 保存字典类型
	 * @param dt
	 * @return true表示保存成功, false表示保存失败
	 */
	boolean save(DicType dt);

	/**
	 * 在首页展示所有的字典类型数据
	 * @return 字段类型的集合
	 */
	List<DicType> listAll();

	/**
	 * 根据编码删除指定的字典类型数据
	 * @param codes
	 * @return true表示删除成功, false表示删除失败
	 */
	boolean deleteByCodes(String[] codes);

	
	
}
