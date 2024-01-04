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
import com.project.ABCDEproject.service.ReviewService;
import com.project.ABCDEproject.service.StadiumService;
import com.project.ABCDEproject.service.TeamService;
import com.project.ABCDEproject.vo.MatchingTeam;
import com.project.ABCDEproject.vo.ReviewRequest;
import com.project.ABCDEproject.vo.Stadium;
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
	
	@ResponseBody
	@GetMapping("getStadium")
	public int getStadium(@RequestParam(name="schedule_id")int schedule_id)
	{
		Stadium stadium= staS.getStadiumByScheduleID(schedule_id);
		System.out.println(schedule_id);
		return stadium.getId();
	}
	
	@GetMapping("myPage")
	public String myPage(@AuthenticationPrincipal UserDetails user, Model model) {
		
		int userid=mems.getId(user.getUsername());
		
		ArrayList<Integer> requestList=revS.getRequestMatchingTeamIDListByID(userid);
		ArrayList<MatchingTeam> list=mats.getMatchingTeamList(userid);
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
		int id = mems.getId(username);
		
		
		return "myPage/myPage3";
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



