package com.project.ABCDEproject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.project.ABCDEproject.vo.MatchingRegion;
import com.project.ABCDEproject.vo.Region;

@Mapper
public interface RegionDAO {

	ArrayList<Region> getRegionList();

	void createMatchingRegion(MatchingRegion mr);
	
}
