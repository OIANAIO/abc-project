package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.Team;
import com.project.ABCDEproject.vo.TeamMember;

@Mapper
public interface TeamDAO {

	void makeTeam(Team team);

	ArrayList<Team> getTeamListFilterID(int userid);

	ArrayList<Team> teamList(int memberid);

	Team getTeamLeader(int team_id);

	ArrayList<TeamMember> getTeamMemberList(int team_id);
	
	
}
