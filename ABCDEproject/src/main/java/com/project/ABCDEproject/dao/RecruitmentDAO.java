package com.project.ABCDEproject.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.project.ABCDEproject.vo.Recruitment;

@Mapper
public interface RecruitmentDAO {

	ArrayList<Recruitment> selectList(HashMap<String, String> map, RowBounds rb);

	int writeBoard(Recruitment recruitment);

	int writerecruitment(Recruitment recruitment);

	void updateHits(int id);

	Recruitment readRecruitment(int id);

	int updateRecruitment(Recruitment recruitment);

	void deleteBoard(int id);

	int countTotal(HashMap<String, String> map);

	ArrayList<Recruitment> search(Map<String, String> map);

}