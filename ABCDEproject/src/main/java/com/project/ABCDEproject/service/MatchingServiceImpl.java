package com.project.ABCDEproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.project.ABCDEproject.dao.MatchingDAO;
import com.project.ABCDEproject.dao.RegionDAO;
import com.project.ABCDEproject.vo.MatchingTeam;
import com.project.ABCDEproject.vo.Region;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class MatchingServiceImpl implements MatchingService{
	
	@Autowired
	MatchingDAO dao;

	@Autowired
	RegionDAO rdao;
	
	@Override
	public void createMatchingTeam(MatchingTeam mt) {
		// TODO Auto-generated method stub
		dao.createMatchingTeam(mt);
	}

	@Override
	public ArrayList<MatchingTeam> getMatchingTeamList(int user_id) {
		// TODO Auto-generated method stub
		return dao.getMatchingTeamList(user_id);
	}
	//매10분마다
	@Scheduled(fixedDelay = 10000)
	public void matching() {
		// 매칭 팀 리스트를 뽑아옴
		ArrayList<Region> rlist=rdao.getRegionList();
		for(Region region : rlist) {
			
			ArrayList<MatchingTeam> requestlist=dao.getMatchingTeamListByRegion(region);
			
			for(int i=0; i<requestlist.size();i++) {//기준
				
				MatchingTeam request = requestlist.get(i);
				System.out.println(request.getId());
				System.out.println("----------");
				if(request.getState()!=0) continue;
				
				for(int j = 0; j < requestlist.size(); j++) {//비교할것
					
					
					MatchingTeam targetRequest = requestlist.get(j);
					
					if(targetRequest.getState()!=0) continue;
					
					//자기자신이 아닌가?
					if(i==j) continue;
					
					//같은 팀이 아닌가?
					if(request.getTeam_id()==targetRequest.getTeam_id()) continue;
					
					//점수가 비슷한가?[시간계산포함]
					int lowerBound=request.getAvg_point()-10;
					int upperBound=request.getAvg_point()+10;
					
					int timeWeight; //시간가중치는 10분마다 5씩쌓임
					
					LocalDateTime now = LocalDateTime.now();
					String dateString = request.getRequest_date(); 
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			        LocalDateTime requestDate = LocalDateTime.parse(dateString, formatter);
			        
			        long minutesDifference = ChronoUnit.MINUTES.between(requestDate, now);
					
					timeWeight=(int)minutesDifference/10;
					System.out.println(timeWeight);
					//범위에 맞을경우
					if(targetRequest.getAvg_point()>=(lowerBound-timeWeight) && targetRequest.getAvg_point()<=(upperBound+timeWeight))
					{
						MatchingTeam mt1 = requestlist.get(i);
						MatchingTeam mt2 = requestlist.get(j);
						//서로의 상대팀을 셋팅해줌.
						mt1.setOpponent(mt2.getId());
						mt2.setOpponent(mt1.getId());
						//서로의 상태값을 바꿈.
						mt1.setState(1);
						mt2.setState(1);
						requestlist.set(i, mt1);
						requestlist.set(j, mt2);
						break;
					}
				}
			}
			for(MatchingTeam request:requestlist) {
				dao.setMatchingTeam(request);
			}
		}
		
	}
	//매일 한번씩 

	@Override
	public void deleteMatching(int matchingreqeustid) {
		dao.deleteMatching(matchingreqeustid);
		
	}

	@Override
	public int getOpponent(int matchingreqeustid) {
		return dao.getOpponent(matchingreqeustid);
	}

	@Override
	public void paymentSuccess(int matchingreqeustid) {
		dao.paymentSuccess(matchingreqeustid);
		
	}

	@Override
	public MatchingTeam getMatchingTeam(int opponent) {
		return dao.getMatchingTeam(opponent);
	}

	@Override
	public void matchingSuccess(int matchingrequestid) {
		dao.matchingSuccess(matchingrequestid);
		
	}
} // service
