package com.java.controller;

import java.io.File;
import java.util.ArrayList;
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
		ArrayList<Board> list =bservice.selectAll();
		model.addAttribute("list", list);
		return "board/blist";
	}
	@RequestMapping("/bview")
	public String bview(int bno, Model model) {
		Board board = bservice.selectOne(bno);
		// System.out.println(board.getBfile());
		// "," 여기서 스플릿 해서. bfiles set함
		System.out.println(board.getBfile());
		if(!(board.getBfile()==null || board.getBfile().equals("")) ) {
			String[] splitFiles = board.getBfile().split(",");
			board.setBfiles(splitFiles);
		}
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
	@GetMapping("/bwrite2")
	public String bwrite2() {
		return "board/bwrite2";
	}
	@PostMapping("/bwrite2")
	public String dobwrite2(Board board, 
			List<MultipartFile> files) throws Exception {
		String fName="";
		String fileName="";  
		int i = 0; 
		for(MultipartFile file: files) {
		//	System.out.println(file.getOriginalFilename());
			String ori_fileName = file.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			fileName = uuid+"_"+ori_fileName;
			String uploadUrl = "c:/upload/";
			File f = new File(uploadUrl+fileName);
			file.transferTo(f);
			if(i==0) fName += fileName;
			else fName += "," + fileName;
			i++;
		}
		board.setBfile(fName);
		bservice.insertOne(board);
		return "redirect:blist";
	}
	//-----------------------------------------------------
	//-------- summernote --------------------------------
	//-------------------------------------------------------
	@GetMapping("/bwrite3")
	public String bwrite3() {
		return "board/bwrite3";
	}
}
