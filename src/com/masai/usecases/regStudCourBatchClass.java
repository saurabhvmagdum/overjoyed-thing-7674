package com.masai.usecases;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.masai.Exception.courException;
import com.masai.Exception.studException;
import com.masai.build.Student;
import com.masai.dao.adminDao;
import com.masai.dao.daoImpl;

public class regStudCourBatchClass {
	
	public regStudCourBatchClass() {
		
		Scanner sc  = new Scanner(System.in);
		Student s = null;
		String cName = null;
		
		try{	
			System.out.println("Enter your e-mail:");
			String sEmail = sc.next();
			System.out.println("Enter your Password:");
			String sPassword = sc.next();
			System.out.println("Enter your Name:");
			String sName = sc.next();				
			
			s = new Student(sEmail, sPassword, sName);
			
			adminDao daoObj = new daoImpl();
			
			try {
				Boolean ans = daoObj.checkForStudFunction(sEmail, sPassword);
				if(!ans) {
					daoObj.regStudFunction(s);
					System.out.println();
					System.out.println("Student "+ sName +" with e-mail "+ sEmail +" registstered successfully");
					System.out.println();
									
					System.out.println("Enter yes to join a course. ");
					System.out.println("Enter no to quit");
					String opt = sc.next();
					
					if(opt.equalsIgnoreCase("yes")) {
						System.out.println("List of courses available:");
						daoObj.disCourAvaiSeatsFunction("inclno");
	
						System.out.println("Enter the name of the course name you want to join:");
	
						String sDecision2 = sc.next().toUpperCase();
						int cId = daoObj.getCourIdFunction(sDecision2);
						
						if(daoObj.checkForCourFunction(sDecision2)) {
							daoObj.regBatchFunction(cId, sDecision2, sEmail);
						}
						else {
							System.out.println("Such course "+ sDecision2 +" doesn't exist");
							
						}
					}
					else throw new courException("Have a nice day " +sName +" .");
					
				}
				else System.out.println("student already exists");
			}
			catch(studException e){
				System.out.println(e.getMessage());
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			} catch (courException e1) {
				System.out.println(e1.getMessage());
			} catch (Exception e) {
				System.out.println(e.getMessage());
	
			}
		}
		catch(InputMismatchException e) {
				System.out.println(e.getMessage());
		}
		
	}
}
