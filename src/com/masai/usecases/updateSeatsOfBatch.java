package com.masai.usecases;

import com.masai.dao.adminDao;
import com.masai.dao.daoImpl;

public class updateSeatsOfBatch{
	public updateSeatsOfBatch() {
		adminDao obj = new daoImpl();
		obj.updateSeatsOfBatchFunction();
	}
}
