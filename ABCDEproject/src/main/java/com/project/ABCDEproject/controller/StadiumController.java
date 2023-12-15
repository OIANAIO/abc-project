package com.project.ABCDEproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ABCDEproject.service.StadiumService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("stadium")
public class StadiumController {
	
	@Autowired
	StadiumService service;
	
	@GetMapping("stadiumList")
	public String stadiumList() {
		return "stadium/stadiumList";
	}
	
} // controller
