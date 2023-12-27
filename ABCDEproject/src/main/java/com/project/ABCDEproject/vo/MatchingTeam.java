package com.project.ABCDEproject.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchingTeam{
	
	int id;
	int resolver_id;
	int team_id;
	int state;
	String request_date;
	int shoes_count;
	int vest_count;
	int ball_count;
	int avg_point;
	int opponent;
	int schedule_id;
}
