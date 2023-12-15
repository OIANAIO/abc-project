package com.project.ABCDEproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.RecordDAO;

@Service
public class RecordServiceImpl implements RecordService{
	
	@Autowired
	RecordDAO dao;
	
}
