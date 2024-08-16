package com.java.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.Page;
import com.java.service.EventBoardService;

@Controller
public class EventBoardController {
	@Autowired EventBoardService ebservice;
	
	@RequestMapping("/board/eventlist")
	public String eventlist(Model model, Page pageDto) {
		
		HashMap<String, Object> map = ebservice.selectAllEventBoard(pageDto);
		model.addAttribute("list",map.get("list"));
		model.addAttribute("pageDto",map.get("pageDto"));
		
		return "board/eventlist";
	}
	
	
	@RequestMapping("/board/eventview")
	public String eventview() {
		return "board/eventview";
	}
}
