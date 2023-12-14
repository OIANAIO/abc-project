package com.project.ABCDEproject.dao;

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
	
	
} // DAO
