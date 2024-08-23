package com.java.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dto.NoticeBoard;
import com.java.mapper.NBMapper;

@Service
public class NBServiceImpl implements NBService {

	@Autowired NBMapper nbmapper;
	@Override
	public ArrayList<NoticeBoard> selectAll() {
		ArrayList<NoticeBoard> list = nbmapper.selectAll();
		return list;
	}
	@Override
	public NoticeBoard selectOne(int nbno) {
		NoticeBoard b = nbmapper.selectOne(nbno);
		return b;
	}

}
