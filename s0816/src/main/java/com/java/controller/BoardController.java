package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	@RequestMapping("/board/blist")
	public String blist() {
		return "board/blist";
	}
	@RequestMapping("/board/bview")
	public String bview() {
		return "board/bview";
	}
}
