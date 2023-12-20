package com.project.ABCDEproject.service;

import java.util.ArrayList;

import com.project.ABCDEproject.vo.Recruitment;

public interface RecruitmentService {

	ArrayList<Recruitment> selectList(String type, String searchWord);

	int writeBoard(Recruitment recruitment);

}
