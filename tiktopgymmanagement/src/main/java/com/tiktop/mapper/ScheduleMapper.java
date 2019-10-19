package com.tiktop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tiktop.model.Schedule;

@Mapper
public interface ScheduleMapper {
	public void save(@Param("schedule") Schedule schedule);

	public List<Schedule> findSchedule(@Param("schedule") Schedule schedule);

	public void update(@Param("schedule") Schedule schedule);

	public void delete(@Param("id") Integer id);
}
