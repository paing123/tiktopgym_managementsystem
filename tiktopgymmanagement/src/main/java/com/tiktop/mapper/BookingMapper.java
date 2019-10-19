package com.tiktop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tiktop.model.Booking;
import com.tiktop.model.Schedule;

@Mapper
public interface BookingMapper {
	public List<Schedule> findScheduleByDate(@Param("schedule") Schedule schedule);

	public List<Schedule> scheduleBookingDetail(@Param("schedule") Schedule schedule);
	
	public void save(@Param("booking") Booking booking);
	
	public List<Booking> findPackageBooking(@Param("booking") Booking booking);

	public List<Booking> findScheduleBooking(@Param("booking") Booking booking);
	
	public List<Booking> findAllBooking(@Param("booking") Booking booking);

	public void confirmBooking(@Param("booking") Booking booking);
	
	public void delete(@Param("id") Integer id);
}
