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
import com.masai.Main.Main;
import com.masai.Utility.DBUtility;
import com.masai.build.Admin;
import com.masai.build.BatchRecord;
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
		try {
			Main.main(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		try {
			Main.main(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		try {
			Main.main(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int getCourIdFunction(String cName) throws SQLException, courException {
		// TODO Auto-generated method stub
		int res = 0;
		
		try(Connection conn = DBUtility.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select courseId from course where courseName = ?");
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
	public boolean checkForAdminFunction(boolean isPresent) {
		// TODO Auto-generated method stub
		Admin ad = null;
		List<Admin> list1 = new ArrayList<>();
		list1.add(new Admin("Saurabh", "pass123"));
		list1.add(new Admin("admin", "admin123"));
		if(isPresent == false) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Login to profile-");
			System.out.println("Enter your User name:");
			String adminName = sc.next();
			System.out.println("Enter your password: ");
			String adminPassword = sc.next();
			
			ad = new Admin(adminName, adminPassword);
		}
		else {
			System.out.println("You are Admin and previously logged in .");
		}
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
		try {
			Main.main(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		try {
			Main.main(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void allocateStudInaBatchFunction() throws Exception {
		adminDao daoObj = new daoImpl();
		Scanner sc = new Scanner(System.in);
		
		try(Connection conn = DBUtility.getConnection()){
			List<Student> listOfStudents = daoObj.getListOfStudFunction();
			if(listOfStudents.size()==0) {
				System.out.println("No student has enrolled yet.");
			}
			else {
				
				System.out.println("List of students in our institution-");
				System.out.println();
				listOfStudents.forEach(s -> System.out.println(s));
				System.out.println();
				System.out.println("Process to allocate student under a batch in a course starts.");
				System.out.println("Enter the Student E-mail: ");
				String sEmail = sc.next();
				String sPassword = daoObj.getStudPwdFunction(sEmail);
				
				boolean existence = daoObj.checkForStudFunction(sEmail, sPassword);
				
				if(existence) {
					System.out.println("Enter course name to allocate into batch other course:");
					daoObj.disCourAvaiSeatsFunction("inclno");
					System.out.println("Enter Course Name");
					String cName = sc.next();
					int cId = daoObj.getCourIdFunction(cName);
					
					daoObj.regBatchFunction(cId, cName, sEmail);
					
				}
				else throw new studException("Student with e-mail "+ sEmail + " not found");
				
			}
		}
		try {
			Main.main(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void disCourAvaiSeatsFunction(String string) throws SQLException {
		// TODO Auto-generated method stub
		
		adminDao daoObj = new daoImpl();
		List<batchSeat> listOfBatchSeats;
		
		try{
			
			listOfBatchSeats = daoObj.getBSeatsDetailsFunction();
			int n = listOfBatchSeats.size();
			int[] courseIdIdx = new int[n];
			int[] seatsFilled = new int[n];
			int[] tSeats = new int[n];
			
			for(int i=0; i<n; i++) {
				courseIdIdx[i] = listOfBatchSeats.get(i).getcId();
				seatsFilled[i] = listOfBatchSeats.get(i).getSeatsFilled();
				tSeats[i] = listOfBatchSeats.get(i).getTotalSeats();
			}
			
	//		display with batch aggregation
			for(int i=0;i<n;i++) {
				for(int j=i+1; j<n; j++) {
					if(courseIdIdx[i] == courseIdIdx[j]) {
						seatsFilled[i] = seatsFilled[i] + seatsFilled[j];
						tSeats[i] = tSeats[i] +tSeats[j];
						tSeats[j] = 0;
						
					}
				}
			}
			
			if(string.equalsIgnoreCase("includeSeat"))
			{
				System.out.println("Course name  |  Seats available");
				for(int i=0; i<n; i++) {
					if(tSeats[i] != 0 && (tSeats[i] != seatsFilled[i]) && courseIdIdx[i] > 0)
					System.out.println(daoObj.getCourseNameFunction(courseIdIdx[i] ) +" | " + (tSeats[i] - seatsFilled[i]) );
				}
			}
			
			else if(string.equalsIgnoreCase("inclno"))
			{
				System.out.println("Sl.no -> Course name");
				int slno =1;
				for(int i=0; i<n; i++) {
					if(tSeats[i] != 0&& (tSeats[i] != seatsFilled[i]))
					{
						String courseName = daoObj.getCourseNameFunction(courseIdIdx[i]);
						if(courseName!= null) {
							System.out.println(slno++ +" -> " + courseName);
						}
					}			
				}
			}
		}
		catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public boolean regBatchFunction(int cId, String cName, String sEmail) throws Exception {
		boolean answer = false;
		int lbId;
		int ltSeats;
		int lseatsFilled;
		int fbatchId;
		
		adminDao daoObj = new daoImpl();

		try(Connection conn = DBUtility.getConnection()){
			
			boolean flag = false;
			boolean used = false;
			PreparedStatement ps = conn.prepareStatement("select batchId, totalSeats, seatsFilled from batchSeats where courseId = ?");
			ps.setInt(1, cId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				lbId = rs.getInt("batchId");
				ltSeats = rs.getInt("totalSeats");
				lseatsFilled = rs.getInt("seatsFilled");

				if(lseatsFilled < ltSeats && used == false) {
					used = true;
					flag = true;
					answer = true;
					fbatchId = lbId;
					

					PreparedStatement ps3 = conn.prepareStatement("update batchSeats set seatsFilled = (seatsFilled + 1) where courseId = ? AND batchId = ?");
					ps3.setInt(1, cId);
					ps3.setInt(2, lbId);
					int x2 = ps3.executeUpdate();
					
					
					PreparedStatement ps2 = conn.prepareStatement("insert into batchrecord (batchNo, courseId, studentEmail) values (?, ?, ?)");
					ps2.setInt(1, fbatchId);
					ps2.setInt(2, cId);
					ps2.setString(3, sEmail);
					int x = ps2.executeUpdate();
					
					System.out.println("Student"+ daoObj.getstudNameFromEmailFunction(sEmail) +"with e-mail "+ sEmail +" registered into course "+ cName );
				}
			}
			if(!flag) {
				throw new courException("Seats not available for course " + cName +" (or) batch not allocated for the course yet.");
			}
		}
		catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return answer;
		
	}

	@Override
	public List<batchSeat> getBSeatsDetailsFunction() throws SQLException {
		List<batchSeat> list1 = new ArrayList<>();
		
		try(Connection conn = DBUtility.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from batchSeats");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int bId = rs.getInt("batchId");
				int cId = rs.getInt("courseId");
				int totalSeats = rs.getInt("totalSeats");
				int SeatsFilled = rs.getInt("seatsFilled");

				batchSeat bs = new batchSeat(bId, cId, totalSeats, SeatsFilled);
				list1.add(bs);
				
			}
		
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		return list1;
	}

	@Override
	public String getCourseNameFunction(int courseId) throws SQLException {
		
		String courseName = null;
		
		try(Connection conn = DBUtility.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select courseName from course where courseId = ?");
			ps.setInt(1, courseId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				courseName = rs.getString("courseName");
			}
		}
		catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		
		return courseName;
	}

	
	
	@Override
	public void updateSeatsOfBatchFunction() {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		
		try(Connection conn = DBUtility.getConnection()){
			adminDao daoObj = new daoImpl();
			
			List<batchSeat> listBS = daoObj.getBSeatsDetailsFunction();
			
			if(listBS.size()==0) {
				System.out.println("There are no batches yet.");
			}
			else {
				System.out.println("Batches available:");
				listBS.forEach(b -> {
					System.out.println(b.getbId());
				});
				System.out.println();
				System.out.println("Enter Batch number:");
				int bId = sc.nextInt();
				System.out.println("Enter new capacity of seats:");
				int newTotalSeats = sc.nextInt();
				PreparedStatement ps = conn.prepareStatement("update batchSeat set totalSeats = ? where batchId = ?");
				ps.setInt(1, newTotalSeats);
				ps.setInt(2, bId);
				ps.executeUpdate();
				System.out.println("BatchID updated to seat capacity "+newTotalSeats);
			}
			
		}	
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		try {
			Main.main(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void viewStudentsOfBatchFunction() {
		// TODO Auto-generated method stub

		adminDao daoObj = new daoImpl();
		try(Connection conn = DBUtility.getConnection()){
			
			PreparedStatement ps = conn.prepareStatement("select batchNo, studentEmail from batchRecord order by batchNo");
			ResultSet rs = ps.executeQuery();
			boolean isEmpty = true;
			while(rs.next()) {
				if(isEmpty)
					{
						System.out.println("BatchNo. -> Student name");
					}
				isEmpty = false;

				int batchNo = rs.getInt("batchNo");
				String sEmail = rs.getString("studentEmail");
				System.out.println(batchNo +" -> "+ daoObj.getstudNameFromEmailFunction(sEmail));
			}
			if(isEmpty)
				{
					System.out.println("No students available in a batch / no batches available.");
				}
			else {
				System.out.println();
				System.out.println("All batches along with their students displayed.");
			}

		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		try {
			Main.main(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getstudNameFromEmailFunction(String sEmail) {
		String name = null;
		
		try(Connection conn = DBUtility.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select studentName from student where studentEmail = ?");
			ps.setString(1, sEmail);
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				name = rs.getString("studentName");
			}
			else throw new studException("Student with e-mail does not exists");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (studException e) {
			System.out.println(e.getMessage());
		} 
		
		return name;
	}

	@Override
	public String regStudFunction(Student s) throws SQLException {
		String answer = "Student already exists!";
		String sEmail = s.getsEmail();
		String sPassword = s.getsPassword();
		String sName = s.getsName();
		
		try(Connection conn = DBUtility.getConnection()){
			PreparedStatement ps = conn.prepareStatement("insert into student values (?, ?, ?)");
			ps.setString(1, sEmail);
			ps.setString(2, sPassword);
			ps.setString(3, sName);
			
			int x = ps.executeUpdate();
			answer = x + " student statements inserted";
			
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		return answer;
	}

	@Override
	public boolean checkForCourFunction(String str1) throws SQLException {
		boolean isVal = false;

		try(Connection conn = DBUtility.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select cName from courses where cName = ?");
			ps.setString(1, str1);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
					isVal = true;
			}
		}
		catch(SQLException e) {
			throw new SQLException(e.getMessage());
		}
		return isVal;
	}

	@Override
	public void editStudProfFunction(String sEmail, String sNewPassword, String sNewName) throws SQLException {
		adminDao daoObj = new daoImpl();

		try(Connection conn = DBUtility.getConnection()){
			
				PreparedStatement ps = conn.prepareStatement("update student set studentPassword = ?, studentName = ? where studentEmail = ? ");
				ps.setString(1, sNewPassword);
				ps.setString(2, sNewName);
				ps.setString(3, sEmail);
				ps.executeUpdate();
				System.out.println("Student "+ daoObj.getstudNameFromEmailFunction(sEmail) +" with e-mail "+ sEmail +" updated with new password and name");
				
			}
		catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		try {
			Main.main(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<String> getSEmailListFunction() {
		List<String> list1 = new ArrayList<>();

		try(Connection conn = DBUtility.getConnection()){

			PreparedStatement ps = conn.prepareStatement("select studentEmail from batch");
			ResultSet rs= ps.executeQuery();
			
			while(rs.next()) {
				String sEmail = rs.getString("studentEmail");
				list1.add(sEmail);
			}
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return list1;
	}

	@Override
	public boolean studDetailEditFunction(String sEmail, String sPassword, String sNewPassword, String sNewName,String sNewCourse) throws SQLException, courException {
		
		boolean answer = false;
		adminDao daoObj = new daoImpl();
		BatchRecord br = null;
		
	
		
		try(Connection conn = DBUtility.getConnection()){
			int newCId = daoObj.getCourIdFunction(sNewCourse);
			daoObj.regBatchFunction(newCId, sNewCourse, sEmail);
			answer = true;
			
			if(daoObj.getBatchRcdForStudFunction(sEmail) != null) {
				br = daoObj.getBatchRcdForStudFunction(sEmail);
				int batchRecordid = br.getBatchRecordId();
				int batchNo = br.getBatchNo();
				
				//caution delete statements ahead
				if((Integer)br.getBatchNo() != null) {
					PreparedStatement ps = conn.prepareStatement("delete from batchRecord where batchRecordid = ?");
					ps.setInt(1, batchRecordid);
					int x = ps.executeUpdate();

					PreparedStatement ps2 = conn.prepareStatement("update batchSeats set seatsFilled = (seatsFilled-1) where batchId = ?");
					ps2.setInt(1, batchNo);
					ps2.executeUpdate();
					
				}
			}												
			
		}
		
		catch (SQLException e) {
			System.out.println("check");
			throw new SQLException(e.getMessage());
		} catch (courException e) {
			System.out.println("check");
			throw new courException(e.getMessage());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		return answer;
		
	}

	@Override
	public BatchRecord getBatchRcdForStudFunction(String sEmail) {
		BatchRecord brdto = null;
		
		try(Connection conn = DBUtility.getConnection()){
			PreparedStatement ps = conn.prepareStatement("select * from batchRecord where studentEmail = ?");
			ps.setString(1, sEmail);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
			int batchUid = rs.getInt("batchRecordId");
			int batchNo = rs.getInt("batchNo");
			int cId = rs.getInt("courseId");
			String sEmailr = rs.getString("studentEmail");
			
			brdto = new BatchRecord(batchUid, batchNo, cId, sEmailr);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return brdto;
	}
}

