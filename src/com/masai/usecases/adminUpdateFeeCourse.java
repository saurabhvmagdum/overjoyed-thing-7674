package com.masai.usecases;

import com.masai.Main.daoImpl;
import com.masai.dao.adminDao;

public class adminUpdateFeeCourse {
	public adminUpdateFeeCourse() {
		adminDao obj = new daoImpl();
		obj.adminUpdateFeesOfCourFunction();
	}
}
