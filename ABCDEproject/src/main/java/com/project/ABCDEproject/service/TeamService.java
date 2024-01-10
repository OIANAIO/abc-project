package com.project.ABCDEproject.service;

import java.util.ArrayList;

import com.project.ABCDEproject.vo.Match;
import com.project.ABCDEproject.vo.MatchingTeam;
import com.project.ABCDEproject.vo.Member;
import com.project.ABCDEproject.vo.Team;
import com.project.ABCDEproject.vo.TeamInvite;
import com.project.ABCDEproject.vo.TeamMember;

public interface TeamService {

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

	void addTeamMember(TeamMember tm);

	void deleteInv(TeamMember tm);

	MatchingTeam getTeamMatching(int matching_team_id);

	void deleteTeam(int teamId);

	void deleteAllMember(int teamId);

	void updateTeam(Team team);

	ArrayList<Member> searchMem(int teamId, String searchWord);

	Team getTeamByID(int teamid);

	ArrayList<Integer> getLeaderIdList();

	ArrayList<Team> getMyTeam(int memberid);

	int getTeamID(String selectedTeam);

	ArrayList<Integer> getTeamIdList(int id);

	ArrayList<Team> getTeamList(ArrayList<Integer> teamIdList);

}
