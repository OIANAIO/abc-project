package com.project.ABCDEproject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.ABCDEproject.service.MemberService;
import com.project.ABCDEproject.service.RecruitmentService;
import com.project.ABCDEproject.service.TeamService;
import com.project.ABCDEproject.service.replyService;
import com.project.ABCDEproject.util.PageNavigator;
import com.project.ABCDEproject.vo.Recruitment;
import com.project.ABCDEproject.vo.Reply;
import com.project.ABCDEproject.vo.Team;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("recruitment")
public class RecruitmentController {

	@Autowired
	RecruitmentService service;

	@Autowired
	replyService Rservice;
	
	@Autowired
	TeamService ts;
	
	@Autowired
	MemberService ms;

	@Value("${user.board.page}")
	int countPerPage;
	
	@Value("${user.board.page}")
	int pagePerGroup;
	
	@GetMapping("recruitmentList")
	public String recruitmentList(Model model, @RequestParam(name = "page", defaultValue = "1") int page, String type, String searchWord, @AuthenticationPrincipal UserDetails user) {
		PageNavigator navi = service.getPageNavigator(pagePerGroup, countPerPage, page, type, searchWord);
		ArrayList<Recruitment> recruitmentList = service.selectList(navi, type, searchWord);
//		log.debug("ㅇㅇㅇㅇㅇㅇ{}", recruitmentList);
//		log.debug("페이지퍼:{}", pagePerGroup);
//		log.debug("카운터:{}", countPerPage);
		
		ArrayList<Integer> leaderIdList = ts.getLeaderIdList();
		int memberId = ms.getId(user.getUsername());
		boolean result = false;
		for(int i: leaderIdList) {
			if(i == memberId) {
				result = true;
			}
		}
		
		model.addAttribute("navi", navi);
		model.addAttribute("recruitmentList", recruitmentList);
		model.addAttribute("type", type);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("result", result);
		
		return "/recruitment/recruitmentList";
	}

	@GetMapping("write")
	public String write() {
		return "recruitment/write";
	}

	@PostMapping("write")
	public String writeForm(Recruitment recruitment, @AuthenticationPrincipal UserDetails user) {

		recruitment.setWriter_id(user.getUsername());
		log.debug("FFFFFFFFFFFFF{}", recruitment);
		int result = service.writeRecruitment(recruitment);

		return "redirect:/recruitment/recruitmentList";
	}

	@GetMapping("read")
	public String read(@RequestParam(name = "id", defaultValue = "0") int id, Model model) {
		Recruitment recruitment = service.readRecruitment(id);
		model.addAttribute("recruitment", recruitment);

		ArrayList<Reply> replyList = Rservice.RecruitmentList(id);
		log.debug("리플아이디 {}", id);

		log.debug("리플리스트 {}", replyList);

		model.addAttribute("replyList", replyList);

		return "recruitment/read";
	}

	@GetMapping("update")
	public String update(int id, @AuthenticationPrincipal UserDetails user, Model model) {

		Recruitment recruitment = service.readRecruitment(id);
		if (!recruitment.getWriter_id().equals(user.getUsername())) {
			return "redirect:/recruitment/recruitmentList";
		}
		model.addAttribute("recruitment", recruitment);

		return "/recruitment/update";
	}

	@PostMapping("update")
	public String update(Recruitment recruitment, @AuthenticationPrincipal UserDetails user) {
		log.debug("수정할 글정보 : {}", recruitment);
		// 작성자 아이디 추가
		recruitment.setWriter_id(user.getUsername());
		int result = service.updaterecruitment(recruitment);
		return "redirect:/recruitment/read?id=" + recruitment.getId();
	}

	@GetMapping("delete")
	public String delete(int id, @AuthenticationPrincipal UserDetails user) {
		Recruitment recruitment = service.readRecruitment(id);
		if (recruitment.getId() == id) {
			if (user.getUsername().equals(recruitment.getWriter_id())) {

				service.deleteBoard(id);
				return "redirect:/recruitment/recruitmentList";
			}

		}
		return "redirect:/recruitment/recruitmentList";
	}
}
