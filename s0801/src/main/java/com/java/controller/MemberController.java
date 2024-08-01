package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	@RequestMapping("/member/login")
	public String login() {
		return "member/login";
	}
	@RequestMapping("/member/step02")
	public String step02() {
		return "member/step02";
	}
}
