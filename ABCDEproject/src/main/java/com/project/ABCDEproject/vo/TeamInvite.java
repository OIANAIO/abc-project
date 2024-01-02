package com.project.ABCDEproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamInvite {
	
	int id;
	int team_id;
	int addressee_id;
	int answer;
	
}
