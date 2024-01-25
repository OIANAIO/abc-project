package com.project.ABCDEproject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.ABCDEproject.service.MatchingService;
import com.project.ABCDEproject.service.MemberService;
import com.project.ABCDEproject.service.MyPageService;
import com.project.ABCDEproject.service.RecordService;
import com.project.ABCDEproject.service.ReviewService;
import com.project.ABCDEproject.service.StadiumService;
import com.project.ABCDEproject.service.TeamService;
import com.project.ABCDEproject.vo.MatchingTeam;
import com.project.ABCDEproject.vo.Member;
import com.project.ABCDEproject.vo.RecordCount;
import com.project.ABCDEproject.vo.Review;
import com.project.ABCDEproject.vo.ReviewRequest;
import com.project.ABCDEproject.vo.Stadium;
import com.project.ABCDEproject.vo.Team;
import com.project.ABCDEproject.vo.TeamInvite;
import com.project.ABCDEproject.vo.TeamMember;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("myPage")
public class MyPageController {
	
	@Autowired
	MyPageService service;
	
	@Autowired
	MatchingService mats;
	
	@Autowired
	MemberService mems;
	
	@Autowired
	TeamService ts;
	
	@Autowired
	ReviewService revS;
	
	@Autowired
	StadiumService staS;
	
	@Autowired
	RecordService recS;
	
	@ResponseBody
	@GetMapping("getStadium")
	public int getStadium(@RequestParam(name="schedule_id")int schedule_id)
	{
		Stadium stadium= staS.getStadiumByScheduleID(schedule_id);
		return stadium.getId();
	}
	
	@ResponseBody
	@GetMapping("getReview")
	public int getSReview(@RequestParam(name="matching_team_id")int matching_team_id)
	{
		ReviewRequest reviewRequest= revS.getReveiwByMatchingTeamID(matching_team_id);
		return reviewRequest.getTarget_team_id();
	}
	
	@GetMapping("myPage")
	public String myPage(@AuthenticationPrincipal UserDetails user, Model model) {
		
		int userid=mems.getId(user.getUsername());
		
		ArrayList<Integer> requestList=revS.getRequestMatchingTeamIDListByID(userid);
		ArrayList<MatchingTeam> list=mats.getMatchingTeamList(userid);
		
		for(MatchingTeam i : list) {
			String team_name = ts.getTeamName(i.getTeam_id());
			i.setTeam_name(team_name);
		}
		
		model.addAttribute("reviewRequestIDList",requestList);
		model.addAttribute("requestlist",list);
		return "myPage/myPage";
	}
	
	@GetMapping("myPage2")
	public String myPage2(@AuthenticationPrincipal UserDetails user, Model model) {
		String username = user.getUsername();
		int id = mems.getId(username);
		ArrayList<TeamInvite> invList = ts.getInvList(id);
		for (TeamInvite teamInvite : invList) {
			int tiId = teamInvite.getAddressee_id();
			String tiAddresseeName = mems.getMemberid(tiId);
			teamInvite.setAddressee_name(tiAddresseeName);
			
			int tiTeamId = teamInvite.getTeam_id();
			String tiTeamName = ts.getTeamName(tiTeamId);
			teamInvite.setTeam_name(tiTeamName);
		}
		model.addAttribute("invList", invList);
		
		return "myPage/myPage2";
	}
	
	@GetMapping("myPage3")
	public String myPage3(@AuthenticationPrincipal UserDetails user, Model model) {
		String username = user.getUsername();
		Member member = mems.getMember(username);
		model.addAttribute("member", member);
		
		RecordCount recordCount=recS.getRecordCount(member.getId());
		model.addAttribute("record",recordCount);
		try {
			ArrayList<Integer> teamIdList = ts.getTeamIdList(member.getId());
			ArrayList<Team> teamList = ts.getTeamList(teamIdList);
			for(Team t : teamList) {
				int tlid = t.getLeader_id();
				t.setLeader_memberid(mems.getMemberid(tlid));
			}
			
			model.addAttribute("teamList", teamList);
		} catch (Exception e) {
		}
		
		return "myPage/myPage3";
	}
	
	@GetMapping("myPageAdmin")
	public String myPageAdmin(@AuthenticationPrincipal UserDetails user, Model model) {
		String username = user.getUsername();
		Member member = mems.getMember(username);
		model.addAttribute("member", member);
		
		RecordCount recordCount=recS.getRecordCount(member.getId());
		model.addAttribute("record",recordCount);
		try {
			ArrayList<Integer> teamIdList = ts.getTeamIdList(member.getId());
			ArrayList<Team> teamList = ts.getTeamList(teamIdList);
			for(Team t : teamList) {
				int tlid = t.getLeader_id();
				t.setLeader_memberid(mems.getMemberid(tlid));
			}
			
			model.addAttribute("teamList", teamList);
		} catch (Exception e) {
		}
		
		return "myPage/myPageAdmin";
	}
	
	@GetMapping("invOK")
	public String invOK(@RequestParam(name="teamId", defaultValue = "0") int teamId, @AuthenticationPrincipal UserDetails user, Model model) {
		String username = user.getUsername();
		int id = mems.getId(username);
		TeamMember tm = new TeamMember();
		tm.setMember_id(id);
		tm.setTeam_id(teamId);
		ts.addTeamMember(tm);
		ts.deleteInv(tm);
		
		ArrayList<TeamInvite> invList = ts.getInvList(id);
		for (TeamInvite teamInvite : invList) {
			int tiId = teamInvite.getAddressee_id();
			String tiAddresseeName = mems.getMemberid(tiId);
			teamInvite.setAddressee_name(tiAddresseeName);
			
			int tiTeamId = teamInvite.getTeam_id();
			String tiTeamName = ts.getTeamName(tiTeamId);
			teamInvite.setTeam_name(tiTeamName);
		}
		model.addAttribute("invList", invList);
		
		return "myPage/myPage2";
	}
	
	@GetMapping("invNO")
	public String invNO(@RequestParam(name="teamId", defaultValue = "0") int teamId, @AuthenticationPrincipal UserDetails user, Model model) {
		String username = user.getUsername();
		int id = mems.getId(username);
		TeamMember tm = new TeamMember();
		tm.setMember_id(id);
		tm.setTeam_id(teamId);
		ts.deleteInv(tm);
		
		ArrayList<TeamInvite> invList = ts.getInvList(id);
		for (TeamInvite teamInvite : invList) {
			int tiId = teamInvite.getAddressee_id();
			String tiAddresseeName = mems.getMemberid(tiId);
			teamInvite.setAddressee_name(tiAddresseeName);
			
			int tiTeamId = teamInvite.getTeam_id();
			String tiTeamName = ts.getTeamName(tiTeamId);
			teamInvite.setTeam_name(tiTeamName);
		}
		model.addAttribute("invList", invList);
		
		return "myPage/myPage2";
	}
	
} // controller



