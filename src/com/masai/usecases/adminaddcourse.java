package com.masai.usecases;


import com.masai.dao.adminDao;
import com.masai.dao.daoImpl;

public class adminaddcourse {
	public adminaddcourse() {
		
		adminDao daoObj = new daoImpl();
		daoObj.adminAddNewCourFunction();
	}
}
