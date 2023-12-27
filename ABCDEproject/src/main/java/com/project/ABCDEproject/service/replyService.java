package com.project.ABCDEproject.service;

import java.util.ArrayList;

import com.project.ABCDEproject.vo.Reply;

public interface replyService {

	void createreply(Reply r);

	ArrayList<Reply> RecruitmentList(int id);

	void deleteReply(int id);

	Reply selectReplyid(int id);

}
