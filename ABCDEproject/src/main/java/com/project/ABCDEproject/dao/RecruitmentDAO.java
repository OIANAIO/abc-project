package com.project.ABCDEproject.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.Reply;
import com.project.ABCDEproject.vo.Recruitment;

@Mapper
public interface RecruitmentDAO {

	ArrayList<Recruitment> selectList(HashMap<String, String> map);

	int writeBoard(Recruitment recruitment);

	int writerecruitment(Recruitment recruitment);

	void updateHits(int id);

	Recruitment readRecruitment(int id);

	int updateRecruitment(Recruitment recruitment);

}
