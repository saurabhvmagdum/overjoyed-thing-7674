package com.masai.build;

import java.util.Objects;

public class batchSeat {
	private int batchId;
	private int courseId;
	private int totalSeats;
	private int seatsFilled;
	
	public batchSeat(int bId, int cId, int totalSeats, int seatsFilled) {
		super();
		this.batchId = bId;
		this.courseId = cId;
		this.totalSeats = totalSeats;
		this.seatsFilled = seatsFilled;
	}

	public int getbId() {
		return batchId;
	}

	public void setbId(int bId) {
		this.batchId = bId;
	}

	public int getcId() {
		return courseId;
	}

	public void setcId(int cId) {
		this.courseId = cId;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getSeatsFilled() {
		return seatsFilled;
	}

	public void setSeatsFilled(int seatsFilled) {
		this.seatsFilled = seatsFilled;
	}

	@Override
	public int hashCode() {
		return Objects.hash(batchId, courseId, seatsFilled, totalSeats);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		batchSeat other = (batchSeat) obj;
		return batchId == other.batchId && courseId == other.courseId && seatsFilled == other.seatsFilled
				&& totalSeats == other.totalSeats;
	}

	@Override
	public String toString() {
		return "batchSeatDTO [bId=" + batchId + ", cId=" + courseId + ", totalSeats=" + totalSeats + ", seatsFilled="
				+ seatsFilled + "]";
	}
	
	
	
	
}
