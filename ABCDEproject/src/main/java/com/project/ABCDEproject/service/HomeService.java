package com.project.ABCDEproject.service;

import java.util.ArrayList;

import com.project.ABCDEproject.vo.Member;
import com.project.ABCDEproject.vo.Recruitment;
import com.project.ABCDEproject.vo.Team;

public interface HomeService {

	ArrayList<Member> getMemberRankList();

	ArrayList<Team> getTeamRankList();

	ArrayList<Recruitment> getRecruRankList();


}
