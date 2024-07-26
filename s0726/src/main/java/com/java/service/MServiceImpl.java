package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.Member;
import com.java.mapper.MMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class MServiceImpl implements MService {
	@Autowired HttpSession session;
	@Autowired MMapper mMapper;
	@Override
	public void selectLogin(Member mdto) {
		Member mem = mMapper.selectLogin(mdto);
		if(mem!=null) {
			session.setAttribute("sessionId", mem.getId());
			session.setAttribute("sessionName", mem.getName());
		}
		
	}

}
