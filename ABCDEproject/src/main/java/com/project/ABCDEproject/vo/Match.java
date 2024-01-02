package com.project.ABCDEproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Match{
	int id;
	int matching_team_id_a;
	int matching_team_id_b;
	int schedule_id;
	int state;
}
