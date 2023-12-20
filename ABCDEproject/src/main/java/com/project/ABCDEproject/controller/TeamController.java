package com.project.ABCDEproject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.ABCDEproject.service.MemberService;
import com.project.ABCDEproject.service.TeamService;
import com.project.ABCDEproject.util.FileService;
import com.project.ABCDEproject.vo.Team;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("team")
public class TeamController {
	
	@Value("${spring.servlet.multipart.location}")
	String uploadpath;
	
	@Autowired
	TeamService service;
	
	@Autowired
	MemberService ms;
	
	@GetMapping("teamList")
	public String teamList(@AuthenticationPrincipal UserDetails user, Model model) {
		int memberid = ms.getId(user.getUsername());
		ArrayList<Team> teamList = service.teamList(memberid);
		model.addAttribute("teamList", teamList);
		
		return "team/teamList";
	}
	
	@GetMapping("makeTeam")
	public String makeTeam() {
		return "team/makeTeam";
	}
	
	@PostMapping("makeTeam")
	public String makeTeam(@AuthenticationPrincipal UserDetails user, MultipartFile upload, Team team) {
		int id = ms.getId(user.getUsername());
		team.setLeader_id(id);
		if (!upload.isEmpty()) {
			String savefile = FileService.saveFile(upload, uploadpath);
			team.setOriginal_icon(upload.getOriginalFilename());
			team.setTeam_icon(savefile);
		}
		service.makeTeam(team);
		
		return "redirect:/team/teamList";
	}
	
	
} // controller



