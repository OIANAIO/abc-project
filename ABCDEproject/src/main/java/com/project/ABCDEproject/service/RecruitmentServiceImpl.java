package com.project.ABCDEproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.RecruitmentDAO;

@Service
public class RecruitmentServiceImpl implements RecruitmentService{
	
	@Autowired
	RecruitmentDAO dao;
	
} // service
