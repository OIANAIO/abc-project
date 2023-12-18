package com.project.ABCDEproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ABCDEproject.service.TeamService;
import com.project.ABCDEproject.vo.Team;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("team")
public class TeamController {
	
	@Autowired
	TeamService service;
	
	@GetMapping("teamList")
	public String teamList() {
		return "team/teamList";
	}
	
	@GetMapping("makeTeam")
	public String makeTeam() {
		return "team/makeTeam";
	}
	
	@PostMapping("makeTeam")
	public String makeTeam(@AuthenticationPrincipal UserDetails user, Team team) {
		int id = service.selectId(user.getUsername());
		team.setLeader_id(id);
		service.makeTeam(team);
		
		return "redirect:/team/teamList";
	}
	
	
} // controller



