package com.java.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.Member;
import com.java.service.MService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MController {
	
	@Autowired MService mService;
	@Autowired HttpSession session;
	
	@RequestMapping("/member/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/member/login")
	public String login() {
		return "member/login";
	}
	
	// -------- jsp 형태 ------------------------
	@PostMapping("/member/login")
	public String dologin(Member member, Model model) {
		System.out.println("id : "+member.getId());
		System.out.println("pw : "+ member.getPw());
		// 1은 성공, 0은 실패 
		int result = mService.login(member);
		model.addAttribute("result",result);
		return "redirect:/";
	}
	// -------- ajax 형태 ------------------------
	@PostMapping("/member/ajaxLogin")
	@ResponseBody
	public String ajaxlogin(Member member) {
		System.out.println("id : "+ member.getId());
		System.out.println("pw : "+ member.getPw());
		// 1은 성공, 0은 실패 
		int result = mService.login(member);
		
		return ""+result;
	}
	
	
	
	@PostMapping("/member/selectAll")
	@ResponseBody
	public ArrayList<Member> selectAll() {
		ArrayList<Member> list = mService.memberSelectAll();
		System.out.println("member select all : "+list.get(0).getId());
		return list;
	}
	
	

}
