package com.masai.usecases;


import com.masai.Main.daoImpl;
import com.masai.dao.adminDao;

public class adminaddcourse {
	public adminaddcourse() {
		
		adminDao daoObj = new daoImpl();
		daoObj.adminAddNewCourFunction();
	}
}
