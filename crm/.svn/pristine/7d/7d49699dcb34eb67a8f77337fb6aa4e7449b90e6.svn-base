package com.wkcto.crm.workbench.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wkcto.crm.utils.SqlSessionUtil;
import com.wkcto.crm.utils.UUIDGenerator;
import com.wkcto.crm.workbench.dao.ClueActivityRelationDao;
import com.wkcto.crm.workbench.domain.ClueActivityRelation;
import com.wkcto.crm.workbench.service.ClueActivityRelationService;

public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {

	private ClueActivityRelationDao card = SqlSessionUtil.getCurrentSqlSession()
			.getMapper(ClueActivityRelationDao.class);

	@Override
	public List<Map<String, String>> getActivityByClueId(String clueId) {
		return card.getActivityByClueId(clueId);
	}

	@Override
	public boolean deleteById(String id) {
		return card.deleteById(id) == 1;
	}

	@Override
	public boolean saves(String clueId, String[] activityIds) {
		List<ClueActivityRelation> carList = new ArrayList<>();
		for(String activityId : activityIds){
			// 创建一个关系对象
			ClueActivityRelation car = new ClueActivityRelation();
			car.setId(UUIDGenerator.generate());
			car.setClueId(clueId);
			car.setActivityId(activityId);
			carList.add(car);
		}
		return card.saves(carList) == carList.size();
	}

}


















