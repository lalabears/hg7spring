package com.java.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.Board;
import com.java.dto.Member;

@Mapper
public interface MMapper {

	Member login(Member mem);

	void updateKakao(String id, String code, String token);

	Member selectId(String id);

	ArrayList<Board> selectAll();

}
