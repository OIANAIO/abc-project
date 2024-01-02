package com.project.ABCDEproject.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.ABCDEproject.vo.Member;

public interface MemberService {
	
	void joinMember(Member member);

	Member selectMember(String username);

	int updateMember(Member member);

	void deleteMember(String username);

	void lastLogin(String username);

	String selectId(HashMap<String, String> map);

	int getId(String username);

	int getPoint(int id);
	
	String getMemberid(int id);

	ArrayList<Member> searchAddMember(String addWord);
	
}
