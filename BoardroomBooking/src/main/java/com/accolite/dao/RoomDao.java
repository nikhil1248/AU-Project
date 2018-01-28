package com.accolite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.accolite.model.Room;

@Repository
public class RoomDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Room> getAllRooms(final int locationId) {
		String sql = "select * from rooms WHERE roomLocID = ?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setInt(1, locationId);
			}
		}, new RoomMapper());
	}

	public void setAvailability(Room room, boolean availability) {
		String sql = "UPDATE rooms SET isAvailable = ? WHERE roomID = ?";
		jdbcTemplate.update(sql, availability, room.getRoomId());
	}

	public Room getRoomByID(final int roomID) {
		String sql = "select * from rooms where roomID = ?";

		List<Room> rooms = jdbcTemplate.query(sql, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setInt(1, roomID);
			}
		}, new RoomMapper());
		return rooms.isEmpty() ? null : rooms.get(0);
	}

}

class RoomMapper implements RowMapper<Room> {
	public Room mapRow(ResultSet rs, int arg1) throws SQLException {
		Room room = new Room();
		room.setRoomId(rs.getInt("roomID"));
		room.setAvailable(rs.getBoolean("isAvailable"));
		room.setLocationId(rs.getInt("roomLocID"));
		return room;
	}
}
