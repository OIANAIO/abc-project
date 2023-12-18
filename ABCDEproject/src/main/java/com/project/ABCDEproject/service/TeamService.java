package com.project.ABCDEproject.service;

import com.project.ABCDEproject.vo.Team;

public interface TeamService {

	int selectId(String username);

	void makeTeam(Team team);

}
