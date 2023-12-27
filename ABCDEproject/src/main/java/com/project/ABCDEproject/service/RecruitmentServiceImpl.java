package com.project.ABCDEproject.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.RecruitmentDAO;
import com.project.ABCDEproject.vo.Reply;
import com.project.ABCDEproject.vo.Recruitment;

@Service
public class RecruitmentServiceImpl implements RecruitmentService{
	
	@Autowired
	RecruitmentDAO dao;

	@Override
	public ArrayList<Recruitment> selectList(String type, String searchWord) {
		HashMap<String, String> map = getMap(type, searchWord);
		
		
		ArrayList<Recruitment> result = dao.selectList(map);
		return result;
	}

	private HashMap<String, String> getMap(String type, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("type", type);
		map.put("searchWord", searchWord);
		return map;
	}
	@Override
	public int writeRecruitment(Recruitment recruitment) {
		int result = dao.writerecruitment(recruitment);
		return result;
	}
	@Override
	public Recruitment readRecruitment(int id) {
		dao.updateHits(id);
		Recruitment Recruitment = dao.readRecruitment(id);
		return Recruitment;
	}

	@Override
	public int updaterecruitment(Recruitment recruitment) {
		int result = dao.updateRecruitment(recruitment);
		return result;
	}


	
}
