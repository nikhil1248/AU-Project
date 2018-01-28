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
import com.accolite.model.User;

@Repository
public class UserDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void registerUser(User user) {

		String sql = "insert into user (userID, username, userPassword, userLocID) values(?,?,?,?)";
		jdbcTemplate.update(sql, user.getUserId(), user.getUsername(), user.getPassword(), user.getLocationId());
	}

	public User validateDetails(final User user) {

		String sql = "SELECT * FROM user WHERE username = ? AND userPassword = ?";

		List<User> users = jdbcTemplate.query(sql, new PreparedStatementSetter() {

			public void setValues(java.sql.PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
			}
		}, new UserMapper());
		if (!users.isEmpty())
			return users.get(0);
		else
			return null;
	}

	public void addSpecialRequest(Booking booking) {
		String sql = "INSERT INTO specialRequests (specialReqBookingID) VALUES (?)";
		jdbcTemplate.update(sql, booking.getId());
	}
}

class UserMapper implements RowMapper<User> {
	public User mapRow(ResultSet rs, int arg) throws SQLException {
		User user = new User();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("userPassword"));
		user.setLocationId(rs.getInt("userLocID"));
		user.setUserId(rs.getInt("userID"));
		user.setAdmin(rs.getBoolean("isAdmin"));
		return user;
	}
}
