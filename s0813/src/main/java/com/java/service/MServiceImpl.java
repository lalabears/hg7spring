package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Member;
import com.java.repository.MemberRepository;

@Service
public class MServiceImpl implements MService {

	@Autowired MemberRepository mRepository; 
	
	
	@Override
	public Member login(Member mem) {
		Member member = mRepository.findById(mem.getId()).orElse(null);
		if(member.getPw().equals(mem.getPw())) {
			return member;
		}
		return null;
	}


	@Override
	public void join(Member member) {
		mRepository.save(member);
		
	}

}
