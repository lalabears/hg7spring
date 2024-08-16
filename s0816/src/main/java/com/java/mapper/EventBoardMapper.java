package com.java.mapper;



import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.Board;
import com.java.dto.EventBoard;
import com.java.dto.Page;

@Mapper
public interface EventBoardMapper {

	int selectListCount();

	ArrayList<EventBoard> selectAllEventBoard(Page pageDto);

	

}
