package com.java.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.Board;
import com.java.service.BService;

@RestController // @Controller + @ResponseBody
public class AjaxController {
	@Autowired BService bservice;
	@PostMapping("/boardList")
	public ArrayList<Board> boardList() {
		// db랑 연결 보드테이블에 있는 리스트 가져오기
		ArrayList<Board> list = bservice.selectAll();
		return list;
	}
	@PostMapping("/insertBoard")
	public Board insertBoard(Board board) {
		System.out.println(board.getId());
		System.out.println(board.getBtitle());
		System.out.println(board.getBcontent());
		Board bdto = bservice.insertBoard(board);
		return bdto;
	}
}
