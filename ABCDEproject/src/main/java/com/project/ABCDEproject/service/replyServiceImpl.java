package com.project.ABCDEproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.replyDAO;
import com.project.ABCDEproject.vo.Reply;

@Service
public class replyServiceImpl implements replyService{

	@Autowired
	replyDAO dao;

	@Override
	public void createreply(Reply r) {
		// TODO Auto-generated method stub
		dao.createreply(r);
	}

	@Override
	public ArrayList<Reply> RecruitmentList(int id) {
		ArrayList<Reply> replyList = dao.selectReplyAll(id);
		return replyList;
	}

	@Override
	public void deleteReply(int id) {
		dao.deleteReply(id);

	}

	@Override
	public Reply selectReplyid(int id) {
		Reply reply = dao.selectReplyid(id);
		return reply;
	}
}
