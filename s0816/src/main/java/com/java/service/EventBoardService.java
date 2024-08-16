package com.java.service;

import java.util.HashMap;

import com.java.dto.Page;

public interface EventBoardService {

	HashMap<String, Object> selectAllEventBoard(Page pageDto);

}
