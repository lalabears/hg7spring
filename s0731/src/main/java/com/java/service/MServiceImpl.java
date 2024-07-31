package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Member;
import com.java.mapper.MMapper;

@Service
public class MServiceImpl implements MService {
	@Autowired MMapper mmapper;
	
	@Override
	public Member idck(String id) {
		Member member = mmapper.idck(id);
		return member;
	}

}
