package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.Member;
import com.project.ABCDEproject.vo.Schedule;
import com.project.ABCDEproject.vo.StadiumSchedule;

@Mapper
public interface ScheduleDAO {

	ArrayList<StadiumSchedule> getScheduleList();

	void addSchedule(Schedule schedule);

	
} // DAO
