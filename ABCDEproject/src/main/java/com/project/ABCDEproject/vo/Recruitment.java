package com.project.ABCDEproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruitment {

	int id;				
	String writer_id;	
	String title;	
	String content;	
	int hits;
	String write_date;
	
}
