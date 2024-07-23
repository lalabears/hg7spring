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
	public HashMap<String, Object> selectAll() {
		HashMap<String, Object> map = new HashMap<>(); 
		ArrayList<Board> list = bMapper.selectAll();
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
