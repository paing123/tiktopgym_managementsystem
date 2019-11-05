package com.tiktop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tiktop.model.Attendance;

@Mapper
public interface AttendanceMapper {
	public void save(@Param("attendance") Attendance attendance);
	
	public List<Attendance> findAttendance(@Param("attendance") Attendance attendance);
	
	public void update(@Param("attendance") Attendance attendance);
	
	public void delete(@Param("id") Integer id);
	
	public List<Attendance> checkAttendance(@Param("attendance") Attendance attendance);

	public List<Attendance> findAttendanceMemberId(@Param("attendance") Attendance attendance);
}
