package com.project.ABCDEproject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.ABCDEproject.service.HomeService;
import com.project.ABCDEproject.vo.Member;
import com.project.ABCDEproject.vo.Team;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	HomeService service;
	
	
	
	@GetMapping("/")
	public String home(Model model) {
		
		ArrayList<Member> memberlist=service.getMemberRankList();
		ArrayList<Team> teamlist=service.getTeamRankList();
		
		
		model.addAttribute("memberlist",memberlist);
		return "home";
	}
	
} // controller
