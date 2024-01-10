package com.project.ABCDEproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.HomeDAO;
import com.project.ABCDEproject.dao.MemberDAO;
import com.project.ABCDEproject.dao.RecruitmentDAO;
import com.project.ABCDEproject.dao.TeamDAO;
import com.project.ABCDEproject.vo.Member;
import com.project.ABCDEproject.vo.Recruitment;
import com.project.ABCDEproject.vo.Team;

@Service
public class HomeServiceImpl implements HomeService{
	
	@Autowired
	HomeDAO HomeDAO;
	
	@Autowired
	MemberDAO memDAO;
	
	@Autowired
	TeamDAO teamDAO;
	
	@Autowired
	RecruitmentDAO reDAO;
	
	@Override
	public ArrayList<Member> getMemberRankList() {
		return memDAO.getMemberRankList();
	}

	@Override
	public ArrayList<Team> getTeamRankList() {
		return teamDAO.getTeamRankList();
	}

	@Override
	public ArrayList<Recruitment> getRecruRankList() {
		ArrayList<Recruitment> recruRankList = reDAO.getRecruRankList();
		return recruRankList;
	}
	
	
	
} // Service
