package com.wkcto.crm.workbench.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.wkcto.crm.utils.DateUtil;
import com.wkcto.crm.utils.SqlSessionUtil;
import com.wkcto.crm.utils.UUIDGenerator;
import com.wkcto.crm.workbench.dao.ClueActivityRelationDao;
import com.wkcto.crm.workbench.dao.ClueDao;
import com.wkcto.crm.workbench.dao.ClueRemarkDao;
import com.wkcto.crm.workbench.dao.ContactsActivityRelationDao;
import com.wkcto.crm.workbench.dao.ContactsDao;
import com.wkcto.crm.workbench.dao.ContactsRemarkDao;
import com.wkcto.crm.workbench.dao.CustomerDao;
import com.wkcto.crm.workbench.dao.CustomerRemarkDao;
import com.wkcto.crm.workbench.dao.TranDao;
import com.wkcto.crm.workbench.dao.TranHistoryDao;
import com.wkcto.crm.workbench.dao.TranRemarkDao;
import com.wkcto.crm.workbench.domain.Clue;
import com.wkcto.crm.workbench.domain.Contacts;
import com.wkcto.crm.workbench.domain.ContactsActivityRelation;
import com.wkcto.crm.workbench.domain.Customer;
import com.wkcto.crm.workbench.domain.Remark;
import com.wkcto.crm.workbench.domain.Tran;
import com.wkcto.crm.workbench.domain.TranHistory;
import com.wkcto.crm.workbench.service.ClueService;

public class ClueServiceImpl implements ClueService {

	private ClueDao clueDao = SqlSessionUtil.getCurrentSqlSession().getMapper(ClueDao.class);
	private CustomerDao customerDao = SqlSessionUtil.getCurrentSqlSession().getMapper(CustomerDao.class);
	private ContactsDao contactsDao = SqlSessionUtil.getCurrentSqlSession().getMapper(ContactsDao.class);
	private ClueActivityRelationDao clueActivityRelationDao = SqlSessionUtil.getCurrentSqlSession().getMapper(ClueActivityRelationDao.class);
	private ContactsActivityRelationDao contactsActivityRelationDao = SqlSessionUtil.getCurrentSqlSession().getMapper(ContactsActivityRelationDao.class);
	private ClueRemarkDao clueRemarkDao = SqlSessionUtil.getCurrentSqlSession().getMapper(ClueRemarkDao.class);
	private CustomerRemarkDao customerRemarkDao = SqlSessionUtil.getCurrentSqlSession().getMapper(CustomerRemarkDao.class);
	private ContactsRemarkDao contactsRemarkDao = SqlSessionUtil.getCurrentSqlSession().getMapper(ContactsRemarkDao.class);
	private TranDao tranDao = SqlSessionUtil.getCurrentSqlSession().getMapper(TranDao.class);
	private TranRemarkDao tranRemarkDao = SqlSessionUtil.getCurrentSqlSession().getMapper(TranRemarkDao.class);
	private TranHistoryDao tranHistoryDao = SqlSessionUtil.getCurrentSqlSession().getMapper(TranHistoryDao.class);
		
	@Override
	public boolean save(Clue clue) {
		return clueDao.save(clue) == 1;
	}

	@Override
	public Clue getById(String id) {
		return clueDao.getById(id);
	}

