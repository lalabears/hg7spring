package com.java.service;

import java.util.ArrayList;

import com.java.dto.Board;
import com.java.dto.Member;

public interface MService {

	Member login(Member mem);

	void updateKakao(String userid, String code, String access_token);

	Member selectId(String id);

	ArrayList<Board> selectAll();

	

}
