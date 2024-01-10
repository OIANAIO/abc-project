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
import com.project.ABCDEproject.vo.Member;
import com.project.ABCDEproject.vo.Record;
import com.project.ABCDEproject.vo.RecordUser;
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
	MemberService memS;
	
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
		
		MatchingTeam team_a=teamS.getTeamMatching(match.getMatching_team_id_a());
		MatchingTeam team_b=teamS.getTeamMatching(match.getMatching_team_id_b());
		
		ArrayList<Integer> list_a=teamS.getTeamMemberIdList(team_a.getTeam_id());
		int sum_a=0;
		for(int val:list_a)
		{
			Member member=memS.selectMember(memS.getMemberid(val));
			sum_a+=member.getPoint();
		}
		int avg_a=sum_a/list_a.size();
		
		ArrayList<Integer> list_b=teamS.getTeamMemberIdList(team_b.getTeam_id());
		int sum_b=0;
		for(int val:list_b)
		{
			Member member=memS.selectMember(memS.getMemberid(val));
			sum_b+=member.getPoint();
		}
		int avg_b=sum_a/list_b.size();
		
		float aP=1/(1+10^((avg_b-avg_a)/500));
		float bP=1/(1+10^((avg_a-avg_b)/500));
		
		int addScoreA=(int)(50*(1-aP));
		int addScoreB=(int)(50*(1-bP));
		
		if(target==0) {
			record.setWinner_team_id(team_a.getTeam_id());
			record.setLoser_team_id(team_b.getTeam_id());
			for(int val:list_a)
			{
				Member member=memS.selectMember(memS.getMemberid(val));
				System.out.println(member);
				member.setPoint(member.getPoint()+addScoreA);
				memS.updateMember(member);
				
			}
			for(int val:list_b)
			{
				
				Member member=memS.selectMember(memS.getMemberid(val));
				System.out.println(member);
				member.setPoint(member.getPoint()-addScoreA);
				memS.updateMember(member);
			}
		}
		else {
			record.setWinner_team_id(team_b.getTeam_id());
			record.setLoser_team_id(team_a.getTeam_id());
			System.out.println(list_b.size());
			for(int val:list_b)
			{
				Member member=memS.selectMember(memS.getMemberid(val));
				System.out.println(member);
				memS.updateMember(member);
				member.setPoint(member.getPoint()+addScoreB);
			}
			for(int val:list_a)
			{
				Member member=memS.selectMember(memS.getMemberid(val));
				System.out.println(member);
				member.setPoint(member.getPoint()-addScoreB);
				memS.updateMember(member);
				
			}
		}
		
		record.setMatch_id(matchid);
		
		ms.setEndMatch(matchid);
		
		rs.addRecord(record);
		
		ReviewRequest request1=new ReviewRequest();
		ReviewRequest request2=new ReviewRequest();
		
		ms.endMatchingTeam(team_a.getId());
		ms.endMatchingTeam(team_b.getId());
		
		if(target==0)
		{
			for(int val:list_a)
			{
				RecordUser recordUser=new RecordUser();
				recordUser.setMember_id(val);
				recordUser.setRecord_id(record.getId());
				rs.addWinUser(recordUser);
			}
			for(int val:list_b)
			{
				RecordUser recordUser=new RecordUser();
				recordUser.setMember_id(val);
				recordUser.setRecord_id(record.getId());
				rs.addDefeatUser(recordUser);
			}
		}
		else
		{
			for(int val:list_b)
			{
				RecordUser recordUser=new RecordUser();
				recordUser.setMember_id(val);
				recordUser.setRecord_id(record.getId());
				rs.addWinUser(recordUser);
			}
			for(int val:list_a)
			{
				RecordUser recordUser=new RecordUser();
				recordUser.setMember_id(val);
				recordUser.setRecord_id(record.getId());
				rs.addDefeatUser(recordUser);
			}
		}
		System.out.println(list_b);
		request1.setState(0);
		request1.setTarget_member_id(team_a.getResolver_id());
		request1.setTarget_team_id(ms.getMatchingTeam(team_a.getOpponent()).getTeam_id());
		request1.setTarget_matching_team_id(team_a.getId());
		
		request2.setState(0);
		request2.setTarget_member_id(team_b.getResolver_id());
		request2.setTarget_team_id(ms.getMatchingTeam(team_b.getOpponent()).getTeam_id());
		request2.setTarget_matching_team_id(team_b.getId());
		
		reviewS.sendReviewRequest(request1);
		reviewS.sendReviewRequest(request2);
		
		return "redirect:/AdminPage/recordsetting";
	}
	
} // controller
