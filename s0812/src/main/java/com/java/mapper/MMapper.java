package com.java.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.java.dto.Member;

@Mapper
public interface MMapper {
	// 아이디와 비밀번호를 통한 로그인 
	Member login(String id, String pw);

	String idCheck(String id);

}
