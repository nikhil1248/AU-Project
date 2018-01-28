package com.accolite.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accolite.model.Booking;
import com.accolite.service.BookingService;

@Controller
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	public BookingService bookingService;

	@RequestMapping(value = "/getallbookings", method = RequestMethod.GET)
	public List<Booking> getAllBookings(@PathParam(value="locationId") int locationId) {
		return bookingService.getAllBookings(locationId);
	}
}
