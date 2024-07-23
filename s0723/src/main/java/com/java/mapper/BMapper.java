package com.java.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.Board;

@Mapper
public interface BMapper {
	// 게시글 전체 가져오기
	ArrayList<Board> selectAll();
	// bno번 게시글 가져오기 
	Board selectOne(int bno);
	// bno의 이전 글 가져오기 
	Board selectPrev(int bno);
	// bno의 이후 글 가져오기 
	Board selectNext(int bno);

}
