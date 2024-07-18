package com.java.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.Board;
import com.java.service.BService;

@Controller
@RequestMapping("/board")
public class BController {
	
	@Autowired  BService bservice; 
	
	@RequestMapping("/blist")
	public String blist(@RequestParam(defaultValue = "1") int page, Model model) {
		HashMap<String, Object> map = bservice.selectAll(page);
		model.addAttribute("list",map.get("list"));
		model.addAttribute("page",map.get("page"));
		model.addAttribute("endRow",map.get("endRow"));
		model.addAttribute("startRow",map.get("startRow"));
		model.addAttribute("endPage",map.get("endPage"));
		model.addAttribute("startPage",map.get("startPage"));
		model.addAttribute("maxPage",map.get("maxPage"));
		model.addAttribute("listCount",map.get("listCount"));
		return "board/blist";
	}
	@RequestMapping("/bview")
	public String bview(int bno, Model model) {
		System.out.println("bno: "+bno);
		Board board = bservice.selectOne(bno);
		bservice.updateBhit(bno);
		System.out.println(board.getBtitle());
		model.addAttribute("board", board);
		return "board/bview";
	}
	// 쓸수 있는화면
	@GetMapping("/bwrite")
	public String bwrite() {
		return "board/bwrite";
	
	}
	// 쓰기 submit 화면 
	@PostMapping("/bwrite")
	public String dobwrite(Board board, @RequestPart MultipartFile file) {
		System.out.println("bwrite-post방식");
		// 게시글1개 저장 
		String fileName ="";
		if(!file.isEmpty()) {
			String ori_file = file.getOriginalFilename();// 실제파일이름
			UUID uuid = UUID.randomUUID();// 랜덤숫자생성
			fileName = uuid+"_"+ori_file; // 최종파일이름
			String uploadUrl = "c:/upload/"; // 파일이저장될위치
			File f = new File(uploadUrl+fileName);
			try {
				file.transferTo(f);// 파일저장
			}catch(Exception e) {e.printStackTrace();}
		}
		board.setBfile(fileName); // board객체에 file이름저장
		bservice.insertOne(board);
		return "redirect:blist";
	
	}
	
	@GetMapping("/bmodi")
	public String bmodi(int bno, Model model) {
		Board board = bservice.selectOne(bno);
		model.addAttribute("board", board);
		return "board/bmodi";
	}
	@PostMapping("/bmodi")
	public String dobmodi(Board board, @RequestPart MultipartFile file) {
		System.out.println("bmodi-post방식");
		// 게시글1개 저장 
		String fileName ="";
		if(!file.isEmpty()) {
			String ori_file = file.getOriginalFilename();// 실제파일이름
			UUID uuid = UUID.randomUUID();// 랜덤숫자생성
			fileName = uuid+"_"+ori_file; // 최종파일이름
			String uploadUrl = "c:/upload/"; // 파일이저장될위치
			File f = new File(uploadUrl+fileName);
			try {
				file.transferTo(f);// 파일저장
				board.setBfile(fileName); // board객체에 file이름저장
			}catch(Exception e) {e.printStackTrace();}
		}
		bservice.updateOne(board);
		return "redirect:blist";
	}
	
	
	@RequestMapping("/bdelete")
	public String bdelete(int bno) {
		
		System.out.println("delete : "+bno);
		bservice.deleteOne(bno);
		return "redirect:blist";
	}
	// 답글 창열림
	@GetMapping("/breply")
	public String breply(int bno, Model model) {
		Board board = bservice.selectOne(bno);
		model.addAttribute("board", board);
		return "board/breply";
	}
	// 답글이 등록됨
	@PostMapping("/breply")
	public String dobreply(Board board, @RequestPart MultipartFile file) {	
		System.out.println("bno : "+board.getBno());
		System.out.println("bstep : "+ board.getBstep());
		System.out.println("bindent : "+ board.getBindent());
		System.out.println("bgroup : "+ board.getBgroup());
		System.out.println("id : "+ board.getId());
		System.out.println("btitle : "+ board.getBtitle());
		
		String fileName ="";
		if(!file.isEmpty()) {
			String ori_file = file.getOriginalFilename();// 실제파일이름
			UUID uuid = UUID.randomUUID();// 랜덤숫자생성
			fileName = uuid+"_"+ori_file; // 최종파일이름
			String uploadUrl = "c:/upload/"; // 파일이저장될위치
			File f = new File(uploadUrl+fileName);
			try {
				file.transferTo(f);// 파일저장
				board.setBfile(fileName); // board객체에 file이름저장
			}catch(Exception e) {e.printStackTrace();}
		}
		
		bservice.insertReplyOne(board);
		
		return "redirect:blist";
	}
	
	
	
	
}
