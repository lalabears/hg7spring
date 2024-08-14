package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.dto.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
