package com.accolite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accolite.model.Room;

@Repository
public class AdminDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addRoom(Room room) {
		String sql = "INSERT INTO rooms (roomID, isAvailable, roomLocID) VALUES (?,?,?)";
		jdbcTemplate.update(sql, room.getRoomId(), room.isAvailable(), room.getLocationId());
	}

	public void toggleAvailability(int roomId) {
		String sql = "UPDATE rooms SET isAvailable = !isAvailable WHERE roomID = ?";
		jdbcTemplate.update(sql, roomId);
	}

}
