package com.tiktop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiktop.mapper.AttendanceMapper;
import com.tiktop.model.Attendance;

@Repository("attendanceRepository")
public class AttendanceDaoImpl implements AttendanceDao {

	@Autowired
	AttendanceMapper attendanceMapper;
	
	public void save(Attendance attendance) {
		attendanceMapper.save(attendance);
	}
	
	public List<Attendance> findAttendance(Attendance attendance) {
		List<Attendance> attendances = attendanceMapper.findAttendance(attendance);
		return attendances;
	}
	
	public void delete(Integer id) {
		attendanceMapper.delete(id);
	}
	
	public void update(Attendance attendance) {
		attendanceMapper.update(attendance);
	}

	@Override
	public List<Attendance> checkAttendance(Attendance attendance) {
		return attendanceMapper.checkAttendance(attendance);
	}

	@Override
	public List<Attendance> findAttendanceMemberId(Attendance attendance) {
		return attendanceMapper.findAttendanceMemberId(attendance);
	}
}
