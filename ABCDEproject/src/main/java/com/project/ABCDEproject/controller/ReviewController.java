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
import com.project.ABCDEproject.service.TeamService;
import com.project.ABCDEproject.vo.MatchingTeam;
import com.project.ABCDEproject.vo.Review;
import com.project.ABCDEproject.vo.Stadium;
import com.project.ABCDEproject.vo.Team;

@Controller
@RequestMapping("review")
public class ReviewController {

	
	@Autowired
	MemberService memS;
	
	@Autowired
	TeamService teamS;
	
	@Autowired
	ReviewService revS;
	
	@GetMapping("create")
	public String createForm(@AuthenticationPrincipal UserDetails user,Model model,@RequestParam(name="team_id")int team_id,@RequestParam(name="matching_team")int matching_team)
	{
		int userid=memS.getId(user.getUsername());
		Team team=teamS.getTeamByID(team_id);
		System.out.println(team);
		model.addAttribute("team",team);
		model.addAttribute("matching_team_id",matching_team);
		return "review/createForm";
	}
	
	@PostMapping("create")
	public String create(@AuthenticationPrincipal UserDetails user,Review review, int team_id, int matching_team_id)
	{
		int userid=memS.getId(user.getUsername());
		review.setWriter_id(userid);
		revS.addReview(review);
		revS.processReviewRequestByMatchingTeamID(matching_team_id);
		return "redirect:/";
	}
}
