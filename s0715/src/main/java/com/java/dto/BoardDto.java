package com.java.dto;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

// data = getter & setter 동시에 가져옴
@Data  
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BoardDto {
	private int bno;
	private String btitle;
}
