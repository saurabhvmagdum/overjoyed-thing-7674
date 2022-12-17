package com.masai.usecases;

import java.sql.SQLException;

import com.masai.Exception.courException;
import com.masai.Exception.studException;
import com.masai.dao.adminDao;
import com.masai.dao.daoImpl;

public class allocateStudentInBatch {
	public allocateStudentInBatch() throws SQLException, studException, courException, Exception {
		adminDao daoObj = new daoImpl();
		daoObj.allocateStudInaBatchFunction();
	}
}
