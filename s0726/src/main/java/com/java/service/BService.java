package com.java.service;

import java.util.ArrayList;

import com.java.dto.Board;
import com.java.dto.cBoard;

public interface BService {

	ArrayList<Board> selectAll();

	Board selectOne(int bno);

	ArrayList<cBoard> selectComAll(int bno);

	cBoard insertCom(cBoard cb);

}
