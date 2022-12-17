package com.masai.usecases;

import com.masai.dao.adminDao;
import com.masai.dao.daoImpl;

public class viewStudentsOfBatch {
	public viewStudentsOfBatch() {
		adminDao obj = new daoImpl();
		obj.viewStudentsOfBatchFunction();
	}
}
