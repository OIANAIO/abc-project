package com.project.ABCDEproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

	int id;				
	int post_id;  
	String writer_id; 
	String content; 
	String write_date;
	public void setReplytext(String replytext) {
		// TODO Auto-generated method stub
		
	} 
}
