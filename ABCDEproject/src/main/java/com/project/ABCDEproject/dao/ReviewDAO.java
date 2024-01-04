package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.ReviewRequest;

@Mapper
public interface ReviewDAO {

	void sendReviewRequest(ReviewRequest request);

	ArrayList<ReviewRequest> getRequestByID(int userid);

	ArrayList<Integer> getRequestMatchingTeamIDListByID(int userid);

}
