package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FController {
	
	@RequestMapping("/")
	public String index() {
		return "main";
	}
	@RequestMapping("/axaj01")
	public String axaj01() {
		return "ajax01";
	}
	
}
