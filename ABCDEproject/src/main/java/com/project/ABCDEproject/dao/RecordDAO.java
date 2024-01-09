package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.Record;
import com.project.ABCDEproject.vo.Review;

@Mapper
public interface RecordDAO {

	void addRecord(Record record);

	ArrayList<Record> getListById(int teamId);

	Record getResultById(int teamId);
	
	
}
