package com.java.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.Member;
import com.java.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MController {
	@Autowired
	HttpSession session;
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/member/memList")
	public String memList(Model model) {
		ArrayList<Member> list = memberService.memberSelectAll();
		// 확인용 . 
		System.out.println(list.get(0).getName());
		model.addAttribute("list", list);
		return "member/memList";
	}
	
	
	
	
	@RequestMapping("/member/join")
	public String join() {
		return "member/join";
	}
	@RequestMapping("/member/doJoin")
	public String doJoin(Member member,String[] hobby, Model model) {
		// 넘어온 정보 콘솔창에 출력해보기 
		System.out.println(member.getId());
		System.out.println(member.getPw());
		System.out.println(member.getName());
		System.out.println(member.getGender());
		System.out.println(member.getPhone());
		String hobbys="";
		for(int i = 0; i<hobby.length;i++) {
			if(i==0) hobbys += hobby[i];
			else hobbys +=", "+ hobby[i];
		} 
		member.setHobbys(hobbys);
		System.out.println(member.getHobbys());
		model.addAttribute("member", member);
		
		
		
		return "member/doJoin";
	}
	
	
	
	@RequestMapping("/member/login")
	public String login() {
		return "member/login";
	}
	@RequestMapping("/member/logout")
	public String logout(Model model) {
		// 세션삭제 
		session.invalidate();
		model.addAttribute("logout",1);
		return "member/logout";
	}
	@RequestMapping("/member/doLogin")
	public String doLogin(String id, String pw, Model model) {
		System.out.println("id: "+id);
		System.out.println("pw: "+pw);
		if(id.equals("aaa") && pw.equals("1111")) {
			// 세션에 추가 
			session.setAttribute("sessionId", id);
			session.setAttribute("sessionName", "홍길동");
			model.addAttribute("loginCheck",1);
		}else {
			model.addAttribute("loginCheck",0);
		}
		return "member/doLogin";
	}
	

}
