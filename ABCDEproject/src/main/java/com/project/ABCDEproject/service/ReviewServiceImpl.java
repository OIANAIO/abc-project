package com.project.ABCDEproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.ReviewDAO;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	ReviewDAO dao;
}
