package com.java.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.Board;
import com.java.dto.KakaoDto;
import com.java.dto.Member;
import com.java.service.MService;

import jakarta.servlet.http.HttpSession;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
@Controller
public class FController {
	@Autowired MService mservice;
	@Autowired HttpSession session;
	@RequestMapping("/")
	public String index(Model model) {
		
		ArrayList<Member> mList = new ArrayList<>();
		Member m1 = new Member();
		m1.setId("aaa");
		m1.setPw("1111");
		m1.setName("홍길동");
		Member m2 = new Member();
		m2.setId("aaa");
		m2.setPw("2222");
		m2.setName("홍길동");
		Member m3 = new Member();
		m3.setId("aaa");
		m3.setPw("3333");
		m3.setName("홍길동");
		Member m4 = new Member();
		m4.setId("bbb");
		m4.setPw("4444");
		m4.setName("유관순");
		
		Member m5 = new Member();
		m5.setId("ccc");
		m5.setPw("5555");
		m5.setName("김구");
		
		Member m6 = new Member();
		m6.setId("ddd");
		m6.setPw("6666");
		m6.setName("강감찬");
		
		mList.add(m1);
		mList.add(m2);
		mList.add(m3);
		mList.add(m4);
		mList.add(m5);
		mList.add(m5);
		mList.add(m6);
		
		
		
		
		ArrayList<Board> blist = mservice.selectAll();
		
		
		
		model.addAttribute("mlist",mList);
		model.addAttribute("blist",blist);
		
		
		
		
		
		return "index";
	}
	@RequestMapping("/kakao")
	public String kakao() {
		return "kakao";
	}
	@GetMapping("/login")
	public String login() {
		jsonWrite();
		
		return "login";
	}
	@PostMapping("/login")
	public String dologin(Member mem) {
		Member member = mservice.login(mem);
		if(member!=null) {
			session.setAttribute("sessionId", member.getId());
		}
		return "redirect:/";
	}
	
	
	
	public void jsonWrite() {
		JSONObject obj = new JSONObject();
		obj.put("id", "nnnn");
		obj.put("name", "유관순");
		obj.put("age", 21);

		try {
			FileWriter file = new FileWriter("c:/upload/person.json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print(obj);
	}
	
	
	@GetMapping("/join")
	public String join(KakaoDto kdto, Model model) {
		
		System.out.println(kdto.toString());
		
		model.addAttribute("kdto",kdto);
		return "join";
	}
	
	
	
}
