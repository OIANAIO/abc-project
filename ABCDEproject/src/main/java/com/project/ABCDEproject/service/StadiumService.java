package com.project.ABCDEproject.service;

import java.util.ArrayList;

import com.project.ABCDEproject.vo.Stadium;

public interface StadiumService {

	ArrayList<Stadium> GetStadiumList();

	Stadium getStadium(int stadiumid);

	void createStadium(Stadium stadium);

	Stadium getStadiumByScheduleID(int schedule_id);

	ArrayList<Stadium> getStadiumListFilterID(int userid);

}
