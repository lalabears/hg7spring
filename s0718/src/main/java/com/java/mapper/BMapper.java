package com.java.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.Board;

@Mapper
public interface BMapper {

	ArrayList<Board> selectAll();

	Board selectOne(int bno);

	void insertOne(Board board);

	void updateOne(Board board);

	void deleteOne(int bno);

	void updateBhit(int bno);

}
