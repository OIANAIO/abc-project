package com.project.ABCDEproject.service;

import java.util.ArrayList;

import com.project.ABCDEproject.vo.Team;
import com.project.ABCDEproject.vo.TeamMember;

public interface TeamService {

	void makeTeam(Team team);

	ArrayList<Team> getTeamListFilterID(int userid);

	ArrayList<Team> teamList(int memberid);

	Team getTeamLeader(int team_id);

	ArrayList<TeamMember> getTeamMemberList(int team_id);

}
