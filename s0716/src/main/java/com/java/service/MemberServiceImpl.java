package com.java.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Member;
import com.java.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper memberMapper; 
	
	// 회원 전체정보 가져오기
	@Override
	public ArrayList<Member> memberSelectAll() {
		
		ArrayList<Member> list = memberMapper.memberSelectAll();
		return list; 
		
	}
	// 회원 한명의 정보 아이디로 가져오기 
	@Override
	public Member memberSelectOne(String id) {
		Member member = memberMapper.memberSelectOne(id);
		return member;
	}

	// 로그인을 위한 메서드 
	@Override
	public Member selectLogin(String id, String pw) {
		Member member = memberMapper.selectLogin(id, pw);
		return member;
	}

	

}
