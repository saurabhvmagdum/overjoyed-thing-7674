package com.masai.usecases;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.masai.Exception.courException;
import com.masai.Main.daoImpl;
import com.masai.build.Course;
import com.masai.dao.adminDao;

public class deleteCourse {
	public deleteCourse()  throws SQLException, courException {
		Scanner sc = new Scanner(System.in);
		adminDao daoObj = new daoImpl();
		List<Course> lisOfCourses = daoObj.getListOfCourses();
		System.out.println("List of courses-");
		lisOfCourses.forEach(c ->{
			System.out.println(c.getCname());
		});
		System.out.println("Enter course name to delete:");
		String cName = sc.next();
		daoObj.adminDeleteCourFunction(cName);
	}
}
