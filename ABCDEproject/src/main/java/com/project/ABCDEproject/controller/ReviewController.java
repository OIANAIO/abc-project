package com.project.ABCDEproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ABCDEproject.service.ReviewService;

@Controller
@RequestMapping
public class ReviewController {

	@Autowired
	ReviewService service;
	
}
