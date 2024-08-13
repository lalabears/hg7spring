package com.java.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		session.invalidate();
		return "redirect:/index";
	}
	@RequestMapping("/member/mlist")
	public String mlist(Model model) {
		List<Member> memList = mservice.selectAll();
		model.addAttribute("mlist",memList);
		return "member/mlist";
	}
	
	@GetMapping("/member/join")
	public String join() {
		return "member/join";
	}
	@PostMapping("/member/join")
	public String dojoin(Member member, String[] hobby) {
		System.out.println(member.getGender());
		System.out.println(Arrays.toString(hobby));
		String hb = "";
		for(int i = 0 ; i < hobby.length ; i ++ ) {
			if(i==0) hb += hobby[i];
			else hb += ", "+ hobby[i];
		}
		member.setHobbys(hb);
		mservice.insertOne(member);
		
		return "redirect:/member/login";
	}
	@GetMapping("/member/updateMem")
	public String updateMem(Model model) {
		// 1. 로그인 한사람이 로그인 한 정보로 보여짐
		String id = (String)session.getAttribute("sessionId");
		Member member = mservice.selectOne(id);
		model.addAttribute("mem",member);
		return "member/updateMem";
	}
	@PostMapping("/member/updateMem")
	public String doupdateMem() {
		return "member/updateMem";
	}
}
