package com.accolite.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class MainController {

	
	@RequestMapping
	public String index() {
		return "index";
	}
}
