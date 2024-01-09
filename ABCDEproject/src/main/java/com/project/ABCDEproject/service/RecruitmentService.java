package com.project.ABCDEproject.service;

import java.util.ArrayList;
import java.util.Map;

import com.project.ABCDEproject.util.PageNavigator;
import com.project.ABCDEproject.vo.Recruitment;

public interface RecruitmentService {

	ArrayList<Recruitment> selectList(PageNavigator navi, String type, String searchWord);

	ArrayList<Recruitment> search(Map<String, String> map);
	
	Recruitment readRecruitment(int id);

	int writeRecruitment(Recruitment recruitment);

	int updaterecruitment(Recruitment recruitment);

	void deleteBoard(int id);

	PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type, String searchWord);

	void updateTeam(String writer_id, String title, int teamid);

}
