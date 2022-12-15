package com.masai.build;

import java.util.Objects;

public class Student {
	private String studentEmail;
	private String studentPassword; 
	private String studentName;
	
	public Student(String sEmail, String sPassword, String sName) {
		super();
		this.studentEmail = sEmail;
		this.studentPassword = sPassword;
		this.studentName = sName;
	}

	public String getsEmail() {
		return studentEmail;
	}

	public void setsEmail(String sEmail) {
		this.studentEmail = sEmail;
	}

	public String getsPassword() {
		return studentPassword;
	}

	public void setsPassword(String sPassword) {
		this.studentPassword = sPassword;
	}

	public String getsName() {
		return studentName;
	}

	public void setsName(String sName) {
		this.studentName = sName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(studentEmail, studentName, studentPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(studentEmail, other.studentEmail) && Objects.equals(studentName, other.studentName)
				&& Objects.equals(studentPassword, other.studentPassword);
	}

	@Override
	public String toString() {
		return "Student Email=" + studentEmail + ", Student Name=" + studentName;
	}
	
}
