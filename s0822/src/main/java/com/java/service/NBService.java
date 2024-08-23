package com.java.service;

import java.util.ArrayList;

import com.java.dto.NoticeBoard;

public interface NBService {

	ArrayList<NoticeBoard> selectAll();

	NoticeBoard selectOne(int nbno);

}
