package com.project.ABCDEproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.project.ABCDEproject.service.MemberService;
import com.project.ABCDEproject.util.FileService;
import com.project.ABCDEproject.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("member")
public class MemberController {
	
	@Value("${spring.servlet.multipart.location}")
	String uploadpath;
	
	
	@Autowired
	MemberService service;
	
	@GetMapping("join")
	public String join() {
		return "member/joinForm";
	}
	
	@PostMapping("join")
	public String join(MultipartFile upload, Member member) {
		log.debug("join_param: {}", upload);
		if (!upload.isEmpty()) {
			String savefile = FileService.saveFile(upload, uploadpath);
			member.setOriginalthumbnail(upload.getOriginalFilename());
			member.setThumbnail(savefile);
		}
		service.joinMember(member);
		return "redirect:/";
	}
	
	@GetMapping("loginForm")
	public String loginForm() {
		return "member/loginForm";
	}
	
	@GetMapping("update")
	public String updateForm(@AuthenticationPrincipal UserDetails user, Model model) {
		Member member = service.selectMember(user.getUsername());
		model.addAttribute("member", member);
		
		return "member/updateForm";
	}
	
	@PostMapping("update")
	public String updateMember(@AuthenticationPrincipal UserDetails user, Member member) {
		member.setMemberid(user.getUsername());
		int result = service.updateMember(member);
		log.debug("update: {}", result);
		
		return "redirect:/";
	}
	
	@GetMapping("delete")
	public String delete(@AuthenticationPrincipal UserDetails user) {
		service.deleteMember(user.getUsername());
		return "redirect:/member/logout";
	}
	
} // controller



