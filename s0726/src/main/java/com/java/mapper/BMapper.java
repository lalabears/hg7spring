package com.java.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.Board;
import com.java.dto.cBoard;

@Mapper
public interface BMapper {

	ArrayList<Board> selectAll();

	Board selectOne(int bno);

	ArrayList<cBoard> selectComAll(int bno);

}
