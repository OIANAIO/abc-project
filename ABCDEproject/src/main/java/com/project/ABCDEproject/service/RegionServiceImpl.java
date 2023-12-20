package com.project.ABCDEproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.RegionDAO;
import com.project.ABCDEproject.dao.StadiumDAO;
import com.project.ABCDEproject.vo.MatchingRegion;
import com.project.ABCDEproject.vo.Region;
import com.project.ABCDEproject.vo.Stadium;

@Service
public class RegionServiceImpl implements RegionService{
	
	@Autowired
	RegionDAO dao;

	@Override
	public ArrayList<Region> getRegionList() {
		// TODO Auto-generated method stub
		return dao.getRegionList();
	}

	@Override
	public void createMatchingRegion(MatchingRegion mr) {
		// TODO Auto-generated method stub
		dao.createMatchingRegion(mr);
	}


	
}
