package com.accolite.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.accolite.dao.AdminDao;
import com.accolite.dao.BookingDao;
import com.accolite.dao.RoomDao;
import com.accolite.dao.SpecialRequestDao;
import com.accolite.dao.UserDao;

@Configuration
public class SpringJdbcConfiguration {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/accounts");// change url
		dataSource.setUsername("root");// change userid
		dataSource.setPassword("root");// change pwd

		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}

	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setJdbcTemplate(jdbcTemplate());
		return userDao;
	}

	@Bean
	public AdminDao adminDao() {
		AdminDao adminDao = new AdminDao();
		adminDao.setJdbcTemplate(jdbcTemplate());
		return adminDao;
	}
	
	@Bean
	public BookingDao bookingDao() {
		BookingDao bookingDao = new BookingDao();
		bookingDao.setJdbcTemplate(jdbcTemplate());
		return bookingDao;
	}
	
	@Bean
	public RoomDao roomDao() {
		RoomDao roomDao = new RoomDao();
		roomDao.setJdbcTemplate(jdbcTemplate());
		return roomDao;
	}
	
	@Bean
	public SpecialRequestDao specialRequestDao() {
		SpecialRequestDao specialRequestDao = new SpecialRequestDao();
		specialRequestDao.setJdbcTemplate(jdbcTemplate());
		return specialRequestDao;
	}
}