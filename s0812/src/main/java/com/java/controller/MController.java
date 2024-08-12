package com.java.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String dologin(String id, String pw, Model model) {
		System.out.println(id);
		System.out.println(pw);
		Member mem = mservice.login(id, pw);
		if(mem == null) {
			model.addAttribute("result", 2);
		}else {
			model.addAttribute("result", 1);
			session.setAttribute("sessionId", mem.getId());
			session.setAttribute("sessionName", mem.getName());
		}
		return "member/login";
	}
	@GetMapping("/member/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/index";
	}
	
	@GetMapping("/member/step02")
	public String step02() {
		return "member/step02";
	}
	@PostMapping("/member/step02")
	public String dostep02(String[] agree, Model model) {
		
		System.out.println(Arrays.toString(agree));
		// 동의의 개수가 3개일때(모두동의시) step3으로 넘어감
		if(agree.length == 3 ) {
			model.addAttribute("agreeAll",1);
			return "member/step03";
		}else {
			return "member/step02";
		}
	}
	
	@PostMapping("/member/idCheck")
	@ResponseBody // ajax 연결시, 데이터보낼때 사용
	public String idCheck(String id) {
		System.out.println(id);
		String name = mservice.idCheck(id);
		System.out.println(name);
		return "성공";
	}
	
	
	
	
	
	
	@GetMapping("/member/step03")
	public String step03() {
		return "member/step03";
	}
	@GetMapping("/member/change_info")
	public String change_info() {
		return "member/change_info";
	}

}
