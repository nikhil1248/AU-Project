package com.accolite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.model.SpecialRequest;
import com.accolite.service.SpecialRequestService;

@RestController
@RequestMapping("/api/special")
public class SpecialRequestController {

	@Autowired
	public SpecialRequestService specialRequestService;

	@RequestMapping(value = "/getallrequests", method = RequestMethod.GET)
	public List<SpecialRequest> getAllRequests() {
		return specialRequestService.getAllRequests();
	}
}

