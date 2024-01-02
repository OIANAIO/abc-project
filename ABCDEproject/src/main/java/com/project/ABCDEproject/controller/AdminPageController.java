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

import com.project.ABCDEproject.service.MatchingService;
import com.project.ABCDEproject.service.MemberService;
import com.project.ABCDEproject.service.MyPageService;
import com.project.ABCDEproject.service.RecordService;
import com.project.ABCDEproject.service.ReviewService;
import com.project.ABCDEproject.service.TeamService;
import com.project.ABCDEproject.vo.Match;
import com.project.ABCDEproject.vo.MatchingTeam;
import com.project.ABCDEproject.vo.Record;
import com.project.ABCDEproject.vo.ReviewRequest;
import com.project.ABCDEproject.vo.TeamMember;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("AdminPage")
public class AdminPageController {
	
	@Autowired
	MatchingService ms;
	
	@Autowired
	TeamService teamS;
	
	@Autowired
	ReviewService reviewS;
	
	@Autowired
	RecordService rs;
	
	@GetMapping("recordsetting")
	public String myPage(@AuthenticationPrincipal UserDetails user, Model model) {
		
		ArrayList<Match> ml=ms.getMatchListByState(0);
		model.addAttribute("MatchList",ml);
		return "adminpage/recordsetting";
	}
	
	@GetMapping("setwin")
	public String setWin(@RequestParam(name = "matchid")int matchid,@RequestParam(name = "target")int target) {
		Match match=ms.getMatchByID(matchid);
		Record record=new Record();
		
		if(target==0) {
			record.setWinner_team_id(match.getMatching_team_id_a());
			record.setLoser_team_id(match.getMatching_team_id_b());
		}
		else {
			record.setWinner_team_id(match.getMatching_team_id_b());
			record.setLoser_team_id(match.getMatching_team_id_a());
		}
		
		record.setMatch_id(matchid);
		
		ms.setEndMatch(matchid);
		
		rs.addRecord(record);
		
		ReviewRequest request1=new ReviewRequest();
		ReviewRequest request2=new ReviewRequest();
		
		request1.setState(0);
		request1.setTarget_member_id(teamS.getTeamLeader(match.getMatching_team_id_a()).getId());
		request1.setTarget_team_id(match.getMatching_team_id_a());
		
		request2.setState(0);
		request2.setTarget_member_id(teamS.getTeamLeader(match.getMatching_team_id_b()).getId());
		request2.setTarget_team_id(match.getMatching_team_id_b());
		
		reviewS.sendReviewRequest(request1);
		reviewS.sendReviewRequest(request2);
		
		return "redirect:/AdminPage/recordsetting";
	}
	
} // controller
