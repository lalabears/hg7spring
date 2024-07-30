package com.java.controller;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.java.dto.Board;

@Controller
public class FController {
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	@RequestMapping("/form1")
	public String form1() {
		return "form1";
	}
	@RequestMapping("/form2")
	public String form2() {
		return "form2";
	}
	@RequestMapping("/doForm1")
	public String doForm1(Board board,
			@RequestPart MultipartFile file, Model model) throws Exception {
		String fileName="";   // 파일명
		if(!file.isEmpty()) {   // 파일을 upload에 저장 
			String ori_fileName = file.getOriginalFilename();
			UUID uuid = UUID.randomUUID();
			fileName = uuid+"_"+ori_fileName;
			String uploadUrl = "c:/upload/";
			File f = new File(uploadUrl+fileName);
			file.transferTo(f);
		}
		model.addAttribute("fileName", fileName);
		model.addAttribute("btitle", board.getBtitle());
		return "fileCheck";
	}
	
	@RequestMapping("/doForm2")
	public String doForm2() {
		return "fileCheck2";
	}
	
	
}
