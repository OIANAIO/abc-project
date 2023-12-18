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
	
}
