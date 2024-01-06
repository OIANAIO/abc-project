package com.project.ABCDEproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

	int id;
	int leader_id;
	String team_icon;
	String Original_icon;
	String team_name;
	String team_description;
	String leader_memberid;
	
}
