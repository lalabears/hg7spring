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
	@Autowired BService bService;
	
	@RequestMapping("/notice")
	public String notice(Model model) {
		ArrayList<Board> list = bService.selectAll();
		model.addAttribute("list",list);
		return "board/notice";
	}
	@RequestMapping("/notice_view")
	public String notice_view(int bno, Model model) {
		Board board = bService.selectOne(bno);
		model.addAttribute("board",board);
		return "board/notice_view";
	}
	
	
	
	
}
