package com.tiktop.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Attendance {

	public Integer attendanceId;
	@NotNull
	@Min(4)
	@Max(7)
	public Double height;
	@NotNull
	@Min(50)
	@Max(700)
	public Double weight;
	@NotNull
	public String attendanceDate;
	@NotNull
	public Integer memberId;
	public String updateDate;
	public String startDate;
	public String endDate;
	public Double resultHeight;
	public Double resultWeight;

	public String getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public Integer getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Integer attendanceId) {
		this.attendanceId = attendanceId;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Double getResultHeight() {
		return resultHeight;
	}

	public void setResultHeight(Double resultHeight) {
		this.resultHeight = resultHeight;
	}

	public Double getResultWeight() {
		return resultWeight;
	}

	public void setResultWeight(Double resultWeight) {
		this.resultWeight = resultWeight;
	}
}
