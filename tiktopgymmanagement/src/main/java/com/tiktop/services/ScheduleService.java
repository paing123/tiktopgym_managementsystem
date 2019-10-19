package com.tiktop.services;

import java.util.List;

import com.tiktop.model.Schedule;

public interface ScheduleService {

	void save(Schedule schedule);

	List<Schedule> findSchedule(Schedule schedule);
	
	void delete(Integer id);

	void update(Schedule schedule);
}
