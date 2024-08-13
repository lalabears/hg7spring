package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.dto.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

	@Query(value="select * from member where id=? and pw=?", nativeQuery=true)
	Member selectLogin(String id, String pw);

}
