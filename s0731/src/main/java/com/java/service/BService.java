package com.java.service;

import java.util.ArrayList;

import com.java.dto.Board;

public interface BService {

	ArrayList<Board> selectEventAll();

	ArrayList<Board> selectNoticeAll();

	void insertOne(Board board);

	ArrayList<Board> selectAll();

}
