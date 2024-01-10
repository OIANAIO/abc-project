package com.project.ABCDEproject.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.MemberDAO;
import com.project.ABCDEproject.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	MemberDAO dao;

	@Override
	public void joinMember(Member member) {
		String encodedPassword = passwordEncoder.encode(member.getMemberpw());
		member.setMemberpw(encodedPassword);
		
		dao.joinMember(member);
	}

	@Override
	public Member selectMember(String username) {
		Member member = dao.selectMember(username);
		return member;
	}

	@Override
	public int updateMember(Member member) {
		int result = dao.updateMember(member);
		
		return result;
	}

	@Override
	public void deleteMember(String username) {
		dao.deleteMember(username);
	}

	@Override
	public void lastLogin(String username) {
		dao.lastLogin(username);
	}

	@Override
	public String selectId(HashMap<String, String> map) {
		String selectId = dao.selectId(map);
		return selectId;
	}

	@Override
	public int getId(String username) {
		int result = dao.getId(username);
		return result;
	}

	@Override
	public int getPoint(int id) {
		return dao.getPoint(id);
	}

	@Override
	public String getMemberid(int id) {
		String Memberid = dao.getMemberid(id);
		return Memberid;
	}

	@Override
	public ArrayList<Member> searchAddMember(String addWord) {
		ArrayList<Member> searchAddMemberList = dao.searchAddMember(addWord);
		
		return searchAddMemberList;
	}

	@Override
	public Member getMember(String username) {
		Member mem = dao.getMember(username);
		return mem;
	}

	@Override
	public String getThumb(String username) {
		String thumb = dao.getThumb(username);
		
		return thumb;
	}

	@Override
	public ArrayList<String> memberidSearchList(String memberid) {
		ArrayList<String> idSearchList = dao.memberidSearchList(memberid);
		
		return idSearchList;
	}

	@Override
	public ArrayList<String> emailSearchList(String email) {
		ArrayList<String> emailSearchList = dao.emailSearchList(email);
		
		return emailSearchList;
	}

	@Override
	public ArrayList<String> phoneSearchList(String phone) {
		ArrayList<String> phoneSearchList = dao.phoneSearchList(phone);
		
		return phoneSearchList;
	}

	@Override
	public Member getMemberOwner(int memberid) {
		Member member = dao.getMemberOwner(memberid);
		
		return member;
	}
	
	
	
} // service
