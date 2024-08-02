package com.java.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Board;
import com.java.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired BoardMapper bmapper;
	
	@Override
	public ArrayList<Board> selectAll() {
		
		ArrayList<Board> list = bmapper.selectAll();
		
		return list;
	}
	@Override
	public Board selectOne(int bno) {
		Board board  = bmapper.selectOne(bno);
		return board;
	}
	@Override
	public void insertOne(Board board) {
		bmapper.insertOne(board);
	}

	
}
