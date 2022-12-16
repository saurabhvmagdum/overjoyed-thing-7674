package com.masai.dao;

import java.sql.SQLException;
import java.util.List;

import com.masai.Exception.courException;
import com.masai.build.Course;

public interface adminDao {

	
	public void adminAddNewCourFunction();
	public void adminUpdateFeesOfCourFunction ();
	public void adminDeleteCourFunction (String cName);
	public int getCourIdFunction(String cName) throws SQLException, courException;
	public boolean checkForAdminFunction();
	public List<Course> getListOfCourses() throws SQLException, courException;
	
}
