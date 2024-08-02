package com.java.service;

import java.util.ArrayList;

import com.java.dto.Board;
import com.java.dto.Comment;

public interface BoardService {

	ArrayList<Board> selectAll();

	Board selectOne(int bno);

	void insertOne(Board board);

	ArrayList<Comment> selectComAll(int bno);

	Comment insertCommentOne(Comment comment);

	void delComment(int cno);

	Comment updateCommentOne(Comment comment);

}
