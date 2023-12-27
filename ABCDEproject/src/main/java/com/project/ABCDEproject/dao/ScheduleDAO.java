package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.Member;
import com.project.ABCDEproject.vo.Schedule;

@Mapper
public interface ScheduleDAO {

	ArrayList<Schedule> getScheduleList();

	
} // DAO
