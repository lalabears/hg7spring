package com.java.controller;

import java.io.File;
import java.util.List;
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
	@GetMapping("/bwrite")
	public String bwrite() {
		return "board/bwrite";
	}
	@PostMapping("/bwrite")
	public String dobwrite(Board board,
			@RequestPart MultipartFile file) throws Exception {
		//System.out.println(board.getMember().getId());
		board.setBfile("");
		if(!file.isEmpty()) {
			String oriFileName = file.getOriginalFilename(); // 파일이름
			UUID uuid = UUID.randomUUID(); // 랜덤한 이름생성
			String uploadFileName = uuid+"_"+oriFileName; // 새로운 파일이름생성
			String fileURL = "c:/upload/"; // 저장위치
			File f = new File(fileURL+uploadFileName);
			file.transferTo(f);
			board.setBfile(uploadFileName);
		}
		bservice.insertOne(board);
		return "redirect:/board/blist";
	}
	
	
	@GetMapping("/bmodi")
	public String bmodi(int bno, Model model) {
		Board board = bservice.selectOne(bno);
		model.addAttribute("board",board);
		return "board/bmodi";
	}
	@PostMapping("/bmodi")
	public String dobmodi(Board board,
			@RequestPart MultipartFile file) throws Exception  {
		
		System.out.println(board.getBno());
		System.out.println(board.getMember().getId());
		System.out.println(board.getBtitle());
		System.out.println(board.getBgroup());
		System.out.println(board.getBcontent());
		System.out.println(board.getBfile());
		
		
		
		return "board/bmodi";
	}
	
	
	
	
}
