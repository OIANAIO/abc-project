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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.exceptions.ParserInitializationException;

import com.project.ABCDEproject.service.MemberService;
import com.project.ABCDEproject.service.RecordService;
import com.project.ABCDEproject.service.ReviewService;
import com.project.ABCDEproject.service.TeamService;
import com.project.ABCDEproject.util.FileService;
import com.project.ABCDEproject.vo.Member;
import com.project.ABCDEproject.vo.Record;
import com.project.ABCDEproject.vo.Review;
import com.project.ABCDEproject.vo.Team;
import com.project.ABCDEproject.vo.TeamInvite;
import com.project.ABCDEproject.vo.TeamMember;

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
	
	@Autowired
	ReviewService revS;
	
	@Autowired
	RecordService recS;
	
	@GetMapping("teamList")
	public String teamList(@AuthenticationPrincipal UserDetails user, Model model) {
		int memberid = ms.getId(user.getUsername());
		ArrayList<Team> teamList = service.teamList(memberid);
		for(Team i: teamList) {
			i.setLeader_memberid(ms.getMemberid(memberid));
		}
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
		int teamId = team.getId();
		TeamMember tm = new TeamMember();
		tm.setMember_id(id);
		tm.setTeam_id(teamId);
		service.addTeamMember(tm);
		
		return "redirect:/team/teamList";
	}
	
	@GetMapping("detail")
	public String detail(@RequestParam(name="teamId", defaultValue = "0") int teamId, @AuthenticationPrincipal UserDetails user, Model model) {
		String leaderName = user.getUsername();
		Team team = service.selectTeam(teamId);
		model.addAttribute("teamId", teamId);
		model.addAttribute("team", team);
		model.addAttribute("leaderName", leaderName);
		ArrayList<Integer> teamMemberId = service.getTeamMemberIdList(teamId);
		try {
			ArrayList<Member> teamMember = service.getTeamMember(teamMemberId);
			model.addAttribute("teamMember", teamMember);
		} catch (Exception e) {
		}
		
		return "team/teamDetail";
	}
	
	@GetMapping("deleteMember")
	public String deleteMember(@RequestParam(name="teamId", defaultValue = "0") int teamId, @RequestParam(name="id", defaultValue = "0") int id) {
		TeamMember tm = new TeamMember();
		tm.setMember_id(id);
		tm.setTeam_id(teamId);
		service.deleteMember(tm);
		
		return "redirect:/team/detail?teamId=" + teamId;
	}
	
	@PostMapping("addMember")
	@ResponseBody
	public ArrayList<Member> addMember(String addWord, @AuthenticationPrincipal UserDetails user) {
		ArrayList<Member> list = ms.searchAddMember(addWord);
		for(Member i: list) {
			if(i.getMemberid().equals(user.getUsername())) {
				list.remove(i);
			}
		}
		
		return list;
	}
	
	@PostMapping("inviteMember")
	@ResponseBody
	public void inviteMember(String num, String teamId) {
		TeamInvite ti = new TeamInvite();
		ti.setAddressee_id(Integer.parseInt(num));
		ti.setTeam_id(Integer.parseInt(teamId));
		
		service.inviteMember(ti);
	}
	
	@PostMapping("searchMember")
	@ResponseBody
	public ArrayList<Member> searchMember(String searchWord, int teamId, @AuthenticationPrincipal UserDetails user) {
		ArrayList<Member> list = service.searchMem(teamId, searchWord);
		
		return list;
	}
	
	@GetMapping("deleteTeam")
	public String deleteTeam(@RequestParam(name="teamId", defaultValue = "0") int teamId, @AuthenticationPrincipal UserDetails user, Model model) {
		service.deleteTeam(teamId);
		service.deleteAllMember(teamId);
		
		int memberid = ms.getId(user.getUsername());
		ArrayList<Team> teamList = service.teamList(memberid);
		for(Team i: teamList) {
			i.setLeader_memberid(ms.getMemberid(memberid));
		}
		model.addAttribute("teamList", teamList);
		
		return "redirect:/team/teamList";
	}
	
	@GetMapping("updateTeam")
	public String updateTeam(@RequestParam(name="teamId", defaultValue = "0") int teamId, Model model) {
		Team team = service.selectTeam(teamId);
		model.addAttribute("team", team);
		model.addAttribute("teamId", teamId);
		
		return "team/updateTeam";
	}
	
	@PostMapping("updateTeam")
	public String updateTeam(@RequestParam(name="teamId", defaultValue = "0") int teamId, @AuthenticationPrincipal UserDetails user, Team Uteam, Model model) {
		int id = ms.getId(user.getUsername());
		Uteam.setLeader_id(id);
		Uteam.setId(teamId);
		
		service.updateTeam(Uteam);
		
		String leaderName = user.getUsername();
		Team team = service.selectTeam(teamId);
		model.addAttribute("teamId", teamId);
		model.addAttribute("team", team);
		model.addAttribute("leaderName", leaderName);
		ArrayList<Integer> teamMemberId = service.getTeamMemberIdList(teamId);
		try {
			ArrayList<Member> teamMember = service.getTeamMember(teamMemberId);
			model.addAttribute("teamMember", teamMember);
		} catch (Exception e) {
		}
		
		return "redirect:/team/detail?teamId=" + teamId;
	}
	
	@GetMapping("info")
	public String info(Model model, @RequestParam(name="teamId", defaultValue = "0") int teamId, @AuthenticationPrincipal UserDetails user) {
		
		Review review=revS.getAVGReview(teamId);
		Team team=service.getTeamByID(teamId);
		System.out.println(team);
		Record result=recS.getResultById(teamId);
		System.out.println(result);
		if(result==null)
		{
			result=new Record();
			result.setWincount(0);
			result.setLosecount(0);
		}
		ArrayList<Record> recordList=recS.getListById(teamId);
		System.out.println(recordList);
		if(review==null)
		{
			review=new Review();
			review.setTeam_id(0);
		}
		model.addAttribute("review",review);
		model.addAttribute("team",team);
		model.addAttribute("recordList",recordList);
		model.addAttribute("result",result);
		return "team/teamInfo";
	}
	
} // controller



