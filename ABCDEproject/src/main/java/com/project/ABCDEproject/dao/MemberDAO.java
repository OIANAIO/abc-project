package com.project.ABCDEproject.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.Member;

@Mapper
public interface MemberDAO {

	void joinMember(Member member);

	Member selectMember(String memberid);

	int updateMember(Member member);

	void deleteMember(String memberid);

	void lastLogin(String memberid);

	String selectId(HashMap<String, String> map);

	int getId(String username);

	int getPoint(int id);

	String getMemberid(int id);

	ArrayList<Member> searchAddMember(String addWord);

	ArrayList<Member> getMemberRankList();

	Member getMember(String username);

	String getThumb(String username);

	ArrayList<String> memberidSearchList(String memberid);

	ArrayList<String> emailSearchList(String email);

	ArrayList<String> phoneSearchList(String phone);

	Member getMemberOwner(int memberid);
	
} // DAO
