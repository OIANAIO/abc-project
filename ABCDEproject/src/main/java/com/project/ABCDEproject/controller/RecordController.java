package com.project.ABCDEproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ABCDEproject.service.RecordService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("record")
public class RecordController {
	
	@Autowired
	RecordService service;
	
	@GetMapping("recordList")
	public String recordList() {
		return "record/recordList";
	}
	
} // controller
