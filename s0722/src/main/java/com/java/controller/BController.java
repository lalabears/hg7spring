package com.java.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.service.BService;

@Controller
@RequestMapping("/board")
public class BController {
	@Autowired BService bservice; 
	
	@RequestMapping("/blist")
	public String blist(@RequestParam(defaultValue = "1") int page ,String category,
		String s_word, Model model  ) {
		System.out.println("category : "+ category);
		System.out.println("s_word : "+ s_word);

		HashMap<String, Object> map = bservice.selectAll(page,category,s_word);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("listCount", map.get("listCount"));
		model.addAttribute("maxPage", map.get("maxPage"));
		model.addAttribute("startPage", map.get("startPage"));
		model.addAttribute("endPage", map.get("endPage"));
		model.addAttribute("startRow", map.get("startRow"));
		model.addAttribute("endRow", map.get("endRow"));
		model.addAttribute("page", map.get("page"));
		
		//System.out.println( "page : " + map.get("page"));	System.out.println( "startRow : " + map.get("startRow"));System.out.println( "endRow : " + map.get("endRow"));
		
		
		
		return "board/blist";
	}
	@RequestMapping("/bread")
	public String bread(int bno, Model model) {
		System.out.println("bno : "+ bno);
		HashMap<String, Object> map = bservice.selectOne(bno);
		model.addAttribute("board", map.get("board"));
		return "board/bread";
	}
	@RequestMapping("/bwrite")
	public String bwrite() {
		return "board/bwrite";
	}
	@RequestMapping("/bmodi")
	public String bmodi() {
		return "board/bmodi";
	}
	@RequestMapping("/brelpy")
	public String brelpy() {
		return "board/brelpy";
	}
}
