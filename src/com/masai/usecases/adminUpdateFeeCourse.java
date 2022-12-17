package com.masai.usecases;

import com.masai.dao.adminDao;
import com.masai.dao.daoImpl;

public class adminUpdateFeeCourse {
	public adminUpdateFeeCourse() {
		adminDao obj = new daoImpl();
		obj.adminUpdateFeesOfCourFunction();
	}
}
