package com.java.service;

import java.util.HashMap;

import com.java.dto.Page;

public interface BoardService {

	HashMap<String, Object> selectAllBoard(Page pageDto);

}
