package com.accolite.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accolite.model.SpecialRequest;
import com.accolite.service.SpecialRequestService;

@Controller
@RequestMapping("/api/special")
public class SpecialRequestController {

	@Autowired
	public SpecialRequestService specialRequestService;

	@RequestMapping(value = "/getallrequests", method = RequestMethod.GET)
	public List<SpecialRequest> getAllRequests(@PathParam(value="locationId") int locationId) {
		return specialRequestService.getAllRequests(locationId);
	}
}

