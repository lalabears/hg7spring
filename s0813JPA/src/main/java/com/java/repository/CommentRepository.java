package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.Board;
import com.java.dto.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
