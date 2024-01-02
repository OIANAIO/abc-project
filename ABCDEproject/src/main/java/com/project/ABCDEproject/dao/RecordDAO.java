package com.project.ABCDEproject.dao;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.Record;

@Mapper
public interface RecordDAO {

	void addRecord(Record record);
	
	
}
