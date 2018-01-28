package com.accolite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.accolite.model.Booking;

@Repository
public class BookingDao {

	// Add delete booking - Less Priority
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void addBooking(Booking booking) {
		String sql = "INSERT INTO bookings (bookingID, title, bookingRoomID, startDate, endDate, description, bookingUserID) VALUES (?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, booking.getId(), booking.getTitle(), booking.getRoomId(), booking.getStart(),
				booking.getEnd(), booking.getDescription(), booking.getLocationID(), booking.getUserId());
	}

	public List<Booking> getAllBookings(final int locationId) {
		String sql = "SELECT * FROM bookings WHERE locationID = ?";
		return jdbcTemplate.query(sql, new PreparedStatementSetter() {
			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setInt(1, locationId);
			}
		}, new BookingMapper());
	}

}

class BookingMapper implements RowMapper<Booking> {
	public Booking mapRow(ResultSet rs, int arg1) throws SQLException {
		Booking booking = new Booking();
		booking.setId(rs.getInt("bookingID"));
		booking.setTitle(rs.getString("title"));
		booking.setRoomId(rs.getInt("bookingRoomID"));
		booking.setStart(rs.getDate("startDate"));
		booking.setEnd(rs.getDate("endDate"));
		booking.setDescription(rs.getString("description"));
		booking.setLocationID(rs.getInt("locationID"));
		booking.setUserId(rs.getInt("bookingUserID"));
		return booking;
	}
}
