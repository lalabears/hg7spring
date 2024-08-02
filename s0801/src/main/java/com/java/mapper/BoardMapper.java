package com.java.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.Board;
import com.java.dto.Comment;

@Mapper
public interface BoardMapper {

	ArrayList<Board> selectAll();

	Board selectOne(int bno);

	void insertOne(Board board);

	ArrayList<Comment> selectComAll(int bno);

	void insertCommentOne(Comment comment);

	Comment selectCommentOne(Comment comment);

	void delComment(int cno);

	void updateCommentOne(Comment comment);

	

}
