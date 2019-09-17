package com.wkcto.crm.workbench.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wkcto.crm.utils.SqlSessionUtil;
import com.wkcto.crm.utils.UUIDGenerator;
import com.wkcto.crm.workbench.dao.CustomerDao;
import com.wkcto.crm.workbench.dao.TranDao;
import com.wkcto.crm.workbench.dao.TranHistoryDao;
import com.wkcto.crm.workbench.domain.Customer;
import com.wkcto.crm.workbench.domain.Tran;
import com.wkcto.crm.workbench.domain.TranHistory;
import com.wkcto.crm.workbench.service.TranService;

public class TranServiceImpl implements TranService {
	
	private TranDao tranDao = SqlSessionUtil.getCurrentSqlSession().getMapper(TranDao.class);
	private TranHistoryDao tranHistoryDao = SqlSessionUtil.getCurrentSqlSession().getMapper(TranHistoryDao.class);
	private CustomerDao customerDao = SqlSessionUtil.getCurrentSqlSession().getMapper(CustomerDao.class);

	@Override
	public boolean save(Tran t, String customerName) {
		
		/*
		 * 先处理客户信息
		 * 才能够决定为tran表，添加的customerId是什么内容
		 */
		
		//通过客户的名字，搜索客户表，判断是新客户，还是已有客户
		//如果是新客户，tbl_customer表新增一条记录
		Customer customer = customerDao.getByName(customerName);
		
		boolean flag = true;
		
		//是新客户，需要添加记录
		if(customer==null){
			
			customer = new Customer();
			customer.setId(UUIDGenerator.generate());
			customer.setContactSummary(t.getContactSummary());
			customer.setCreateBy(t.getCreateBy());
			customer.setCreateTime(t.getCreateTime());
			customer.setName(customerName);
			customer.setNextContactTime(t.getNextContactTime());
			customer.setOwner(t.getOwner());
			
			int count = customerDao.save1(customer);
			if(count!=1){
				flag = false;
			}
			
		}
		t.setCustomerId(customer.getId());
		
		int count = tranDao.save1(t);
		if(count!=1){
			flag = false;
		}
		
		TranHistory th = new TranHistory();
		
		th.setId(UUIDGenerator.generate());
		th.setCreateBy(t.getCreateBy());
		th.setCreateTime(t.getCreateTime());
		th.setExpectedDate(t.getExpectedDate());
		th.setMoney(t.getMoney());
		th.setStage(t.getStage());
		th.setTranId(t.getId());
		
		int count1 = tranHistoryDao.save1(th);
		
		if(count1!=1){
			
			flag = false;
			
		}
		
		return flag;
	}

	@Override
	public List<Tran> list() {
		
		List<Tran> tList = tranDao.list();
		
		return tList;
	}

	@Override
	public Tran detail(String id) {
		
		Tran t = tranDao.detail(id);
		
		return t;
	}

	@Override
	public boolean changeStage(Tran t) {
		
		//更新交易
		int count1 = tranDao.changeStage(t);
		
		//添加交易历史
		TranHistory th = new TranHistory();
		th.setId(UUIDGenerator.generate());
		th.setCreateBy(t.getEditBy());
		th.setCreateTime(t.getEditTime());
		th.setExpectedDate(t.getExpectedDate());
		th.setMoney(t.getMoney());
		th.setStage(t.getStage());
		th.setTranId(t.getId());
		
		int count2 = tranHistoryDao.save1(th);
		
		boolean flag = true;
		
		if((count1+count2)!=2){
			flag = false;
		}
		
		return flag;
	}

	@Override
	public Map<String, Object> getChart() {
		
		//取得所有记录数
		int total = tranDao.getTotal();
		
		//取得按照阶段分组后的数据
		List<Map<String,Object>> mapList = tranDao.getChart();
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("total",total);
		map.put("dataList",mapList);
		
		return map;
	}

	
}

























