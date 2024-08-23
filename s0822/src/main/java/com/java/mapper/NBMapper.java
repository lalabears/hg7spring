package com.java.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.NoticeBoard;

@Mapper
public interface NBMapper {

	ArrayList<NoticeBoard> selectAll();

	NoticeBoard selectOne(int nbno);

}
