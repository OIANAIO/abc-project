package com.project.ABCDEproject.service;

import java.util.ArrayList;

import com.project.ABCDEproject.vo.Schedule;
import com.project.ABCDEproject.vo.StadiumSchedule;

public interface ScheduleService {

	ArrayList<StadiumSchedule> getScheduleList();

	void addSchedule(Schedule schedule);
	

}
