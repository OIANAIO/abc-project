package com.project.ABCDEproject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		// DB의 spring5_board 테이블의 전 데이터를 가져오기
		ArrayList<Recruitment> boardList = service.selectList(type, searchWord);
		model.addAttribute("boardList", boardList);
		model.addAttribute("type", type);
		model.addAttribute("searchWord", searchWord);
		
		return "recruitment/recruitmentList";
	}
	
} // controller
