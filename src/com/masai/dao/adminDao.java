package com.masai.dao;

import java.sql.SQLException;
import java.util.List;

import com.masai.Exception.courException;
import com.masai.Exception.studException;
import com.masai.build.Course;
import com.masai.build.Student;

public interface adminDao {

	
	public void adminAddNewCourFunction();
	public void adminUpdateFeesOfCourFunction ();
	public void adminDeleteCourFunction (String cName);
	public int getCourIdFunction(String cName) throws SQLException, courException;
	public boolean checkForAdminFunction();
	public List<Course> getListOfCourses() throws SQLException, courException;
	public void adminDisCourInfoFunction(String cName);
	public void creatingBatchUnderCourFunction(String courseName, int bId, int totalSeats);
	public void allocateStudInaBatchFunction();
	public List<Student> getListOfStudFunction();
	public String getStudPwdFunction(String sEmail) throws studException;
	public boolean checkForStudFunction(String sEmail, String sPassword) throws studException;
	public void disCourAvaiSeatsFunction(String string);
	
}
