package com.project.ABCDEproject.service;

import java.util.ArrayList;

import com.project.ABCDEproject.vo.Reply;
import com.project.ABCDEproject.vo.Recruitment;

public interface RecruitmentService {

	ArrayList<Recruitment> selectList(String type, String searchWord);

	Recruitment readRecruitment(int id);

	int writeRecruitment(Recruitment recruitment);

	int updaterecruitment(Recruitment recruitment);

	void deleteBoard(int id);
}
