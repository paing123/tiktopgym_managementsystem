package com.tiktop.dao;

import java.util.List;

import com.tiktop.model.Booking;
import com.tiktop.model.Schedule;

public interface BookingDao {
	List<Schedule> findScheduleByDate(Schedule schedule);
	
	List<Schedule> scheduleBookingDetail(Schedule schedule);

	void save(Booking booking);
	
	List<Booking> findScheduleBooking(Booking booking);
	
	List<Booking> findPackageBooking(Booking booking);
	
	List<Booking> findTrainerBooking(Booking booking);
	
	List<Booking> findAllBooking(Booking booking);
	
	void confirmBooking(Booking booking);
	
	void delete(Integer id);
}
