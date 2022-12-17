package com.masai.usecases;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.masai.Exception.courException;
import com.masai.build.Course;
import com.masai.dao.adminDao;
import com.masai.dao.daoImpl;

public class searchCourseInfo {
	public searchCourseInfo() throws SQLException, courException {
		Scanner sc = new Scanner(System.in);
		adminDao daoObj = new daoImpl();
		List<Course> lisOfCourses = daoObj.getListOfCourses();
		System.out.println("List of courses-");
		lisOfCourses.forEach(c ->{
			System.out.println(c.getCname());
		});
		System.out.println("Enter course name to get info");
		String cName = sc.next();
		daoObj.adminDisCourInfoFunction(cName);
	}
}
