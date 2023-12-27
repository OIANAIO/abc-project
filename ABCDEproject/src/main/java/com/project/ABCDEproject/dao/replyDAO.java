package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.Reply;

@Mapper
public interface replyDAO {

	void createreply(Reply r);

	ArrayList<Reply> selectReplyAll(int id);

	void deleteReply(int id);

	Reply selectReplyid(int id);
	
}
