package com.java.service;

import java.util.List;

import com.java.dto.Board;

public interface BService {

	List<Board> selectAll();

	Board selectOne(int bno);

	void insertOne(Board board);

}
