package com.project.ABCDEproject.service;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.ReviewDAO;
import com.project.ABCDEproject.vo.Review;
import com.project.ABCDEproject.vo.ReviewRequest;


@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	ReviewDAO dao;

	@Override
	public void sendReviewRequest(ReviewRequest request) {
		dao.sendReviewRequest(request);
	}

	@Override
	public ArrayList<ReviewRequest> getRequestByID(int userid) {
		return dao.getRequestByID(userid);
	}


	@Override
	public ArrayList<Integer> getRequestMatchingTeamIDListByID(int userid) {
		return dao.getRequestMatchingTeamIDListByID(userid);
	}

	@Override
	public ReviewRequest getReveiwByMatchingTeamID(int matching_team_id) {
		return dao.getReveiwByMatchingTeamID(matching_team_id);
	}

	@Override
	public void addReview(Review review) {
		dao.addReview(review);
		
	}

	@Override
	public void processReviewRequestByMatchingTeamID(int matching_team_id) {
		dao.processReviewRequestByMatchingTeamID(matching_team_id);
	}

	@Override
	public Review getAVGReview(int teamId) {
		return dao.getAVGReview(teamId);
	}

}