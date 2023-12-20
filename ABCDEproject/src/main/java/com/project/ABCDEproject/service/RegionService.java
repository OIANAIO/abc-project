package com.project.ABCDEproject.service;

import java.util.ArrayList;

import com.project.ABCDEproject.vo.MatchingRegion;
import com.project.ABCDEproject.vo.Region;

public interface RegionService {

	ArrayList<Region> getRegionList();

	void createMatchingRegion(MatchingRegion mr);

}
