package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.Team;

@Mapper
public interface TeamDAO {

	void makeTeam(Team team);

	ArrayList<Team> getTeamListFilterID(int userid);

	ArrayList<Team> teamList(int memberid);
	
	
}
