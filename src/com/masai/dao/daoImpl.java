package com.masai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.masai.Exception.courException;
import com.masai.Exception.studException;
import com.masai.Utility.DBUtility;
import com.masai.build.Admin;
import com.masai.build.Course;
import com.masai.build.Student;
import com.masai.build.batchSeat;

public class daoImpl implements adminDao {

	@Override
	public void adminAddNewCourFunction() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course ID:");
		int courseId = sc.nextInt();
		System.out.println("Enter Course name :");
		String courseName = sc.next();
		System.out.println("Enter Course fees:");
		int coursefees = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter course information:");
		String courseInfo = sc.nextLine();
		
		try(Connection conn = DBUtility.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("insert into course values (?, ?, ?, ?)");
			ps.setInt(1, courseId);
			ps.setString(2, courseName.toUpperCase());
			ps.setInt(3, coursefees);
			ps.setString(4, courseInfo);
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				System.out.println("Course inserted into database successfully.");
			}
			else {
				System.out.println("Insertion failed.");
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void adminUpdateFeesOfCourFunction() {
		// TODO Auto-generated method stub
		adminDao obj = new daoImpl();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Course name: ");
		String cName = sc.next();
		System.out.println("Enter fees to update:");
		int fees = sc.nextInt();
		
		try(Connection conn = DBUtility.getConnection()){
			int cId = obj.getCourIdFunction(cName);
			PreparedStatement ps = conn.prepareStatement("update course set coursefees = ? where courseId = ?");
			ps.setInt(1, fees);
			ps.setInt(2, cId);
			ps.executeUpdate();
			
			System.out.println("Course is updated with fees. ");
			
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		catch (courException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void adminDeleteCourFunction(String cName) {
		adminDao daoObj = new daoImpl();

		try(Connection conn = DBUtility.getConnection()){
			int courseId = daoObj.getCourIdFunction(cName);

			PreparedStatement ps2 = conn.prepareStatement("delete from batchSeats where courseId = ?");
			ps2.setInt(1, courseId);
			PreparedStatement ps = conn.prepareStatement("delete from course where courseId = ?");
			ps.setInt(1, courseId);
			ps2.executeUpdate();
			ps.executeUpdate();
			
			System.out.println(" course deleted successfully.");
			System.out.println(" deleted in every training session.");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (courException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	@Override
	public int getCourIdFunction(String cName) throws SQLException, courException {
		// TODO Auto-generated method stub
		int res = 0;
		
		try(Connection conn = DBUtility.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select courseId from courses where courseName = ?");
			ps.setString(1, cName);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				res = rs.getInt("courseId");
			}
			else throw new courException("There's no such course. Please enter course correctly.");
			
		}
		catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		return res;
	}

	@Override
	public boolean checkForAdminFunction() {
		// TODO Auto-generated method stub

		List<Admin> list1 = new ArrayList<>();
		list1.add(new Admin("Saurabh", "pass123"));
		list1.add(new Admin("admin", "admin123"));
		boolean isPresent = false;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Login to profile-");
		System.out.println("Enter your User name:");
		String adminName = sc.next();
		System.out.println("Enter your password: ");
		String adminPassword = sc.next();
		
		Admin ad = new Admin(adminName, adminPassword);
		
		if(list1.contains(ad)){
				isPresent=true;
		}
		return isPresent;
	}

	@Override
	public List<Course> getListOfCourses() throws SQLException, courException {
		
		List<Course> list1 = new ArrayList<>();
		boolean isEmpty = true;
		
		try(Connection conn = DBUtility.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from course");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				isEmpty = false;
				int cId = rs.getInt("courseId");
				String cName = rs.getString("courseName");
				int fees = rs.getInt("coursefees");
				String cInfo = rs.getString("courseInfo");
				
				Course c = new Course(cId, cName, fees, cInfo);
				list1.add(c);
			}
		}
		catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		if(isEmpty){
				throw new courException("No course present in the database");
		}
		
		return list1;
	
	}

	@Override
	public void adminDisCourInfoFunction(String cName) {
		// TODO Auto-generated method stub
		adminDao daoObj = new daoImpl();

		try(Connection conn = DBUtility.getConnection()){
			int courseId = daoObj.getCourIdFunction(cName);

			PreparedStatement ps = conn.prepareStatement("select * from course where courseId = ?");
			ps.setInt(1, courseId);
			ResultSet rs =  ps.executeQuery();
			
			if(rs.next()) {
				int cIdl = rs.getInt("courseId");
				String cNamel = rs.getString("courseName");
				int fees = rs.getInt("coursefees");
				String cInfo = rs.getString("courseInfo");
				System.out.println("The course information for course name you asked for-");
				System.out.println("Course information :");
				System.out.println("CourseID: "+ cIdl);
				System.out.println("Course Name: "+ cNamel);
				System.out.println("Course fee: "+ fees);
				System.out.println("Course information: "+ cInfo);
				System.out.println();
			}
			
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (courException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void creatingBatchUnderCourFunction(String courseName, int bId, int totalSeats) {
		adminDao daoObj = new daoImpl();
		
		try(Connection conn = DBUtility.getConnection()){
			int cId = daoObj.getCourIdFunction(courseName);
			PreparedStatement ps = conn.prepareStatement("insert into batchSeats values (?, ?, ?, ?)");
			ps.setInt(1, bId);
			ps.setInt(2, cId);
			ps.setInt(3, totalSeats);
			ps.setInt(4, 0);
			ps.executeUpdate();
			System.out.println("Batch created under course "+ courseName);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (courException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void allocateStudInaBatchFunction() {
		// TODO Auto-generated method stub
		adminDao daoObj = new daoImpl();
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = DBUtility.getConnection()){
			List<Student> arr = daoObj.getListOfStudFunction();
			if(arr.size()==0)
				{
					System.out.println("No student has enrolled yet.");
				}
			else {
				
				System.out.println("List of students :");
				System.out.println();
				arr.forEach(s -> System.out.println(s));
				System.out.println();
				System.out.println("Process to allocate student under a abatch in a acourse starts.");
				System.out.println("Enter the Student E-mail: ");
				String sEmail = sc.next();
				String sPassword = daoObj.getStudPwdFunction(sEmail);
				
				boolean existence = daoObj.checkForStudFunction(sEmail, sPassword);
				
				if(existence) {
					System.out.println("Enter course name to allocate into batch :");
					daoObj.disCourAvaiSeatsFunction("inclno");
					String cName = sc.next();
					int cId = daoObj.getCourIdFunction(cName);
					
//					daoObj.regBatchFunction(cId, cName, sEmail);
					
				}
				else throw new studException("Student with e-mail "+ sEmail + " not found");
			}
		
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (studException e) {
			System.out.println(e.getMessage());
		} catch (courException e) {
			System.out.println(e.getMessage());
		} 
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		
	}

	@Override
	public List<Student> getListOfStudFunction() {
		List<Student> list = new ArrayList<>();

		try(Connection conn = DBUtility.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from student");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				String sEmail = rs.getString("studentEmail");
				String sPassword = rs.getString("studentPassword");
				String sName = rs.getString("studentName");
				Student s = new Student(sEmail, sPassword, sName);
				list.add(s);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return list;
	}

	@Override
	public String getStudPwdFunction(String studentEmail) throws studException {
		
		String str = null;
		try(Connection conn = DBUtility.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select studentPassword from student where studentEmail = ?");
			ps.setString(1, studentEmail);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				str = rs.getString("studentPassword"); 
			}
			else throw new studException("Student with E-mail "+ studentEmail +" does not exists");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return str;
	}

	@Override
	public boolean checkForStudFunction(String sEmail, String sPassword) throws studException {
		boolean isVal = false;

		try(Connection conn = DBUtility.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select * from student where studentEmail= ? AND studentPassword = ?");
			ps.setString(1, sEmail);
			ps.setString(2, sPassword);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				isVal = true;
			}
			
		} catch (SQLException e) {
			throw new studException(e.getMessage());
		}
		
		return isVal;
	}

	@Override
	public void disCourAvaiSeatsFunction(String string) {
		// TODO Auto-generated method stub

	
	}
}

