package com.project.ABCDEproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.TeamDAO;
import com.project.ABCDEproject.vo.Team;

@Service
public class TeamServiceImpl implements TeamService{
	
	@Autowired
	TeamDAO dao;

	@Override
	public int selectId(String username) {
		int result = dao.selectId(username);
		
		return result;
	}

	@Override
	public void makeTeam(Team team) {
		dao.makeTeam(team);
	}


	@Override
	public ArrayList<Team> getTeamListFilterID(int userid) {
		// TODO Auto-generated method stub
		return dao.getTeamListFilterID(userid);
	}
	
} // service
