package com.java.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
	private int page = 1; 
	private int listCount;
	private int maxPage;
	private int endPage;
	private int startRow;
	private int endRow;
	private int rowPerPage=10;
	private int numberingPerPage = 10;
}
