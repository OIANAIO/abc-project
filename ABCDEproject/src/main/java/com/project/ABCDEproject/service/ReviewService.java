package com.project.ABCDEproject.service;

import java.util.ArrayList;

import com.project.ABCDEproject.vo.Review;
import com.project.ABCDEproject.vo.ReviewRequest;


public interface ReviewService {
	void sendReviewRequest(ReviewRequest request);

	ArrayList<ReviewRequest> getRequestByID(int userid);

	ArrayList<Integer> getRequestMatchingTeamIDListByID(int userid);

	ReviewRequest getReveiwByMatchingTeamID(int matching_team_id);

	void addReview(Review review);

	void processReviewRequestByMatchingTeamID(int matching_team_id);

	Review getAVGReview(int teamId);

}
