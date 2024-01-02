package com.project.ABCDEproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.RecordDAO;
import com.project.ABCDEproject.vo.Record;

@Service
public class RecordServiceImpl implements RecordService{
	
	@Autowired
	RecordDAO dao;

	@Override
	public void addRecord(Record record) {
		dao.addRecord(record);
		
	}
	
}
