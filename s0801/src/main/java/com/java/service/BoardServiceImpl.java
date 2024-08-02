package com.java.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Board;
import com.java.dto.Comment;
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
	@Override
	public ArrayList<Comment> selectComAll(int bno) {
		ArrayList<Comment> comlist = bmapper.selectComAll(bno);
		return comlist;
	}
	@Override
	public Comment insertCommentOne(Comment comment) {
		// 테이블에 커맨트 내용을 한개 삽입함. (bno, id, ccontent, cpw 내용있음)
		bmapper.insertCommentOne(comment);
		// 삽입한 정보를 사용해서 그 내용을 가져옴 (cno, cdate 내용이 추가적으로 생겨서 가져옴)
		Comment cdto = bmapper.selectCommentOne(comment);
		return cdto;
	}
	@Override
	public void delComment(int cno) {
		bmapper.delComment(cno);
	}

	
}
