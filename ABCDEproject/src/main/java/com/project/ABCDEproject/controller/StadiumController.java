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
import org.springframework.web.multipart.MultipartFile;

import com.project.ABCDEproject.service.MemberService;
import com.project.ABCDEproject.service.StadiumService;
import com.project.ABCDEproject.util.FileService;
import com.project.ABCDEproject.vo.Member;
import com.project.ABCDEproject.vo.Stadium;
import com.project.ABCDEproject.vo.Team;
import com.project.ABCDEproject.vo.TeamMember;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("stadium")
public class StadiumController {
	
	@Autowired
	StadiumService service;
	
	@Autowired
	MemberService memS;
	
	@Value("${spring.servlet.multipart.location}")
	String uploadpath;
	
	@GetMapping("stadiumList")
	public String stadiumList(Model model,@AuthenticationPrincipal UserDetails user) {
		
		ArrayList<Stadium> list=service.GetStadiumList();
		Member u=memS.getMember(user.getUsername());
		model.addAttribute("isAdmin",u.isAdmin());
		model.addAttribute("list",list);
		return "stadium/stadiumList";
	}
	
	@GetMapping("detail")
	public String detail(Model model, @RequestParam(name="stadiumid") int stadiumid){
		Stadium stadium = service.getStadium(stadiumid);
		int ownerId = stadium.getOwner();
		Member owner = memS.getMemberOwner(ownerId);
		
		model.addAttribute("stadium",stadium);
		model.addAttribute("owner", owner);
		
		return "stadium/detail";
	}
	
	@GetMapping("create")
	public String createForm(){
		
		return "stadium/createForm";
	}
	
	@PostMapping("create")
	public String create(@AuthenticationPrincipal UserDetails user, MultipartFile upload, Stadium stadium) {
		int id = memS.getId(user.getUsername());
		stadium.setOwner(id);
		if (!upload.isEmpty()) {
			String savefile = FileService.saveFile(upload, uploadpath);
			stadium.setOriginal_thumbnail(upload.getOriginalFilename());
			stadium.setThumbnail(savefile);
		}
		service.createStadium(stadium);
		return "redirect:/stadium/stadiumList";
	}
	
	
}
