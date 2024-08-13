package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Member;
import com.java.repository.MemberRepository;

@Service
public class MServiceImpl implements MService {

	@Autowired MemberRepository mRepository;
	
	
	public Member selectLoginA(Member member) {
		// 2 . 쿼리 사용하기 
		Member mem = mRepository.selectLogin(member.getId(), member.getPw());
		return mem;
	}
	
	@Override
	public Member selectLogin(Member member) {
		// 1. 쿼리없이 사용하기. 
		// 1-1 id가 db에 있는지 검사 
		Member mem = mRepository.findById(member.getId()).orElse(null);
		// 1-2 id가 존재할 경우 pw를 검사 
		if(  mem.getPw().equals( member.getPw() )   ) {
			return mem;
		}
		return null;
	}

	@Override
	public List<Member> selectAll() {
		List<Member> mlist =  mRepository.findAll();
		return mlist;
	}

	@Override
	public void insertOne(Member member) {
		mRepository.save(member);
	}

	@Override
	public Member selectOne(String id) {
		Member member = mRepository.findById(id).orElse(null);
		return member;
	}

	@Override
	public void updateOne(Member member) {
		try {		
			mRepository.save(member);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	

}
