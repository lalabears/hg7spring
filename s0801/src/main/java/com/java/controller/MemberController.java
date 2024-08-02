package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.Member;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	@Autowired MemberService mservice;
	@Autowired HttpSession session; 
	@GetMapping("/member/login")
	public String login() {
		return "member/login2";
	}
	@PostMapping("/member/login")
	public String dologin(Member member) {
	//	System.out.println(member.getId());
	//	System.out.println(member.getPw());
		Member mem = mservice.selectLogin(member);
		if(mem != null ) {
			session.setAttribute("sessionId", mem.getId());
			session.setAttribute("sessionName", mem.getName());
		}
		return "redirect:/";
	}
	@GetMapping("/member/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	
	
	@RequestMapping("/member/step02")
	public String step02() {
		return "member/step02";
	}
	@RequestMapping("/member/step03")
	public String step03() {
		return "member/step03";
	}
	
	
	
	@PostMapping("/member/login2")
	@ResponseBody
	public String login2(Member member) {
		System.out.println("post - ajax");
		Member mem = mservice.selectLogin(member);
		if (mem!=null) {
			session.setAttribute("sessionId", mem.getId());
			session.setAttribute("sessionName", mem.getName());
			System.out.println(session.getAttribute("sessionId"));
			return "success";
		}
		else 		return "fail";
	}
}
