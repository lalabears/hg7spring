package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Member;
import com.java.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired MemberMapper mMapper;
	@Override
	public Member selectLogin(Member member) {
		Member mem = mMapper.selectLogin(member);
		return mem;
	}

}
