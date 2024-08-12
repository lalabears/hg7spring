package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Member;
import com.java.mapper.MMapper;

@Service
public class MServiceImpl implements MService {

	@Autowired MMapper mmapper;
	@Override
	public Member login(String id, String pw) {
		Member member = mmapper.login(id,pw);
		return member;
	}
	@Override
	public String idCheck(String id) {
		String name = mmapper.idCheck(id);
		return name;
	}

}
