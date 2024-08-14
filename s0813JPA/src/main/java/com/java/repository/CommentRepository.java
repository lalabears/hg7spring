package com.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.dto.Board;
import com.java.dto.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	@Query(	value ="select * from commentboard where bno=? order by cno desc",
			nativeQuery = true)
	List<Comment> findAllByBno(int bno);

}
