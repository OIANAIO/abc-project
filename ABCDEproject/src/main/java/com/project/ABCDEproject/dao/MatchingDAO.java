package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.MatchingTeam;
import com.project.ABCDEproject.vo.Region;

@Mapper
public interface MatchingDAO {

	void createMatchingTeam(MatchingTeam mt);

	ArrayList<MatchingTeam> getMatchingTeamList(int user_id);

	ArrayList<MatchingTeam> getMatchingTeamListByRegion(Region region);

	void setMatchingTeam(MatchingTeam request);

	void deleteMatching(int matchingreqeustid);

	int getOpponent(int matchingreqeustid);

	void paymentSuccess(int matchingreqeustid);

	MatchingTeam getMatchingTeam(int opponent);

	void matchingSuccess(int matchingrequestid);

}
