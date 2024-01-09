package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.Review;
import com.project.ABCDEproject.vo.ReviewRequest;

@Mapper
public interface ReviewDAO {

	void sendReviewRequest(ReviewRequest request);

	ArrayList<ReviewRequest> getRequestByID(int userid);

	ArrayList<Integer> getRequestMatchingTeamIDListByID(int userid);

	ReviewRequest getReveiwByMatchingTeamID(int matching_team_id);

	void addReview(Review review);

	void processReviewRequestByMatchingTeamID(int matching_team_id);

	Review getAVGReview(int teamId);

}
