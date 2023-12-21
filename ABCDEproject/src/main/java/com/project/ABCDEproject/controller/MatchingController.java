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

import com.project.ABCDEproject.service.MatchingService;
import com.project.ABCDEproject.service.MemberService;
import com.project.ABCDEproject.service.RegionService;
import com.project.ABCDEproject.service.TeamService;
import com.project.ABCDEproject.vo.MatchingRegion;
import com.project.ABCDEproject.vo.MatchingTeam;
import com.project.ABCDEproject.vo.Region;
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
	RegionService rs;
	
	@Autowired
	TeamService ts;
	
	
	@GetMapping("requestMatching")
	public String requestMatching(Model model, @AuthenticationPrincipal UserDetails user) {;
		int userid=mb.getId(user.getUsername());
		ArrayList<Region> rl=rs.getRegionList();
		model.addAttribute("RegionList",rl);
		
		ArrayList<Team> tl=ts.getTeamListFilterID(userid);
		model.addAttribute("TeamList",tl);
		
		return "matching/requestMatching";
	}
	
	@PostMapping("requestMatching")
	public String stackMatching(@AuthenticationPrincipal UserDetails user,
			String teamSelect, String checkRegionArray,
			boolean shoesTF, int shoesCount,boolean vestTF, int vestCount,boolean ballTF, int ballCount) {
		
		int userid=mb.getId(user.getUsername());
		MatchingTeam mt=new MatchingTeam();
		mt.setResolver_id(userid);
		mt.setState(0);
		mt.setTeam_id(Integer.parseInt(teamSelect));
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
		System.out.println("test");
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
		
		String[] resultArray = checkRegionArray.split(",");
		//선호지역 저장
		for(String region:resultArray) {
			MatchingRegion mr=new MatchingRegion();
			mr.setMatching_team_id(mt.getId());
			mr.setRegion_id(Integer.parseInt(region));
			rs.createMatchingRegion(mr);
		}

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
} // controller
