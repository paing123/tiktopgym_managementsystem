package com.tiktop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiktop.mapper.ClassesMapper;
import com.tiktop.mapper.ScheduleMapper;
import com.tiktop.model.Schedule;

@Repository("scheduleRepository")
public class ScheduleDaoImpl implements ScheduleDao {

	@Autowired
	ScheduleMapper scheduleMapper;
	
	@Autowired
	ClassesMapper classesMapper;
	
	public void save(Schedule schedule) {
		scheduleMapper.save(schedule);
	}
	
	public List<Schedule> findSchedule(Schedule schedule) {
		List<Schedule> schedules = scheduleMapper.findSchedule(schedule);
		return schedules;
	}
	
	public void delete(Integer id) {
		scheduleMapper.delete(id);
	}
	
	public void update(Schedule schedule) {
		scheduleMapper.update(schedule);
	}
}
