package com.accolite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.dao.BookingDao;
import com.accolite.dao.UserDao;
import com.accolite.model.Booking;
import com.accolite.model.User;

@Service
public class UserService {

	@Autowired
	public UserDao userDao;
	
	@Autowired
	public BookingDao bookingDao;

	public User userLogin(User user) {
		return userDao.validateDetails(user);
	}

	public void userRegister(User user) {
		userDao.registerUser(user);
	}

	public void userBookingRequest(Booking booking) {
		bookingDao.addBooking(booking);
	}
	
	public void userSpecialRequest(Booking booking) {
		userDao.addSpecialRequest(booking);
	}
}