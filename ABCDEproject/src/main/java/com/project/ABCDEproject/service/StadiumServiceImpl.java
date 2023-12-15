package com.project.ABCDEproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.StadiumDAO;

@Service
public class StadiumServiceImpl implements StadiumService{
	
	@Autowired
	StadiumDAO dao;
	
}
