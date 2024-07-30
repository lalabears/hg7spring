package com.java.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.Board;
import com.java.service.BService;

@Controller
@RequestMapping("/board")
public class BController {
	@Autowired BService bservice;
	@RequestMapping("/blist")
	public String blist(Model model) {
		ArrayList<Board> list =bservice.selectAll();
		model.addAttribute("list", list);
		return "board/blist";
	}
	@RequestMapping("/bview")
	public String bview(int bno, Model model) {
		Board board = bservice.selectOne(bno);
		model.addAttribute("board",board);
		return "board/bview";
	}
	@GetMapping("/bwrite")
	public String bwrite() {
		return "board/bwrite";
	}
	@PostMapping("/bwrite")
	public String dobwrite(Board board, @RequestPart MultipartFile file) 
	throws Exception {
		String fileName="";   // 파일명
		if(!file.isEmpty()) {   // 파일을 upload에 저장 
			String ori_fileName = file.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			fileName = uuid+"_"+ori_fileName;
			String uploadUrl = "c:/upload/";
			File f = new File(uploadUrl+fileName);
			file.transferTo(f);
		}
		board.setBfile(fileName);
		bservice.insertOne(board);
		return "redirect:blist";
	}
}
