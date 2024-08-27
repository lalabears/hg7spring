package com.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScrapDto {

	private String title;
	private String url;
	private String imgUrl;
	private String des;
	private String press;
	private String time;
}
