package com.java.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.dto.Comment;
import com.java.service.BService;

@Controller
public class BController {
	@Autowired BService bService;
	
	@PostMapping("/board/commentInsert")
	@ResponseBody
	public Comment commentInsert(Comment comdto) {
		// comdto jsp에서 전달받은 값
		System.out.println(comdto.getCcontent());
		System.out.println(comdto.getId());
		// 새로 dto를 만들어서 jsp전송할 값 > data 
		Comment cdto = bService.commentInsert(comdto);
		return cdto;
	}
	@PostMapping("/board/commentDelete")
	@ResponseBody
	public String commentDelete(int cno) {
		System.out.println("cno: "+cno);
		bService.commentDelete(cno);
		return "success";
	}
	@PostMapping("/board/commentUpdate")
	@ResponseBody
	public Comment commentUpdate(Comment cdto) {
		System.out.println("cno: "+cdto.getCno());
		System.out.println("cpw: "+cdto.getCpw());
		System.out.println("ccontent: "+cdto.getCcontent());
		Comment comment = bService.commentUpdate(cdto);
 		return comment;
	}
	
	
	
	
	@RequestMapping("/board/notice")
	public String notice(@RequestParam(defaultValue = "1") int page
			,String category, String s_word ,Model model) {

		// board를 db에서 가져와서 게시판에 출력하기
		HashMap<String, Object> map = bService.selectAll(page, category, s_word);
		
		// list 라는 변수는 Array를 담고있다. 
		model.addAttribute("list", map.get("list"));
		
		model.addAttribute("listCount", map.get("listCount"));
		model.addAttribute("maxPage", map.get("maxPage"));
		model.addAttribute("startPage", map.get("startPage"));
		model.addAttribute("endPage", map.get("endPage"));
		model.addAttribute("startRow", map.get("startRow"));
		model.addAttribute("endRow", map.get("endRow"));
		model.addAttribute("page", map.get("page"));
		model.addAttribute("category", map.get("category"));
		model.addAttribute("s_word", map.get("s_word"));
		
		
		
		return "board/notice";
	}
	
	
	@RequestMapping("/board/noticeView")
	public String noticeView(@RequestParam(defaultValue = "1") int page
			,String category, String s_word, int bno, Model model) {
		System.out.println("BController - noticeView");
		System.out.println("category : "+category);
		System.out.println("s_word : "+s_word);
		
		HashMap<String, Object> map = bService.selectOne(bno,category,s_word);
		ArrayList<Comment> comList = bService.selectComAll(bno);
		// System.out.println(comList.get(0).getCno());
		// 게시글에 있는 댓글 
		model.addAttribute("comList",comList);
		// 현재 게시글
		model.addAttribute("board", map.get("board"));
		// 이전 게시글
		model.addAttribute("prev", map.get("prev"));
		// 이후 게시글 
		model.addAttribute("next", map.get("next"));
		
		model.addAttribute("category", category);
		model.addAttribute("s_word", s_word);
		model.addAttribute("page", page);
		
		
		
		return "board/noticeView";
	}
	
	
	@PostMapping("/board/commentCnt")
	@ResponseBody
	public int commentCnt(int bno) {
		int ccnt = bService.ComCnt(bno);
		return ccnt;
	}
	
	
}
