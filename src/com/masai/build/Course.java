package com.masai.build;

import java.util.Objects;

public class Course {

	private int courseId;
	private String courseName;
	private int coursefees;
	private String courseInfo;
	
	public Course(int cId, String cname, int fees, String cInfo) {
		super();
		this.courseId = cId;
		courseName = cname;
		this.coursefees = fees;
		this.courseInfo = cInfo;
	}

	public int getcId() {
		return courseId;
	}

	public void setcId(int cId) {
		this.courseId = cId;
	}

	public String getCname() {
		return courseName;
	}

	public void setCname(String cname) {
		courseName = cname;
	}

	public int getFees() {
		return coursefees;
	}

	public void setFees(int fees) {
		this.coursefees = fees;
	}

	public String getcInfo() {
		return courseInfo;
	}

	public void setcInfo(String cInfo) {
		this.courseInfo = cInfo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseName, courseId, courseInfo, coursefees);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(courseName, other.courseName) && courseId == other.courseId && Objects.equals(courseInfo, other.courseInfo)
				&& coursefees == other.coursefees;
	}

	@Override
	public String toString() {
		return "Course [cId=" + courseId + ", Cname=" + courseName + ", fees=" + coursefees + ", cInfo=" + courseInfo + "]";
	}
	
	
}
