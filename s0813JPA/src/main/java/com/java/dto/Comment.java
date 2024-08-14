package com.java.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder //부분생성자
@AllArgsConstructor // 전체생성자
@NoArgsConstructor // 기본생성자
@Data // getter setter
@DynamicInsert // insert쿼리시 null 값은 제외하고 쿼리를 만들어줌
@Entity // jpa를 관리하는 클래스 
@SequenceGenerator(  // 오라클 시퀀스를 사용함 
	name = "comment_seq_generator", // 시퀀스 제너레이터이름 
	sequenceName = "comment_seq",
	initialValue = 1 , // 시작값
	allocationSize = 1 // 메모리를 통한 할당범위 
)
@Table(name="commentboard")
public class Comment {
	@Id // pk
	@GeneratedValue(strategy = 
		GenerationType.SEQUENCE,
		generator = "comment_seq_generator")
	private int cno; 
	@ManyToOne
	@JoinColumn(name="bno")
	private Board board;   	// 기존 private int bno;
	@ManyToOne
	@JoinColumn(name="id")
	private Member member;    // 기존 private String id; 
	private String cpw;
	private String ccontent;
	private Timestamp cdate;

}
