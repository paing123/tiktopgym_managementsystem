package com.tiktop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tiktop.mapper.BookingMapper;
import com.tiktop.model.Booking;
import com.tiktop.model.Schedule;

@Repository("bookingDao")
public class BookingDaoImpl implements BookingDao {

	@Autowired
	BookingMapper bookingMapper;
	
	@Override
	public List<Schedule> findScheduleByDate(Schedule schedule) {
		List<Schedule> schedules=bookingMapper.findScheduleByDate(schedule);
		return schedules;
	}
	
	public List<Booking> findScheduleBooking(Booking booking){
		List<Booking> bookings = bookingMapper.findScheduleBooking(booking);
		return bookings;
	}
	
	public List<Booking> findPackageBooking(Booking booking){
		List<Booking> bookings = bookingMapper.findPackageBooking(booking);
		return bookings;
	}
	
	public List<Booking> findTrainerBooking(Booking booking){
		List<Booking> bookings = bookingMapper.findTrainerBooking(booking);
		return bookings;
	}
	
	public List<Booking> findAllBooking(Booking booking){
		List<Booking> bookings = bookingMapper.findAllBooking(booking);
		return bookings;
	}
	
	@Override
	public List<Schedule> scheduleBookingDetail(Schedule schedule) {
		List<Schedule> bookSchedule = bookingMapper.scheduleBookingDetail(schedule);
		return bookSchedule;
	}
	
	public void save(Booking booking) {
		bookingMapper.save(booking);
	}
	
	public void confirmBooking(Booking booking) {
		bookingMapper.confirmBooking(booking);
	}
	
	public void delete(Integer id) {
	bookingMapper.delete(id);
	}
}
