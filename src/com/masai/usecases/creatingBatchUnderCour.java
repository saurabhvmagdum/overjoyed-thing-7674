package com.masai.usecases;

import java.util.Scanner;

import com.masai.dao.adminDao;
import com.masai.dao.daoImpl;

public class creatingBatchUnderCour {
	public creatingBatchUnderCour() {
		Scanner sc = new Scanner(System.in);
		adminDao daoObj = new daoImpl();
		System.out.println("Enter Course name:");
		String courseName = sc.next().toUpperCase();
		System.out.println("Enter Batch no:");
		int bId = sc.nextInt();
		System.out.println("Enter total seats in this batch: ");
		int totalSeats = sc.nextInt();
		daoObj.creatingBatchUnderCourFunction(courseName, bId, totalSeats);

	}
}
