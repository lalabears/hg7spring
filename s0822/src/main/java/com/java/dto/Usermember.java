package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usermember {
	private int uno;
	private String id;
	private String pw;
	private String name;
	private String unick;
	private String email;
	private String phone;
}
