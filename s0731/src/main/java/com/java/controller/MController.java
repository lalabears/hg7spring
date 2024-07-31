package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.service.MService;

@Controller
@RequestMapping("/member")
public class MController {
	@Autowired MService mservice;
	@PostMapping("/idck")
	@ResponseBody
	public String idck(String id) {
		System.out.println(id);
		
		return"성공";
	}

}
