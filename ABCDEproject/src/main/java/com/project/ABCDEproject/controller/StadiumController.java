package com.project.ABCDEproject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ABCDEproject.service.StadiumService;
import com.project.ABCDEproject.vo.Stadium;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("stadium")
public class StadiumController {
	
	@Autowired
	StadiumService service;
	
	@GetMapping("stadiumList")
	public String stadiumList(Model model) {
		ArrayList<Stadium> list=service.GetStadiumList();
		System.out.println(list);
		model.addAttribute("list",list);
		return "stadium/stadiumList";
	}
	
} // controller
