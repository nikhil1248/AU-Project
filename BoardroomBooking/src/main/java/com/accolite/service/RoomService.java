package com.accolite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.dao.RoomDao;
import com.accolite.model.Room;

@Service
public class RoomService {
	@Autowired
	RoomDao roomDao;
	
	public List<Room> getAllRooms(int locationId) {
		return roomDao.getAllRooms(locationId);
	}
}
