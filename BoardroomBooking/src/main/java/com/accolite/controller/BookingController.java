package com.accolite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.model.Booking;
import com.accolite.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	public BookingService bookingService;

	@RequestMapping(value = "/getallbookings/{id}", method = RequestMethod.GET)
	public List<Booking> getAllBookings(@PathVariable int id) {
		return bookingService.getAllBookings(id);
	}
}
