package com.accolite.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.accolite.model.Booking;
import com.accolite.model.User;
import com.accolite.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserController {

	private static final Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	public UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody User login(@RequestBody User user) {

		User temp = userService.userLogin(user);
		if (null != temp) {
			log.info("User " + temp.getUsername() + " logged in, Is Admin : " + temp.isAdmin());
			return temp;
		} else {
			log.warn("User not found");
			return null;
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void register(@RequestBody User user) {

		userService.userRegister(user);
		log.info("A new User, " + user.getUsername() + " signed up.");
	}

	@RequestMapping(value = "/booking", method = RequestMethod.POST)
	public void userBookingRequest(@RequestBody Booking booking) {
		userService.userBookingRequest(booking);
		log.info("Created a new booking.");
	}

	@RequestMapping(value = "/specialrequest", method = RequestMethod.POST)
	public void recieveSpecialRequest(@RequestBody Booking booking) {
		userService.userSpecialRequest(booking);

	}
}