	@Override
	public void convert(String clueId, String createBy, Tran tran) {
//		1. 根据线索id获取线索信息
		Clue clue = clueDao.getById2(clueId);
//		2. 从线索信息中提取客户信息，保存到客户表（避免客户重复：根据客户名称[公司名]精确查询）
		Customer customer = customerDao.getByName(clue.getCompany());
		if(customer == null){
			customer = new Customer();
			customer.setId(UUIDGenerator.generate());
			customer.setOwner(clue.getOwner());
			customer.setName(clue.getCompany());
			customer.setWebsite(clue.getWebsite());
			customer.setPhone(clue.getPhone());
			customer.setDescription(clue.getDescription());
			customer.setContactSummary(clue.getContactSummary());
			customer.setNextContactTime(clue.getNextContactTime());
			customer.setAddress(clue.getAddress());
			customer.setCreateBy(createBy);
			customer.setCreateTime(DateUtil.getSysTime());
			customerDao.save(customer);
		}
//		3. 从线索信息中提取联系人信息，保存到联系人表（无法避免重复，直接保存新的联系人）
		Contacts contacts = new Contacts();
		contacts.setId(UUIDGenerator.generate());
		contacts.setOwner(clue.getOwner());
		contacts.setSource(clue.getSource());
		contacts.setFullname(clue.getFullname());
		contacts.setAppellation(clue.getAppellation());
		contacts.setJob(clue.getJob());
		contacts.setMphone(clue.getMphone());
		contacts.setEmail(clue.getEmail());
		contacts.setCustomerId(customer.getId());
		contacts.setDescription(clue.getDescription());
		contacts.setContactSummary(clue.getContactSummary());
		contacts.setNextContactTime(clue.getNextContactTime());
		contacts.setAddress(clue.getAddress());
		contacts.setCreateBy(createBy);
		contacts.setCreateTime(DateUtil.getSysTime());
		contactsDao.save(contacts);
//		4. 将“线索市场活动关系”转换为“联系人市场活动关系”（转换之前需要判断是否存在关系）
		List<String> activityIds = clueActivityRelationDao.getActivityIdByClueId(clueId);
		if(activityIds.size() > 0){
			List<ContactsActivityRelation> carList = new ArrayList<>();
			for(String activityId : activityIds){
				// 创建一个“联系人市场活动关系对象”
				ContactsActivityRelation car = new ContactsActivityRelation();
				car.setId(UUIDGenerator.generate());
				car.setContactsId(contacts.getId());
				car.setActivityId(activityId);
				carList.add(car);
			}
			contactsActivityRelationDao.saves(carList);
		}
//		5. 将“线索备注”转换为：客户备注、联系人备注（转换备注之前需要判断是否存在备注）
		List<Remark> remarkList = clueRemarkDao.getByClueId(clueId);
		if(remarkList.size() > 0){
			for(Remark remark : remarkList){
				remark.setCustomerId(customer.getId());
				remark.setContactsId(contacts.getId());
			}
			customerRemarkDao.saves(remarkList);
			contactsRemarkDao.saves(remarkList);
		}
		// 判断是否创建交易
		if(tran != null){
			// 给交易对象的属性赋值
			tran.setId(UUIDGenerator.generate());
			tran.setOwner(clue.getOwner());
			tran.setCustomerId(customer.getId());
			tran.setSource(clue.getSource());
			tran.setContactsId(contacts.getId());
			tran.setDescription(clue.getDescription());
			tran.setContactSummary(clue.getContactSummary());
			tran.setNextContactTime(clue.getNextContactTime());
			tran.setCreateBy(createBy);
			tran.setCreateTime(DateUtil.getSysTime());
			// 保存交易
			tranDao.save(tran);
			// 保存交易备注
			if(remarkList.size() > 0){
				for(Remark remark : remarkList){
					remark.setTranId(tran.getId());
				}
				tranRemarkDao.saves(remarkList);
			}
			// 保存交易历史
			TranHistory th = new TranHistory();
			th.setId(UUIDGenerator.generate());
			th.setStage(tran.getStage());
			th.setMoney(tran.getMoney());
			th.setExpectedDate(tran.getExpectedDate());
			th.setCreateBy(tran.getCreateBy());
			th.setCreateTime(tran.getCreateTime());
			th.setTranId(tran.getId());
			tranHistoryDao.save(th);
		}
//		6. 删除线索备注
		clueRemarkDao.deleteByClueId(clueId);
//		7. 删除线索市场活动的关系
		clueActivityRelationDao.deleteByClueId(clueId);
//		8. 删除线索 
		clueDao.deleteById(clueId);
	}

}




















