package com.java.controller;

import java.util.List;

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
	@RequestMapping("/blist")
	public String blist(Model model) {
		List<Board> list = bservice.selectAll();
		model.addAttribute("list",list);
		return "board/blist";
	}
	@RequestMapping("/bview")
	public String bview(int bno, Model model) {
		Board board = bservice.selectOne(bno);
		model.addAttribute("board",board);
		return "board/bview";
	}
	@RequestMapping("/bmodi")
	public String bmodi() {
		return "board/bmodi";
	}
	@RequestMapping("/bwrite")
	public String bwrite() {
		return "board/bwrite";
	}
}
