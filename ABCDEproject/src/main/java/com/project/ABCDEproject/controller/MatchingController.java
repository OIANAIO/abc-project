package com.project.ABCDEproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ABCDEproject.service.MatchingService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("matching")
public class MatchingController {
	
	@Autowired
	MatchingService service;
	
	@GetMapping("requestMatching")
	public String requestMatching() {
		return "matching/requestMatching";
	}
	
} // controller
