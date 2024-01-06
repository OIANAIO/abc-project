package com.project.ABCDEproject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ABCDEproject.service.MatchingService;
import com.project.ABCDEproject.service.MemberService;
import com.project.ABCDEproject.service.ReviewService;
import com.project.ABCDEproject.service.ScheduleService;
import com.project.ABCDEproject.service.TeamService;
import com.project.ABCDEproject.vo.MatchingRegion;
import com.project.ABCDEproject.vo.MatchingTeam;
import com.project.ABCDEproject.vo.ReviewRequest;
import com.project.ABCDEproject.vo.Schedule;
import com.project.ABCDEproject.vo.StadiumSchedule;
import com.project.ABCDEproject.vo.Team;
import com.project.ABCDEproject.vo.TeamMember;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("matching")
public class MatchingController {
	
	@Autowired
	MemberService mb;
	
	@Autowired
	MatchingService ms;
	
	@Autowired
	ReviewService rs;
	
	@Autowired
	TeamService ts;
	
	@Autowired
	ScheduleService ss;
	
	
	@GetMapping("requestMatching")
	public String requestMatching(Model model, @AuthenticationPrincipal UserDetails user) {;
		int userid=mb.getId(user.getUsername());
		
		ArrayList<Team> tl=ts.getTeamListFilterID(userid);
		model.addAttribute("TeamList",tl);
		
		ArrayList<StadiumSchedule> sl=ss.getScheduleList();
		System.out.println(sl);
		model.addAttribute("ScheduleList",sl);
		return "matching/requestMatching";
	}
	
	@PostMapping("requestMatching")
	public String stackMatching(@AuthenticationPrincipal UserDetails user,
			String teamSelect,
			boolean shoesTF, int shoesCount,boolean vestTF, int vestCount,boolean ballTF, int ballCount, int schedule_id) {
		
		System.out.println(schedule_id);
		
		int userid=mb.getId(user.getUsername());
		MatchingTeam mt=new MatchingTeam();
		mt.setResolver_id(userid);
		mt.setState(0);
		mt.setTeam_id(Integer.parseInt(teamSelect));
		mt.setSchedule_id(schedule_id);
		System.out.println(mt.getTeam_id());
		
		ArrayList<Integer> id_list=new ArrayList<Integer>();
		int leader_id=ts.getTeamLeader(mt.getTeam_id()).getLeader_id();
		ArrayList<TeamMember> memberlist=ts.getTeamMemberList(mt.getTeam_id());
		
		id_list.add(leader_id);
		for(TeamMember ob : memberlist) {
			id_list.add(ob.getMember_id());
		}
		
		ArrayList<Integer> avg_list=new ArrayList<Integer>();
		
		for(int id:id_list) {
			avg_list.add(mb.getPoint(id));
		}
		

		System.out.println(avg_list);
		System.out.println(calculateAverage(avg_list));
		mt.setAvg_point(calculateAverage(avg_list));
				
		
		if(shoesTF)
		{
			mt.setShoes_count(shoesCount);
		}
		else
		{
			mt.setShoes_count(0);
		}
		
		if(vestTF)
		{
			mt.setVest_count(vestCount);
		}
		else
		{
			mt.setVest_count(0);
		}
		
		if(ballTF)
		{
			mt.setBall_count(shoesCount);
		}
		else
		{
			mt.setBall_count(0);
		}
		
		ms.createMatchingTeam(mt);
		
		//선호지역 저장

		return "redirect:/";
	}
	
	public static int calculateAverage(ArrayList<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }

        // 리스트 내의 모든 값 더하기
        int sum = 0;
        for (int num : list) {
            sum += num;
        }

        // 평균 계산
        return sum / list.size();
    }
	
	@GetMapping("delete")
	public String deleteMatching(@RequestParam(value="matchingrequestid", defaultValue="0") int matchingreqeustid) {
		if(matchingreqeustid!=0)
		{
			try {
				int opponentId=ms.getOpponent(matchingreqeustid);
				ms.deleteMatching(opponentId);
			}
			catch(Exception e) {
				
			}
			ms.deleteMatching(matchingreqeustid);
		}
		
		return "redirect:/myPage/myPage";
	}
	
	@GetMapping("payment")
	public String payment(@RequestParam(value="matchingrequestid", defaultValue="0") int matchingrequestid) {
		
		ms.paymentSuccess(matchingrequestid);
		int opponent=ms.getOpponent(matchingrequestid);
		MatchingTeam mt=ms.getMatchingTeam(opponent);
		if(mt.getState()==2)
		{
			ms.matchingSuccess(matchingrequestid);
			ms.matchingSuccess(opponent);
		}
		return "redirect:/myPage/myPage";
	}
} // controller
