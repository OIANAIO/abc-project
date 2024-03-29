package com.project.ABCDEproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {

	int id;
	int winner_team_id;
	int loser_team_id;
	int match_id;
	int wincount;
	int losecount;
	String match_date;
	String winner_team_name;
	String loser_team_name;
}
