package com.accolite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.dao.SpecialRequestDao;
import com.accolite.model.SpecialRequest;

@Service
public class SpecialRequestService {
	
	@Autowired
	SpecialRequestDao specialRequestDao;

	public List<SpecialRequest> getAllRequests() {
		return specialRequestDao.getAllRequests();
	}
}
