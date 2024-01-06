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

import com.project.ABCDEproject.service.MemberService;
import com.project.ABCDEproject.service.ReviewService;
import com.project.ABCDEproject.service.ScheduleService;
import com.project.ABCDEproject.service.StadiumService;
import com.project.ABCDEproject.vo.Schedule;
import com.project.ABCDEproject.vo.Stadium;

@Controller
@RequestMapping("schedule")
public class ScheduleController {

	@Autowired
	ScheduleService schS;
	
	@Autowired
	StadiumService staS;
	
	@Autowired
	MemberService memS;
	
	@GetMapping("create")
	public String createForm(@AuthenticationPrincipal UserDetails user,Model model)
	{
		int userid=memS.getId(user.getUsername());
		ArrayList<Stadium> list=staS.getStadiumListFilterID(userid);
		model.addAttribute("stadiumList",list);
		return "schedule/createForm";
	}
	
	@PostMapping("create")
	public String create(@AuthenticationPrincipal UserDetails user, Schedule schedule)
	{
		System.out.println(schedule);
		int userid=memS.getId(user.getUsername());
		schedule.setOwner_id(userid);
		schS.addSchedule(schedule);
		return "redirect:/";
	}
}
