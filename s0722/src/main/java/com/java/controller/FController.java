package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import com.java.dto.Member;
import com.java.service.MService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FController {
	
	@Autowired HttpSession session;
	@Autowired MService mservice;
	
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	@GetMapping("/login")
	public String login() {
		// 로그인 할 수 있는 화면 열기
		return "login";
	}
	@PostMapping("/login")
	public String dologin(String id, String pw, Model model ) {
		// login.jsp에서 submit버튼을 눌렀을때 데이터들이 전송됨. 
		Member member = mservice.selectLogin(id, pw);
		if(member != null) {
			session.setAttribute("sessionId", member.getId());
			session.setAttribute("sessionName", member.getName());
			model.addAttribute("loginCk",1);
		}else {
			model.addAttribute("loginCk",0);
		}
		return "login";
	}
	@RequestMapping("/logout")
	public String logout() {
		session.invalidate();// 세션 전체 삭제하기
		return "redirect:index";
	}
	
}
