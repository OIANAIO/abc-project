package com.project.ABCDEproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruitment {

	int ID;				
	int WRITER_ID;	
	String TITLE;	
	String CONTENT;	
	String WRITE_DATE;		
}
