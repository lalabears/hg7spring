package com.java.controller;

import java.util.ArrayList;

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

@RequestMapping("/member")
public class MController {
	@Autowired  HttpSession session; 
	@Autowired	MService mService;
	
	@GetMapping("/join")
	public String join() {
		return "member/join";
	}
	@PostMapping("/join")
	public String doJoin(Member member,String[] hobby, Model model) {
		String hobbys="";
		for(int i =0;i<hobby.length;i++) {
			if(i==0) hobbys += hobby[i];
			else hobbys += ", "+ hobby[i];
		}
		member.setHobbys(hobbys);
		mService.insertMember(member);
		return "redirect:/";
	}
	//전체멤버확인
	@RequestMapping("/mlist")
	public String mlist(Model model) {
		ArrayList<Member> list = mService.selectMemberAll();
		//확인용
		System.out.println(list.get(0).getId());
		// 모델에 객체 전달
		model.addAttribute("list",list);
		return "member/mlist";
	}
	// login.jsp 기본페이지 여는 부분
	@RequestMapping("/login")
	public String login() {
		return "member/login";
	}
	// login페이지에서 submit버튼을 눌럿을때 연결되는 부분
	@RequestMapping("/doLogin")
	public String dologin(Member member, Model model) {
		// 확인용
		System.out.println("doLogin id: "+member.getId());
		System.out.println("doLogin pw: "+member.getPw());
		Member m = mService.selectLogin( member.getId(), member.getPw() );
		//System.out.println("doLogin db연결 확인 id : "+m.getId());
		if(m!=null) {
			session.setAttribute("sessionId", m.getId());
			session.setAttribute("sessionName", m.getName());
			model.addAttribute("loginCk",1);
		}else {
			model.addAttribute("loginCk",0);
		}
		return "member/doLogin";
	}
	@RequestMapping("/logout")
	public String logout(Model model) {
		
		// 세션 전부 삭제 
		session.invalidate();  
		//model.addAttribute("logout",1);
		//return "member/logout";
		return "redirect:/";
	}

}
