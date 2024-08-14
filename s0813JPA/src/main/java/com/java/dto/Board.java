package com.java.dto;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
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
	name = "board_seq_generator", // 시퀀스 제너레이터이름 
	sequenceName = "board_seq",
	initialValue = 1 , // 시작값
	allocationSize = 1 // 메모리를 통한 할당범위 
)
public class Board {
	@Id  // pk 
	@GeneratedValue(strategy = 
	GenerationType.SEQUENCE, 
	generator = "board_seq_generator"
	)
	private int bno;
	@ManyToOne
	@JoinColumn(name="id")
	private Member member; 
	// private String id; // member테이블의 id의 아이디와 연결된 fk 
	@Column(nullable = false)
	private String btitle;
	private String bcontent;
	@CreationTimestamp  // 시간을 자동으로입력 
	private Timestamp bdate; 
	@ColumnDefault("0")
	private int bhit;
	private int bgroup;
	private int bstep;
	private int bindent;
	private String bfile; 

}
