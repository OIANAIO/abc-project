package com.project.ABCDEproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

	int id;
	int writer_id;
	int team_id;
	String content;
	int offensive;
	int defensive;
	int manner;
	int strength;
	int dribble;
	int speed;
	int pass;
	int shot;
}
