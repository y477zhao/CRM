package com.wkcto.crm.workbench.service;

import java.util.List;
import java.util.Map;

import com.wkcto.crm.workbench.domain.Tran;

public interface TranService {

	boolean save(Tran t, String customerName);

	List<Tran> list();

	Tran detail(String id);

	boolean changeStage(Tran t);

	Map<String, Object> getChart();


}
