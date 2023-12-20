package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.Team;

@Mapper
public interface TeamDAO {

	int selectId(String username);

	void makeTeam(Team team);

	ArrayList<Team> getTeamListFilterID(int userid);
	
	
}
