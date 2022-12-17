package com.masai.usecases;

import com.masai.dao.adminDao;
import com.masai.dao.daoImpl;

public class allocateStudentInBatch {
	public allocateStudentInBatch() {
		adminDao daoObj = new daoImpl();
		daoObj.allocateStudInaBatchFunction();
	}
}
