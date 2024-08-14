package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.java.dto.Board;
import com.java.repository.BoardRepository;

@Service
public class BServiceImpl implements BService {

	@Autowired BoardRepository bRepository;
	
	@Override
	public List<Board> selectAll() {
		// bgroup desc, bstep asc
		Sort sort = Sort.by(
				Sort.Order.desc("bgroup"),
				Sort.Order.asc("bstep")
				);
		// bno 로 정렬하고싶을때. 
		//Sort sort = Sort.by(Sort.Direction.ASC, "bno");
		
		// 게시글 전체 가져오기
		List<Board> list = bRepository.findAll(sort);
		return list;
	}

	@Override
	public Board selectOne(int bno) {
		// 게시글 한개 가져오기 
		Board board = bRepository.findById(bno).orElse(null);
		return board;
	}

}
