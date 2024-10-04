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
