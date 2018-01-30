package com.accolite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.accolite.model.SpecialRequest;

@Repository
public class SpecialRequestDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<SpecialRequest> getAllRequests() {
		String sql = "SELECT * FROM specialRequests";
		return jdbcTemplate.query(sql, new SpecialRequestMapper());
	}

	public void deleteSpecialRequest(SpecialRequest specialRequest) {
		String sql = "DELETE FROM specialRequests WHERE specialReqBookingID = ?";
		jdbcTemplate.update(sql, specialRequest.getId());
	}

}

class SpecialRequestMapper implements RowMapper<SpecialRequest> {
	public SpecialRequest mapRow(ResultSet rs, int arg1) throws SQLException {
		SpecialRequest specialRequest = new SpecialRequest();
		specialRequest.setId(rs.getInt("specialReqBookingID"));
		specialRequest.setStatus(rs.getBoolean("specialReqStatus"));
		return specialRequest;
	}
}