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
		System.out.println("bserviceImpl category : "+ category);
		System.out.println("bserviceImpl s_word : "+ s_word);
		
		HashMap<String, Object> map = new HashMap<>();
		// page, 게시글 관련 부분. 
		// 1. 총 게시글의 수 
		int listCount = bMapper.selectListCount();
		// 2. 최대페이지 
		int maxPage = (int)Math.ceil(listCount/10.0);
		// 3. startPage, endPage 
		int startPage = (int)((page-1)/10)*10+1;
		int endPage = startPage+10-1;
		// 4. startRow, endRow 
		int startRow = (page-1)*10+1;
		int endRow = startRow+10-1;
		if(endPage > maxPage) endPage=maxPage;
		// startrow부터 endrow 까지의 게시글 가져오기
		ArrayList<Board> list = bMapper.selectAll(startRow, endRow, category, s_word);
		// map에 내용 추가하기
		map.put("listCount", listCount);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("page", page);
		map.put("list", list);
		return map;
	}
	@Override
	public HashMap<String, Object> selectOne(int bno) {
		HashMap<String, Object> map = new HashMap<>();
		Board board = bMapper.selectOne(bno);
		map.put("board", board);
		return map;
	}

}
