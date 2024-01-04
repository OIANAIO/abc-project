package com.project.ABCDEproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.StadiumDAO;
import com.project.ABCDEproject.vo.Stadium;

@Service
public class StadiumServiceImpl implements StadiumService{
	
	@Autowired
	StadiumDAO dao;

	@Override
	public ArrayList<Stadium> GetStadiumList() {
		// TODO Auto-generated method stub
		
		return dao.GetStadiumList();
	}

	@Override
	public Stadium getStadium(int stadiumid) {
		return dao.getStadium(stadiumid);
	}

	@Override
	public void createStadium(Stadium stadium) {
		dao.createStadium(stadium);
		
	}

	@Override
	public Stadium getStadiumByScheduleID(int schedule_id) {
		return dao.getStadiumByScheduleID(schedule_id);
	}
	
}
