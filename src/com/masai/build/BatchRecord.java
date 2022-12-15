package com.masai.build;

import java.util.Objects;

public class BatchRecord {
	private int batchRecordId;
	private int batchNo;
	private int courseId;
	private String studentEmail;
	
	public BatchRecord(int batchUid, int batchNo, int cId, String sEmail) {
		super();
		this.batchRecordId = batchUid;
		this.batchNo = batchNo;
		this.courseId = cId;
		this.studentEmail = sEmail;
	}

	public int getBatchUid() {
		return batchRecordId;
	}

	public void setBatchUid(int batchUid) {
		this.batchRecordId = batchUid;
	}

	public int getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(int batchNo) {
		this.batchNo = batchNo;
	}

	public int getcId() {
		return courseId;
	}

	public void setcId(int cId) {
		this.courseId = cId;
	}

	public String getsEmail() {
		return studentEmail;
	}

	public void setsEmail(String sEmail) {
		this.studentEmail = sEmail;
	}

	@Override
	public int hashCode() {
		return Objects.hash(batchNo, batchRecordId, courseId, studentEmail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BatchRecord other = (BatchRecord) obj;
		return batchNo == other.batchNo && batchRecordId == other.batchRecordId && courseId == other.courseId
				&& Objects.equals(studentEmail, other.studentEmail);
	}

	@Override
	public String toString() {
		return "BatchRecordDTO [batchUid=" + batchRecordId + ", batchNo=" + batchNo + ", cId=" + courseId + ", sEmail=" + studentEmail
				+ "]";
	}
	
}
