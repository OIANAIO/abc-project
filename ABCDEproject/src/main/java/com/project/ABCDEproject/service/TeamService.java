package com.project.ABCDEproject.service;

import java.util.ArrayList;

import com.project.ABCDEproject.vo.Team;

public interface TeamService {

	void makeTeam(Team team);

	ArrayList<Team> getTeamListFilterID(int userid);

	ArrayList<Team> teamList(int memberid);

}
