package com.project.ABCDEproject.service;

import java.util.ArrayList;

import com.project.ABCDEproject.vo.Match;
import com.project.ABCDEproject.vo.MatchingTeam;

public interface MatchingService {

	void createMatchingTeam(MatchingTeam mt);

	ArrayList<MatchingTeam> getMatchingTeamList(int user_id);

	void deleteMatching(int matchingreqeustid);

	int getOpponent(int matchingreqeustid);

	void paymentSuccess(int matchingreqeustid);

	MatchingTeam getMatchingTeam(int opponent);

	void matchingSuccess(int matchingrequestid);

	ArrayList<Match> getMatchListByState(int state);

	Match getMatchByID(int matchid);

	void setEndMatch(int matchid);

}
