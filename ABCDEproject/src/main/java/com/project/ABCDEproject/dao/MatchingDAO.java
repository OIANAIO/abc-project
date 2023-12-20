package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.MatchingTeam;

@Mapper
public interface MatchingDAO {

	void createMatchingTeam(MatchingTeam mt);

	ArrayList<MatchingTeam> getMatchingTeamList(int user_id);

}
