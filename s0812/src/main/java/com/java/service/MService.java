package com.java.service;

import com.java.dto.Member;

public interface MService {

	Member login(String id, String pw);

	String idCheck(String id);

	void insertMember(Member member);

}
