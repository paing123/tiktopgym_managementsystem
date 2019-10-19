package com.tiktop.dao;

import java.util.List;

import com.tiktop.model.Schedule;

public interface ScheduleDao {

	void save(Schedule schedule);
	
	 List<Schedule> findSchedule(Schedule schedule);
	 
	 void delete(Integer id);
	 
	 void update(Schedule schedule);
}
