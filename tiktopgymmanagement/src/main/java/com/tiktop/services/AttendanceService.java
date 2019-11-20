package com.tiktop.services;

import java.text.ParseException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.tiktop.model.Attendance;

public interface AttendanceService {

	void save(Attendance attendance);

	List<Attendance> findAttendance(Attendance attendance);

	void delete(Integer id);

	void update(Attendance attendance);
	
	List<Attendance> checkAttendance(Attendance attendance);
	
	Attendance bodyMeasurementCheck(List<Attendance> attendances);
	
	Boolean checkDate(Attendance attendance) throws ParseException;

	ModelAndView checkLossGain(Attendance attendance);
	
	List<Attendance> findAttendanceMemberId(Attendance attendance);
	
	Attendance convertDateFormat(Attendance attendance);
}
