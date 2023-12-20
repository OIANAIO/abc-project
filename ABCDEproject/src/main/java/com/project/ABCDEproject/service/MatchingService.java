package com.project.ABCDEproject.service;

import java.util.ArrayList;

import com.project.ABCDEproject.vo.MatchingTeam;

public interface MatchingService {

	void createMatchingTeam(MatchingTeam mt);

	ArrayList<MatchingTeam> getMatchingTeamList(int user_id);

}
