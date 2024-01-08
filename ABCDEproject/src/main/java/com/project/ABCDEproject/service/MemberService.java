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

	Member getMember(String username);

	String getThumb(String username);

	ArrayList<String> memberidSearchList(String memberid);

	ArrayList<String> emailSearchList(String email);

	ArrayList<String> phoneSearchList(String phone);
	
}
