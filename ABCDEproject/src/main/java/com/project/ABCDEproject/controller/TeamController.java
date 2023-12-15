package com.project.ABCDEproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ABCDEproject.service.TeamService;

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
	
} // controller
