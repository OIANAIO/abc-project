package com.project.ABCDEproject.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.MatchingTeam;
import com.project.ABCDEproject.vo.Member;
import com.project.ABCDEproject.vo.Team;
import com.project.ABCDEproject.vo.TeamInvite;
import com.project.ABCDEproject.vo.TeamMember;

@Mapper
public interface TeamDAO {

	void makeTeam(Team team);

	ArrayList<Team> getTeamListFilterID(int userid);

	ArrayList<Team> teamList(int memberid);

	Team getTeamLeader(int team_id);

	ArrayList<TeamMember> getTeamMemberList(int team_id);

	Team selectTeam(int teamId);

	ArrayList<Integer> getTeamMemberIdList(int teamId);

	ArrayList<Member> getTeamMember(ArrayList<Integer> teamMemberId);

	void deleteMember(TeamMember tm);

	void inviteMember(TeamInvite ti);

	ArrayList<TeamInvite> getInvList(int id);

	String getTeamName(int tiTeamId);

	void addTeamMember(TeamMember TeamMember);

	void deleteInv(TeamMember tm);
	
	ArrayList<Team> getTeamRankList();

	MatchingTeam getTeamMatching(int matching_team_id);

	void deleteTeam(int teamId);

	void deleteAllMember(int teamId);

	void updateTeam(Team team);

	ArrayList<Member> searchMem(HashMap<String, Object> map);

	Team getTeamByID(int teamid);

	ArrayList<Integer> getLeaderList();

	ArrayList<String> getMyTeam(int memberid);

	int getTeamId(String selectedTeam);

	
	
}
