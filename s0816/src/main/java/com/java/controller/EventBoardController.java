package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventBoardController {
	@RequestMapping("/board/eventlist")
	public String eventlist() {
		return "board/eventlist";
	}
	@RequestMapping("/board/eventview")
	public String eventview() {
		return "board/eventview";
	}
}
