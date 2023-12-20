package com.project.ABCDEproject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.MatchingTeam;

@Mapper
public interface MatchingDAO {

	void createMatchingTeam(MatchingTeam mt);

}
