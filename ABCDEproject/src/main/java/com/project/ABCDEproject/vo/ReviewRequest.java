package com.project.ABCDEproject.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {

	int id;
	int state;
	int target_member_id;
	int target_team_id;
	int target_matching_team_id;
}
