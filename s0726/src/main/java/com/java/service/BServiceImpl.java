package com.java.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Board;
import com.java.dto.cBoard;
import com.java.mapper.BMapper;

@Service
public class BServiceImpl implements BService {
	@Autowired BMapper bMapper;
	@Override
	public ArrayList<Board> selectAll() {
		ArrayList<Board> list = bMapper.selectAll();
		return list;
	}
	@Override
	public Board selectOne(int bno) {
		Board board = bMapper.selectOne(bno);
		return board;
	}
	@Override
	public ArrayList<cBoard> selectComAll(int bno) {
		ArrayList<cBoard> list = bMapper.selectComAll(bno);
		return list;
	}
	@Override
	public cBoard insertCom(cBoard cb) {
		bMapper.insertComOne(cb);
		cBoard comment = bMapper.selectComOne(cb);
		return comment;
	}

}
