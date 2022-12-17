package com.masai.usecases;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.masai.Exception.courException;
import com.masai.Exception.studException;
import com.masai.dao.adminDao;
import com.masai.dao.daoImpl;

public class LoginStudClass {
	public LoginStudClass() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter student e-mail:");
		String sEmail = sc.next();
		System.out.println("Enter student password:");
		String sPassword = sc.next();
		
		adminDao daoObj = new daoImpl();
		
		try {
			boolean isStudentPresent = daoObj.checkForStudFunction(sEmail, sPassword);
			
			if(isStudentPresent) {
				System.out.println("Welcome "+ daoObj.getstudNameFromEmailFunction(sEmail));
				System.out.println("Please select-");
				System.out.println("1. Update my details");
				System.out.println("2. View all available courses and seat availability");
				int opt = sc.nextInt();
				
				if(opt == 1) {
					System.out.println("Enter your new password:");
					String sNewPassword = sc.next();
					sc.nextLine();
					System.out.println("Enter your new name:");
					String sNewName = sc.nextLine();
					daoObj.editStudProfFunction(sEmail, sNewPassword, sNewName);
					
					System.out.println("Enter yes to change course:");
					System.out.println("Enter no to leave");
					String opt2 = sc.next().toLowerCase();
					
					if(opt2.equalsIgnoreCase("yes")) {
						System.out.println("Enter your new course:");
						String sNewCourse = sc.next().toUpperCase();
						List<String>SEmailList = daoObj.getSEmailListFunction();
						
						if(SEmailList.contains(sEmail)) {
							boolean check = daoObj.studDetailEditFunction(sEmail, sPassword, sNewPassword, sNewName, sNewCourse);
						}
						else
						{
							int cId = daoObj.getCourIdFunction(sNewCourse);
							daoObj.regBatchFunction(cId, sNewCourse, sEmail);
							System.out.println("Student name "+sNewName+ " registered into course "+ sNewCourse);
						}
						
					
						
					}
						
				}
				else if(opt == 2) {
					
					daoObj.disCourAvaiSeatsFunction("includeSeat");
					
				}
				
			}
			else System.out.println("Student with e-mail "+ sEmail +" does not exists");
			
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		catch (studException e) {
			System.out.println(e.getMessage());
		}
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		catch (courException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {		
			System.out.println(e.getMessage());
		}
		
	}
}
