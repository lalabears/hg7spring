package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeBoard {
	
	private int nbno;
	//private int uno;
	private String nbtitle;
	private String nbcontent;
	
	private Usermember user;

}
