package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.MemberDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
	//session
	@Autowired
	HttpSession session;
	
	@RequestMapping("/mForm")
	public String mForm() {
		return "member/mForm";
	}
	@RequestMapping("/doMForm")
	public String doMForm(MemberDto mdto,String[] hobbys ,Model model) {
		// 확인해보기 
		//System.out.println("이름"+mdto.getName());
		String hobby = "";
		for(int i = 0 ; i < hobbys.length ; i ++) {
			if(i==0) hobby+=hobbys[i];
			else hobby+=","+hobbys[i];
		}
		System.out.println(hobby);
		mdto.setHobby(hobby); // mdto 객체에 취미 저장 
		
		model.addAttribute("mdto", mdto);
		return "member/doMForm";
	}
	@RequestMapping("/updateMForm")
	public String updateMForm(MemberDto mdto, 
			Model model) {
		
		model.addAttribute("member", mdto);
		
		return "member/updateMForm";
	}
	
	//파일의 위치
	@RequestMapping("/member1")
	public String member1() {
		// 주소
		return "member/member1";
	}
	//파일의 위치
	@RequestMapping("/login")
	public String login() {
		// 주소
		return "member/login";
	}
	@RequestMapping("/doLogin")
	public String doLogin(String id, String pw, Model model) {
		System.out.println("id: "+ id);
		System.out.println("pw: "+ pw);
		
		//HttpSession session = request.getSession(); 대신에
		// iv에 @Autowried로 HttpSession session; 선언한다. 
		
		//  만약에 db에 aaa 1111이 있다면.  
		if(id.equals("aaa") && pw.equals("1111")) {
			// 세션 설정하기 
			session.setAttribute("sessionId", id);
		}
		return "member/doLogin";
	}
//	public String doLogin(MemberDto mdto, Model model) {
//		model.addAttribute("id", mdto.getId());
//		model.addAttribute("pw", mdto.getPw());
//		return "member/doLogin";
//	}
//	public String doLogin(@RequestParam(defaultValue="admin") String id,
//			              @RequestParam("pw")String pw, Model model) {
//		model.addAttribute("id", id);
//		model.addAttribute("pw", pw);
//		
//		
//		return "member/doLogin";
//	}
	
	
	
	//회원가입창을 보여줌
	@RequestMapping("/memberForm")
	public String memberForm() {
		// 주소
		return "member/memberForm";
	}
	//회원가입 후 전송버튼을 눌럿을때 이동 
	@RequestMapping("/doMemberForm")
	public String doMemberForm(MemberDto mdto
			,HttpServletRequest request,Model model) {
	     //	import org.springframework.ui.Model;
//		String id = request.getParameter("id");
//		String pw = request.getParameter("pw");
//		String name = request.getParameter("name");
//		String gender = request.getParameter("gender");
//		
//		System.out.println(id);System.out.println(pw);System.out.println(name);	System.out.println(gender);
		
		// form태그속 name 명과 dto의 변수명이 같은경우
		model.addAttribute("member",mdto);

		//MemberDto member = new MemberDto(id,pw,name,gender);
		
		// model에 정보 넣기 
		//model.addAttribute("member",member);
		
//		model.addAttribute("id",id);
//		model.addAttribute("pw",pw);
//		model.addAttribute("name",name);
//		model.addAttribute("gender",gender);
		
		return "member/doMemberForm";
	}

}
