package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MController {
	@RequestMapping("/login")
	public String index() {
		return "member/login";
	}
}
