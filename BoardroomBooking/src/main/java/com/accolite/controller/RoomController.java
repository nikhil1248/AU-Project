package com.accolite.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.model.Room;
import com.accolite.service.RoomService;

@RestController
@RequestMapping("/api/room")
public class RoomController {

	@Autowired
	public RoomService roomService;

	@RequestMapping(value = "/getallrooms", method = RequestMethod.GET)
	public List<Room> getAllRooms(@PathParam(value="locationId") int locationId) {
		return roomService.getAllRooms(locationId);
	}
}
