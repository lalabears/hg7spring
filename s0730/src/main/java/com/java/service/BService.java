package com.java.service;

import java.util.ArrayList;

import com.java.dto.Board;

public interface BService {

	ArrayList<Board> selectAll();

	void insertOne(Board board);

	Board selectOne(int bno);

}
