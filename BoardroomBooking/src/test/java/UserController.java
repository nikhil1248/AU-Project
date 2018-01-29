package com.accolite.pizzeria.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/user")
public class UserController {
	// Handles GET or POST Request
		@RequestMapping("/one")
		public @ResponseBody String handlerOne() {
			return "<h1>Inside handlerOne() Method Of MyController</h1>";
		}
		
		// Handles POST Request For login form
				@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
			    @ResponseStatus(HttpStatus.OK)
				public ModelAndView handleFormRequest(HttpServletRequest request,HttpServletResponse response) throws IOException {
					//condition checking
					response.setContentType("text/plain");
				    
					System.out.println("Logging in");
					//check for login
					if(request.getParameter("username").equals("pawan")&&request.getParameter("password").equals("pass"))
				    {
				    	request.getSession().setAttribute("Userid", "1233Ph");
				    	request.getSession().setAttribute("SessionCreated", "true");
				    	System.out.print("Success login");
			            return new ModelAndView("redirect:" + "/main");
					}
				    return new ModelAndView("redirect:" + "/failure");					
				}
}
