package com.java.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventBoard {
	private int ebno;
	private String id;
	private String ebtitle;
	private String ebcontent;
	private Timestamp ebdate;
	private Timestamp eenddate;
	private int ebhit;
	private int ebgroup;
	private int ebstep;
	private int ebindent;
	private String ebfile;

}
