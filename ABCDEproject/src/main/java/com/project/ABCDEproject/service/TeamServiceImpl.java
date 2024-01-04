package com.project.ABCDEproject.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.TeamDAO;
import com.project.ABCDEproject.vo.MatchingTeam;
import com.project.ABCDEproject.vo.Member;
import com.project.ABCDEproject.vo.Team;
import com.project.ABCDEproject.vo.TeamInvite;
import com.project.ABCDEproject.vo.TeamMember;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TeamServiceImpl implements TeamService{
	
	@Autowired
	TeamDAO dao;

	@Override
	public void makeTeam(Team team) {
		dao.makeTeam(team);
	}


	@Override
	public ArrayList<Team> getTeamListFilterID(int userid) {
		return dao.getTeamListFilterID(userid);
	}

	@Override
	public ArrayList<Team> teamList(int memberid) {
		ArrayList<Team> teamList = dao.teamList(memberid);
		
		return teamList;
	}


	@Override
	public Team getTeamLeader(int team_id) {
		return dao.getTeamLeader(team_id);
	}


	@Override
	public ArrayList<TeamMember> getTeamMemberList(int team_id) {
		return dao.getTeamMemberList(team_id);
	}


	@Override
	public Team selectTeam(int teamId) {
		Team team = dao.selectTeam(teamId);
		return team;
	}


	@Override
	public ArrayList<Integer> getTeamMemberIdList(int teamId) {
		ArrayList<Integer> teamMemberIdList = dao.getTeamMemberIdList(teamId);
		return teamMemberIdList;
	}

	@Override
	public ArrayList<Member> getTeamMember(ArrayList<Integer> teamMemberId) {
		ArrayList<Member> teamMember = dao.getTeamMember(teamMemberId);
		return teamMember;
	}

	@Override
	public void deleteMember(TeamMember tm) {
		dao.deleteMember(tm);
	}


	@Override
	public void inviteMember(TeamInvite ti) {
		dao.inviteMember(ti);
	}


	@Override
	public ArrayList<TeamInvite> getInvList(int id) {
		ArrayList<TeamInvite> invList = dao.getInvList(id);
		
		return invList;
	}


	@Override
	public String getTeamName(int tiTeamId) {
		String teamName = dao.getTeamName(tiTeamId);
		
		return teamName;
	}


	@Override
	public void addTeamMember(TeamMember tm) {
		dao.addTeamMember(tm);
	}


	@Override
	public void deleteInv(TeamMember tm) {
		dao.deleteInv(tm);
	}


	@Override

	public MatchingTeam getTeamMatching(int matching_team_id) {
		return dao.getTeamMatching(matching_team_id);

	public void deleteTeam(int teamId) {
		dao.deleteTeam(teamId);
	}


	@Override
	public void deleteAllMember(int teamId) {
		dao.deleteAllMember(teamId);
	}


	@Override
	public void updateTeam(Team team) {
		dao.updateTeam(team);
	}

	@Override
	public ArrayList<Member> searchMem(int teamId, String searchWord) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("searchWord", searchWord);
		map.put("teamId", teamId);
		ArrayList<Member> list = dao.searchMem(map);
		return list;
	}


	
} // service
