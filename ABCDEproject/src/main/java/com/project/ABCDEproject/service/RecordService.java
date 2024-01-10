package com.project.ABCDEproject.service;

import java.util.ArrayList;

import com.project.ABCDEproject.vo.Record;
import com.project.ABCDEproject.vo.RecordCount;
import com.project.ABCDEproject.vo.RecordUser;
import com.project.ABCDEproject.vo.Review;

public interface RecordService {

	void addRecord(Record record);

	ArrayList<Record> getListById(int teamId);

	Record getResultById(int teamId);

	void addDefeatUser(RecordUser recordUser);

	void addWinUser(RecordUser recordUser);

	RecordCount getRecordCount(int id);

}
