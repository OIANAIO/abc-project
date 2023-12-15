package com.project.ABCDEproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.TeamDAO;

@Service
public class TeamServiceImpl implements TeamService{
	
	@Autowired
	TeamDAO dao;
	
} // service
