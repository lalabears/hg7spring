package com.java.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.Page;
import com.java.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired BoardService bservice;
	
	@RequestMapping("/board/blist")
	public String blist(Model model, Page pageDto) {
		// 게시판과 연결됨 
		HashMap<String, Object> map =  bservice.selectAllBoard(pageDto);
		
		model.addAttribute("list", map.get("list")); 
		model.addAttribute("pageDto", map.get("pageDto"));
		
		return "board/blist";
	}
	@RequestMapping("/board/bview")
	public String bview() {
		return "board/bview";
	}
}
