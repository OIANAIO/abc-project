package com.project.ABCDEproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.reviewDAO;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	reviewDAO dao;
}
