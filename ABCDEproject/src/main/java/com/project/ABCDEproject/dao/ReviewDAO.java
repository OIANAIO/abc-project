package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.MatchingRegion;
import com.project.ABCDEproject.vo.ReviewRequest;

@Mapper
public interface ReviewDAO {

	void sendReviewRequest(ReviewRequest request);

	
}
