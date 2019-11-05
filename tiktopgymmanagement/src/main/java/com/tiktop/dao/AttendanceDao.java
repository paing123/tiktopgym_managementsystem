package com.tiktop.dao;

import java.util.List;

import com.tiktop.model.Attendance;

public interface AttendanceDao {

	void save(Attendance attendance);

	List<Attendance> findAttendance(Attendance attendance);

	void delete(Integer id);

	void update(Attendance attendance);
	
	List<Attendance> checkAttendance(Attendance attendance);

	List<Attendance> findAttendanceMemberId(Attendance attendance);
}
