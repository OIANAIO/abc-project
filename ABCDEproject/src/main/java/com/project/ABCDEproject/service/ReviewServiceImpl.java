package com.project.ABCDEproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.ReviewDAO;
import com.project.ABCDEproject.vo.ReviewRequest;


@Service
public class ReviewServiceImpl implements ReviewService{
	@Autowired
	ReviewDAO dao;

	@Override
	public void sendReviewRequest(ReviewRequest request) {
		dao.sendReviewRequest(request);
		
	}
	
}
