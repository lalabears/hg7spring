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
public class MController {
	
	@Autowired MService mservice; 
	@Autowired HttpSession session; 
	
	@GetMapping("/member/login")
	public String login() {
		return "member/login";
	}
	@PostMapping("/member/login")
	public String dologin(Member member) {
		
		Member mem = mservice.selectLogin(member);
		if(mem != null) {
			session.setAttribute("sessionId", mem.getId());
			session.setAttribute("sessionName", mem.getName());
		}
		return "redirect:/index";
	}
	
	
	
	
	@RequestMapping("/member/logout")
	public String logout() {
		return "redirect:/index";
	}
	@RequestMapping("/member/join")
	public String join() {
		return "member/join";
	}
	@RequestMapping("/member/mlist")
	public String mlist() {
		return "member/mlist";
	}
	@RequestMapping("/member/updateMem")
	public String updateMem() {
		return "member/updateMem";
	}
}
