package com.tiktop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiktop.dao.ScheduleDao;
import com.tiktop.model.Schedule;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleDao scheduleDao;

	@Override
	public void save(Schedule schedule) {
		scheduleDao.save(schedule);
	}
	
	@Override
	public List<Schedule> findSchedule(Schedule schedule) {
		return scheduleDao.findSchedule(schedule);
	}
	
	@Override
	public void delete(Integer id) {
		scheduleDao.delete(id);
	}

	@Override
	public void update(Schedule schedule) {
		scheduleDao.update(schedule);
	}
}
