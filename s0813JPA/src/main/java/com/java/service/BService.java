package com.java.service;

import java.util.List;

import com.java.dto.Board;
import com.java.dto.Comment;

public interface BService {

	List<Board> selectAll();

	Board selectOne(int bno);

	void insertOne(Board board);

	void updateOne(Board board);

	void deleteOne(int bno);

	List<Comment> selectComAll(int bno);

	Comment insertCom(Comment com);



}
