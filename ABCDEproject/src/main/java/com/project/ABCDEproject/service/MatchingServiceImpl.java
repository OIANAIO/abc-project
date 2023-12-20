package com.project.ABCDEproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.MatchingDAO;
import com.project.ABCDEproject.vo.MatchingTeam;

@Service
public class MatchingServiceImpl implements MatchingService{
	
	@Autowired
	MatchingDAO dao;

	@Override
	public void createMatchingTeam(MatchingTeam mt) {
		// TODO Auto-generated method stub
		dao.createMatchingTeam(mt);
	}
	
} // service
