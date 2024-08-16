package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventBoardController {
	@Autowired 
	@RequestMapping("/board/eventlist")
	public String eventlist() {
		return "board/eventlist";
	}
	@RequestMapping("/board/eventview")
	public String eventview() {
		return "board/eventview";
	}
}
