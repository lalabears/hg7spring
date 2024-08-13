package com.java.service;

import java.util.List;

import com.java.dto.Member;

public interface MService {

	Member selectLogin(Member member);

	List<Member> selectAll();

	void insertOne(Member member);

	Member selectOne(String id);

}
