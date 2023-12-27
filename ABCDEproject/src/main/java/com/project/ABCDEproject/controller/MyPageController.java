package com.project.ABCDEproject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ABCDEproject.service.MatchingService;
import com.project.ABCDEproject.service.MemberService;
import com.project.ABCDEproject.service.MyPageService;
import com.project.ABCDEproject.vo.MatchingTeam;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("myPage")
public class MyPageController {
	
	@Autowired
	MyPageService service;
	
	
	
	@GetMapping("myPage")
	public String myPage(@AuthenticationPrincipal UserDetails user, Model model) {
		
		
		return "myPage/myPage";
	}
	
} // controller
