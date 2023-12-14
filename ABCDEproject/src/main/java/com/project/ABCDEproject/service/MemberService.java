package com.project.ABCDEproject.service;

import com.project.ABCDEproject.vo.Member;

public interface MemberService {
	
	void joinMember(Member member);

	Member selectMember(String username);

	int updateMember(Member member);

	void deleteMember(String username);
	
}
