package com.java.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.Member;

@Mapper
public interface MemberMapper {
	// 회원전체정보 가져오기
	ArrayList<Member> memberSelectAll();
}
