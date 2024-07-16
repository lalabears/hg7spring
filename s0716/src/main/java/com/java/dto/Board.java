package com.java.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	private int bno; 
	private String id; 
	private String btitle; 
	private String bcontent; 
	private Timestamp bdate; 
	private int bhit; 
	private int bgroup; 
	private int bstep; 
	private int bindent; 
	private String bfile; 
}
