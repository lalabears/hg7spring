package com.java.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Board;
import com.java.mapper.BMapper;

@Service
public class BServiceImpl implements BService {
	@Autowired BMapper bmapper;
	@Override
	public ArrayList<Board> selectAll() {
		ArrayList<Board> list =bmapper.selectAll();
		return list;
	}
	@Override
	public void insertOne(Board board) {
		bmapper.insertOne(board);
		
	}
	@Override
	public Board selectOne(int bno) {
		Board board = bmapper.selectOne(bno);
		return board;
	}

}
