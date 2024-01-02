package com.project.ABCDEproject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.ReviewRequest;

@Mapper
public interface ReviewDAO {

	void sendReviewRequest(ReviewRequest request);

}
