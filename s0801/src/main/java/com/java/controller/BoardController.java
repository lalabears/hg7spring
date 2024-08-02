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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.Board;
import com.java.dto.Comment;
import com.java.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired BoardService bservice;
	
	// 댓글 
	@PostMapping("/board/commentInsert")
	@ResponseBody  // ajax 쓸때만 !! 
	public Comment comInsert(Comment comment) {
		
		Comment cdto = bservice.insertCommentOne(comment);
		
		return cdto;
	}
	@PostMapping("/board/delComment")
	@ResponseBody  // ajax 쓸때만 !! 
	public String delComment(int cno) {
		System.out.println(cno);
		bservice.delComment(cno);
		return "성공";
	}
	
	
	
	
	
	
	
	// 게시판 열기
	@RequestMapping("/board/notice")  // url에 입력할 주소
	public String notice(Model model) {
		ArrayList<Board> list = bservice.selectAll();
		model.addAttribute("list", list);
		return "customer/notice"; // jsp 파일의 위치
	}
	// 게시판 글 보기
	@RequestMapping("/board/notice_view")  // url에 입력할 주소
	public String notice_view(int bno, Model model) {
		// 게시글 한개 가져오기
		Board board = bservice.selectOne(bno);
		// 게시글에 해당하는 댓글 가져오기 
		ArrayList<Comment> comList = bservice.selectComAll(bno);
		
		// jsp로 보내는..
		model.addAttribute("board",board);
		model.addAttribute("comList",comList);
		
		return "customer/notice_view"; // jsp 파일의 위치
	}
	// 게시판 글 작성 
	@GetMapping("/board/write")  // url에 입력할 주소
	public String write() {
		return "customer/write"; // jsp 파일의 위치
	}
	// 게시판에 글을 등록햇을때 호출되는 부분 
	@PostMapping("/board/write")  
	public String dowrite(Board board, @RequestPart MultipartFile file) {
		System.out.println("dopost- write");
		System.out.println(board.getId());
		System.out.println(board.getBtitle());
		System.out.println(board.getBcontent());
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
		
		return "redirect:/"; 
	}
	
}
