package com.project.ABCDEproject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.RecruitmentDAO;
import com.project.ABCDEproject.util.PageNavigator;
import com.project.ABCDEproject.vo.Recruitment;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {

	@Autowired
	RecruitmentDAO dao;

	
	@Override
	public ArrayList<Recruitment> selectList(PageNavigator navi, String type, String searchWord) {
		HashMap<String, String> map = getMap(type, searchWord);
		System.out.println(navi);
		RowBounds rb = new RowBounds(navi.getStartRecord(), navi.getCountPerPage());
		System.out.println(type);
		ArrayList<Recruitment> result = dao.selectList(map, rb);
		return result;
	}

	private HashMap<String, String> getMap(String type, String searchWord) {
		HashMap<String, String> map = new HashMap<>();
		map.put("type", type);
		map.put("searchWord", searchWord);
		return map;
	}

	@Override
	public void writeRecruitment(Recruitment recruitment) {
		dao.writerecruitment(recruitment);
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

	@Override
	public void deleteBoard(int id) {
		dao.deleteBoard(id);

	}

	@Override
	public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage
			, int page, String type, String searchWord) {
		HashMap<String, String> map = getMap(type, searchWord);
		
		// 검색 키워드가 있든 없든 전체글수를 DB로 부터 조회하기
		// 전체 글 수
		int total = dao.countTotal(map);
		
		PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, total);
		return navi;
	}

	@Override
	public ArrayList<Recruitment> search(Map<String, String> map) {
		ArrayList<Recruitment> recruitmentList = dao.search(map);
		return recruitmentList;
	}


}
