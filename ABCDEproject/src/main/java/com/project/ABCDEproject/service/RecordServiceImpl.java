package com.project.ABCDEproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.RecordDAO;
import com.project.ABCDEproject.vo.Record;
import com.project.ABCDEproject.vo.Review;

@Service
public class RecordServiceImpl implements RecordService{
	
	@Autowired
	RecordDAO dao;

	@Override
	public void addRecord(Record record) {
		dao.addRecord(record);
	}

	@Override
	public ArrayList<Record> getListById(int teamId) {
		return dao.getListById(teamId);
	}

	@Override
	public Record getResultById(int teamId) {
		return dao.getResultById(teamId);
	}
	
}
