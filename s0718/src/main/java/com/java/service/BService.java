package com.java.service;

import java.util.ArrayList;

import com.java.dto.Board;

public interface BService {

	ArrayList<Board> selectAll();

	Board selectOne(int bno);

	void insertOne(Board board);

	void updateOne(Board board);

	void deleteOne(int bno);

	void updateBhit(int bno);

}
