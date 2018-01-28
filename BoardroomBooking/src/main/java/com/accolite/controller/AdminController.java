package com.accolite.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accolite.model.Room;
import com.accolite.model.SpecialRequest;
import com.accolite.service.AdminService;

@Controller
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	public AdminService adminService;

	@RequestMapping(value = "/addroom", method = RequestMethod.POST)
	public void addNewRoom(@RequestBody Room room) {
		adminService.addNewRoom(room);
	}

	// Change the past bookings as well - Less Priority
	@RequestMapping(value = "/changeavail", method = RequestMethod.GET)
	public void changeAvailablity(@PathParam(value = "id")  int roomId) {
		adminService.changeAvailability(roomId);
	}

	@RequestMapping(value = "/acceptrequest", method = RequestMethod.POST)
	public void acceptSpecialRequest(@RequestBody SpecialRequest specialRequest) {
		adminService.respondSpecialRequest(specialRequest, true);
	}
	
	@RequestMapping(value = "/rejectrequest", method = RequestMethod.POST)
	public void rejectSpecialRequest(@RequestBody SpecialRequest specialRequest) {
		adminService.respondSpecialRequest(specialRequest, false);
	}
}
