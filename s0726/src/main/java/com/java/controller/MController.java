package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.Member;
import com.java.service.MService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MController {
	@Autowired MService mService;
	@Autowired HttpSession session;
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	@PostMapping("/login")
	public String dologin(Member mdto) {
		System.out.println(mdto.getId());
		mService.selectLogin(mdto);
		
		return "redirect:/";
	}
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
}
