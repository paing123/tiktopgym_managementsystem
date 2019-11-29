package com.tiktop.services;

import java.util.Arrays;
import java.util.Collections;
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
		schedule = convertDateFormat(schedule);
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
	
	@Override
	public Schedule convertDateFormat(Schedule schedule) {
		String date = schedule.getCreateDate();
		String dates[]=date.split("/");
		Collections.reverse(Arrays.asList(dates)); 
		String newDate = "";
		for (String str : dates) {
			newDate=newDate+"-"+str;
		}
		schedule.setCreateDate(newDate.substring(1));
		return schedule;
	}
}
