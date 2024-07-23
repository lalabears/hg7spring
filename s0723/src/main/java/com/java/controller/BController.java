package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BController {
	@RequestMapping("/board/notice")
	public String notice() {
		return "board/notice";
	}
	@RequestMapping("/board/noticeView")
	public String noticeView() {
		return "board/noticeView";
	}
}
