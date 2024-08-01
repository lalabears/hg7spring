package com.java.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.Board;
import com.java.service.BService;

@Controller
@RequestMapping("/board")
public class BController {
	@Autowired BService bservice;
	@RequestMapping("/event")
	public String event(Model model) {
		// 이벤트만 출력 
		ArrayList<Board> list = bservice.selectEventAll();
		model.addAttribute("list",list);
		return "board/event";
	}
	@RequestMapping("/notice")
	public String notice(Model model) {
		// 공지만 출력
		ArrayList<Board> list = bservice.selectNoticeAll();
		model.addAttribute("list",list);
		return "board/notice";
	}
	
	
	
	
	@RequestMapping("/event_view")
	public String event_view() {
		return "board/event_view";
	}
	
	@RequestMapping("/notice_view")
	public String notice_view() {
		return "board/notice_view";
	}
	@RequestMapping("/product")
	public String product() {
		return "board/list";
	}
	@RequestMapping("/write")
	public String write() {
		return "board/write";
	}

}
