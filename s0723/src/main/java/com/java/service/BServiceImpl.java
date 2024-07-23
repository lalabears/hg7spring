package com.java.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Board;
import com.java.mapper.BMapper;

@Service
public class BServiceImpl implements BService {

	@Autowired BMapper bMapper;
	@Override
	public HashMap<String, Object> selectAll(int page, String category, String s_word) {
		// 확인용 
		System.out.println("category " + category);
		System.out.println("s_word " + s_word);
		HashMap<String, Object> map = new HashMap<>(); 
		// 1. 총 게시글의 수 
		int listCount = bMapper.selectListCount(category, s_word);
		// 2. 최대페이지 
		int maxPage = (int)Math.ceil(listCount/10.0);
		// 3. startPage, endPage 
		int startPage = (int)((page-1)/10)*10+1;
		int endPage = startPage+10-1;
		// 4. startRow, endRow 
		int startRow = (page-1)*10+1;
		int endRow = startRow+10-1;
		if(endPage > maxPage) endPage=maxPage;
		
		System.out.println("listCount:"+listCount);
		
		ArrayList<Board> list = bMapper.selectAll(startRow, endRow, category,s_word);

		
		
		map.put("list", list);
		return map;
	}
	@Override
	public HashMap<String, Object> selectOne(int bno) {
		HashMap<String, Object> map = new HashMap<>(); 
		Board board = bMapper.selectOne(bno);
		Board prev = bMapper.selectPrev(bno);
		Board next = bMapper.selectNext(bno);
		map.put("board", board);
		map.put("prev", prev);
		map.put("next", next);
		return map;
	}

}
