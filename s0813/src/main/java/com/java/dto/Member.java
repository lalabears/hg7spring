package com.java.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder             // 부분생성자
@AllArgsConstructor  // 전체생성자
@NoArgsConstructor   // 기본생성자
@Data
@Entity // JPA가 관리하는 클래스 - 필드의 어노테이션을 보고 db테이블과 맵핑해서 관리함
//@Table(name="MEMBERDTO")
public class Member {
	@Id // entity의 primary key로 설정 - not null , unique
	private String id; 
	@Column(nullable=false, length=30)
	private String pw; 
	@Column(nullable=false, length=30)
	private String name; 
	private String phone; 
	private String gender; 
	private String hobbys; 

}
