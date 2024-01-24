package com.project.ABCDEproject.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.ABCDEproject.service.HomeService;
import com.project.ABCDEproject.service.MemberService;
import com.project.ABCDEproject.vo.Member;
import com.project.ABCDEproject.vo.Recruitment;
import com.project.ABCDEproject.vo.Team;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@Autowired
	HomeService service;
	
	@Autowired
	MemberService memS;
	
	@GetMapping("/")
	public String home(@AuthenticationPrincipal UserDetails user, Model model) {
		if (user != null) {
            String userTh = memS.getThumb(user.getUsername());
            model.addAttribute("userTh", userTh);

            Member u = memS.getMember(user.getUsername());

            // Null check for 'u' object
            if (u != null) {
                model.addAttribute("isAdmin", u.isAdmin());
            } else {
                // Handle the case where 'u' is null, e.g., set isAdmin to false or handle accordingly
                model.addAttribute("isAdmin", false);
            }
        } else {
            // Handle the case where 'user' is null, e.g., set userTh to a default value or handle accordingly
            model.addAttribute("userTh", "defaultThumb");
            model.addAttribute("isAdmin", false);
        }

        ArrayList<Member> memberlist = service.getMemberRankList();
        ArrayList<Team> teamlist = service.getTeamRankList();
        ArrayList<Recruitment> recrulist = service.getRecruRankList();

        // Null check for 'memberlist', 'teamlist', and 'recrulist'
        if (memberlist != null) {
            model.addAttribute("memberlist", memberlist);
        }
        if (teamlist != null) {
            model.addAttribute("teamlist", teamlist);
        }
        if (recrulist != null) {
            model.addAttribute("recrulist", recrulist);
        }

        return "home";
    }
	
} // controller
