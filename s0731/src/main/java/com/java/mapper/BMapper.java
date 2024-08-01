package com.java.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.Board;

@Mapper
public interface BMapper {

	ArrayList<Board> selectEventAll();

	ArrayList<Board> selectNoticeAll();

	void insertOne(Board board);

	ArrayList<Board> selectAll();

}
