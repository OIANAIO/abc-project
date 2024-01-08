package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.Match;
import com.project.ABCDEproject.vo.MatchingTeam;
import com.project.ABCDEproject.vo.ReviewRequest;

@Mapper
public interface MatchingDAO {

	void createMatchingTeam(MatchingTeam mt);

	ArrayList<MatchingTeam> getMatchingTeamList(int user_id);

	void setMatchingTeam(MatchingTeam request);

	void deleteMatching(int matchingreqeustid);

	int getOpponent(int matchingreqeustid);

	void paymentSuccess(int matchingreqeustid);

	MatchingTeam getMatchingTeam(int opponent);

	void matchingSuccess(int matchingrequestid);

	ArrayList<MatchingTeam> getMatchingTeamListByNotMatching();

	boolean checkSchedule(int schedule_id);

	void addMatch(Match match);

	ArrayList<Match> getMatchListByState(int state);

	Match getMatchByID(int matchid);

	void setEndMatch(int matchid);

	void endMatchingTeam(int id);

}
