package com.accolite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.dao.BookingDao;
import com.accolite.model.Booking;

@Service
public class BookingService {
	
	@Autowired
	BookingDao bookingDao;
	
	public List<Booking> getAllBookings(int locationId) {
		return bookingDao.getAllBookings(locationId);
	}
}
