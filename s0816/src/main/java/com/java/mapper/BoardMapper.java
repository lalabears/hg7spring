package com.java.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.Board;
import com.java.dto.Page;

@Mapper
public interface BoardMapper {

	int selectListCount();

	ArrayList<Board> selectAllBoard(Page pageDto);

}
