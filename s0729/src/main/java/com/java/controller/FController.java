package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FController {
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	@RequestMapping("/blist")
	public String blist() {
		return "blist";
	}
	@RequestMapping("/bikeData")
	public String bikeData() {
		return "bikeData";
	}
	@RequestMapping("/sportsData")
	public String sportsData() {
		return "sportsData";
	}
	@RequestMapping("/subwayData")
	public String subwayData() {
		return "subwayData";
	}
	
}
