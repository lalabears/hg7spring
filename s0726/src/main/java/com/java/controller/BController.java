package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BController {
	@RequestMapping("/notice")
	public String index() {
		return "board/notice";
	}
}
