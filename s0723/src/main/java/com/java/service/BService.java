package com.java.service;

import java.util.HashMap;

public interface BService {

	HashMap<String, Object> selectAll();

	HashMap<String, Object> selectOne(int bno);

}
