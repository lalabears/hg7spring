package com.java.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.dto.NoticeBoard;
import com.java.service.NBService;

@Controller
public class FController {
	
	@Autowired NBService nbservice;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	@RequestMapping("/notice")
	public String notice(Model model) {
		ArrayList<NoticeBoard> list = nbservice.selectAll(); 
		model.addAttribute("list",list);
		return "notice";
	}
	
	@RequestMapping("/nview")
	public String nview(int nbno, Model model) {
		NoticeBoard board = nbservice.selectOne(nbno);
		model.addAttribute("nb",board);
		return "nview";
	}
	
}
