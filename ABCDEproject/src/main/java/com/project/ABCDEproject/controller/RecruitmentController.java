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

import com.project.ABCDEproject.service.RecruitmentService;
import com.project.ABCDEproject.vo.Recruitment;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("recruitment")
public class RecruitmentController {
	
	@Autowired
	RecruitmentService service;
	
	@GetMapping("recruitmentList")
	public String recruitmentList(Model model
			, @RequestParam(name = "page", defaultValue = "1")int page
			, String type
			, String searchWord) {
		
		ArrayList<Recruitment> boardList = service.selectList(type, searchWord);
		model.addAttribute("boardList", boardList);
		model.addAttribute("type", type);
		model.addAttribute("searchWord", searchWord);
		
		return "recruitment/recruitmentList";
	}
	@GetMapping("write")
	public String write() {
		return "recruitment/write";
	}
	@PostMapping("write")
	public String writeForm(Recruitment Recruitment, @AuthenticationPrincipal UserDetails user) {
		
		Recruitment.setMemberid(user.getUsername());
		log.debug("FFFFFFFFFFFFF{}",Recruitment);
		int result = service.writeBoard(Recruitment);

		return "redirect:/recruitment/recruitmentList";
	}
	
}
