package com.project.ABCDEproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.HomeDAO;
import com.project.ABCDEproject.dao.ScheduleDAO;
import com.project.ABCDEproject.vo.Member;
import com.project.ABCDEproject.vo.Schedule;

@Service
public class ScheduleServiceImpl implements ScheduleService{
	
	@Autowired
	ScheduleDAO dao;

	@Override
	public ArrayList<Schedule> getScheduleList() {
		
		return dao.getScheduleList();
	}

	
	
} // Service
