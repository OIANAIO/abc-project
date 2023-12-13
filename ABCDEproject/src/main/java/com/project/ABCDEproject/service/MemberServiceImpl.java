package com.project.ABCDEproject.service;

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
	
} // service
