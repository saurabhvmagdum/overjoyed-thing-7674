package com.masai.Main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.masai.dao.adminDao;
import com.masai.usecases.adminUpdateFeeCourse;
import com.masai.usecases.adminaddcourse;
import com.masai.usecases.deleteCourse;

public class Main {

	public static void main(String[] args) throws Exception {
		System.out.println();
		System.out.println("     						Welcome to Automated Student registration system!");
		
		adminDao daoObj = new daoImpl();
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println();
		System.out.println("     						Please choose your profile by entering the number below-");
		System.out.println();
		System.out.println("     						1. Administrator");
		System.out.println("     						2. Register in a course as a student");
		System.out.println("     						3. Login as student");

		try {
			int num = sc.nextInt();
			
			if(num == 1) {
				if(daoObj.checkForAdminFunction()) {
					System.out.println();
					System.out.println("Welcome Admin!");
					System.out.println();
					System.out.println("Enter number to do tasks");
					System.out.println();
					System.out.println("1. Add a new Courses");
					System.out.println("2. Update Fees of course.");	
					System.out.println("3. Delete  a course from any Training session.");
					System.out.println("4. Search information about a course.");
					System.out.println("5. Create Batch under a course.");
					System.out.println("6. Allocate students in a Batch under a course.");
					System.out.println("7. Update total seats of a batch.");
					System.out.println("8. View the students of every batch.");
					
					
					int num1 = sc.nextInt();
					
					switch(num1) {
					case 1:
						adminaddcourse a = new adminaddcourse();
						break;
						
					case 2:
						adminUpdateFeeCourse a1 = new adminUpdateFeeCourse();
						break;
						
					case 3:
						deleteCourse a2 = new deleteCourse();
					}
				}
				else {
					System.out.println("You entered wrong ID and Password! Try again");
				}
				
			}
		}
		catch(InputMismatchException ime) {
			System.out.println("Please enter valid input");
		}
				
	}


}
