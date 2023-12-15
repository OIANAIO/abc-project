package com.project.ABCDEproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ABCDEproject.service.RecruitmentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("recruitment")
public class RecruitmentController {
	
	@Autowired
	RecruitmentService service;
	
	@GetMapping("recruitmentList")
	public String recruitmentList() {
		return "recruitment/recruitmentList";
	}
	
} // controller
