package com.accolite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.dao.AdminDao;
import com.accolite.dao.BookingDao;
import com.accolite.dao.SpecialRequestDao;
import com.accolite.model.Room;
import com.accolite.model.SpecialRequest;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	private BookingDao bookingDao;
	private SpecialRequestDao specialRequestDao;

	public void addNewRoom(Room room) {
		adminDao.addRoom(room);
	}

	public void changeAvailability(int roomId) {
		adminDao.toggleAvailability(roomId);
	}

	public void respondSpecialRequest(SpecialRequest specialRequest, boolean response) {
		specialRequest.setStatus(response);
		if (response) {
			bookingDao.addBooking(specialRequest);
		}
		specialRequestDao.deleteSpecialRequest(specialRequest);
	}
}
