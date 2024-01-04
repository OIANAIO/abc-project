package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.Stadium;

@Mapper
public interface StadiumDAO {

	ArrayList<Stadium> GetStadiumList();

	Stadium getStadium(int stadiumid);

	void createStadium(Stadium stadium);

	Stadium getStadiumByScheduleID(int schedule_id);
	
}
