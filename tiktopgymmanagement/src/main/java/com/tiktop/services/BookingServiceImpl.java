package com.tiktop.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiktop.dao.BookingDao;
import com.tiktop.model.Booking;
import com.tiktop.model.Schedule;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingDao bookingDao;

	@Override
	public List<Schedule> findScheduleByDate(Schedule schedule) {
		List<Schedule> schedules = bookingDao.findScheduleByDate(schedule);
		return schedules;
	}

	public List<Booking> findScheduleBooking(Booking booking) {
		List<Booking> bookings = bookingDao.findScheduleBooking(booking);
		return bookings;
	}

	public List<Booking> findAllBooking(Booking booking) {
		List<Booking> bookings = bookingDao.findAllBooking(booking);
		return bookings;
	}
	
	public List<Booking> findPackageBooking(Booking booking) {
		List<Booking> bookings = bookingDao.findPackageBooking(booking);
		return bookings;
	}
	
	@Override
	public List<Schedule> scheduleBookingDetail(Schedule schedule) {
		return bookingDao.scheduleBookingDetail(schedule);
	}

	public void save(Booking booking) {
		bookingDao.save(booking);
	}

	public void confirmBooking(Booking booking) {
		bookingDao.confirmBooking(booking);
	}

	public void delete(Integer id) {
		bookingDao.delete(id);
	}
	
	@Override
	public LocalDate checkExpiredDate(int duration) {
		LocalDate expDate = LocalDate.now().plusMonths(duration).minusDays(1);
		return expDate;
	}

	@Override
	public List<Booking> findTrainerBooking(Booking booking) {
		return bookingDao.findTrainerBooking(booking);
	}
}
