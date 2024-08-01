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
	public String product(Model model) {
		ArrayList<Board> list = bservice.selectAll();
		model.addAttribute("list",list);
		return "board/list";
	}
	// page를 여는 부분
	@GetMapping("/write")
	public String write() {
		return "board/write";
	}
	// 게시글을 저장하는 부분 
	@PostMapping("/write")
	public String dowrite(Board board, @RequestPart MultipartFile file) {
		String fileName="";
		// 파일이 있을 경우 파일 저장 
		if(!file.isEmpty()) {
			String ori_fileName = file.getOriginalFilename(); //실제 파일이름
			UUID uuid = UUID.randomUUID();    // 랜덤숫자 생성
			fileName = uuid+"_"+ori_fileName; // 파일이름 변경 (중복방지)
			System.out.println(fileName);
			String uploadUrl = "c:/upload/" ; // 파일 업로드위치 
			File f = new File(uploadUrl+fileName);
			try {
				file.transferTo(f); // 파일 저장 
			}catch(Exception e) {e.printStackTrace();}
		}
		board.setBfile(fileName);
		board.setId("admin");
		bservice.insertOne(board);
		
		return "redirect:/";
		
	}

}
