package com.masai.dao;

import java.sql.SQLException;
import java.util.List;

import com.masai.Exception.courException;
import com.masai.Exception.studException;
import com.masai.build.BatchRecord;
import com.masai.build.Course;
import com.masai.build.Student;
import com.masai.build.batchSeat;

public interface adminDao {

	
	public void adminAddNewCourFunction();
	public void adminUpdateFeesOfCourFunction ();
	public void adminDeleteCourFunction (String cName);
	public int getCourIdFunction(String cName) throws SQLException, courException;
	public boolean checkForAdminFunction(boolean isPresent);
	public List<Course> getListOfCourses() throws SQLException, courException;
	public void adminDisCourInfoFunction(String cName);
	public void creatingBatchUnderCourFunction(String courseName, int bId, int totalSeats);
	public void allocateStudInaBatchFunction() throws SQLException, studException, courException, Exception;
	public List<Student> getListOfStudFunction();
	public String getStudPwdFunction(String sEmail) throws studException;
	public boolean checkForStudFunction(String sEmail, String sPassword) throws studException;
	public void disCourAvaiSeatsFunction(String string) throws SQLException;
	public boolean regBatchFunction(int cId, String cName, String sEmail) throws SQLException, Exception;
	public List<batchSeat> getBSeatsDetailsFunction() throws SQLException;
	public String getCourseNameFunction(int i) throws SQLException;
	public void updateSeatsOfBatchFunction();
	public void viewStudentsOfBatchFunction();
	public String getstudNameFromEmailFunction(String sEmail);
	public String regStudFunction(Student s) throws SQLException;
	public boolean checkForCourFunction(String str1) throws SQLException;
	public void editStudProfFunction(String sEmail, String sNewPassword, String sNewName) throws SQLException;
	public List<String> getSEmailListFunction();
	public boolean studDetailEditFunction(String sEmail, String sPassword, String sNewPassword, String sNewName,
			String sNewCourse) throws SQLException, courException;
	public BatchRecord getBatchRcdForStudFunction(String sEmail);
	
}
