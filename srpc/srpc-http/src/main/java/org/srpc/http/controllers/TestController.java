package org.srpc.http.controllers;

import org.springframework.stereotype.Controller;
import org.srpc.http.servlet.HttpServletRequest;
import org.srpc.http.servlet.HttpServletResponse;
import org.srpc.http.servlet.mapping.RequestMapping;

@Controller
public class TestController {
	@RequestMapping("/hello")
	public String test(HttpServletRequest request, HttpServletResponse response){
		return null;
	}

}
