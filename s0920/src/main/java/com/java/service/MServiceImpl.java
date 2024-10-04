package com.java.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Board;
import com.java.dto.Member;
import com.java.mapper.MMapper;

@Service
public class MServiceImpl implements MService {
	@Autowired MMapper mmapper;

	@Override
	public Member login(Member mem) {
		Member member = mmapper.login(mem);
		return member;
	}

	@Override
	public void updateKakao(String id, String code, String token) {
		mmapper.updateKakao(id,code,token);

	}

	@Override
	public Member selectId(String id) {
		Member member = mmapper.selectId(id);
		return member;
	}

	@Override
	public ArrayList<Board> selectAll() {
		ArrayList<Board> blist = mmapper.selectAll();
		return blist;
	}
	

}
