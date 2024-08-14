package com.java.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.dto.Board;
import com.java.repository.BoardRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class BServiceImpl implements BService {

	@Autowired BoardRepository bRepository;
	@PersistenceContext // 영속성컨텍스트
	private EntityManager entityManger; // 엔티티 관리해줌. 
	// entitiy들이 db로 바로가지 않고 entity를 저장하는 환경으로 역할
	
	
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

	@Transactional  // 트랜젝션을 시작하고 정상여부에 따라 commit, rollback
	@Override
	public Board selectOne(int bno) {
		// 게시글 한개 가져오기 
		Board board = bRepository.findById(bno).orElse(null);
		// 조회수 1 증가 
		board.setBhit(board.getBhit()+1);
		return board;
	}

	@Transactional 
	@Override
	public void insertOne(Board board) {
		try {
			entityManger.persist(board); // 시퀀스를 가져옴. 
			System.out.println("entityManager.persist : " + board.getBno());
			
			// bgroup에 board_seq.currval 넣기
			board.setBgroup(board.getBno()); 
			// bdate에 sysdate 넣기 
			board.setBdate(new Timestamp(System.currentTimeMillis()));
			// insert 구문 대신에 사용. 
			bRepository.save(board);
			
		} catch (Exception e) {e.printStackTrace();}
		
	}

}
