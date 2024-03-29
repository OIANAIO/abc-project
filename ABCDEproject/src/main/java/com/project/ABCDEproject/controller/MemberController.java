package com.project.ABCDEproject.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
		return "redirect:/member/loginForm";
	}
	
	@GetMapping("loginForm")
	public String loginForm() {
		return "member/loginForm";
	}
	
	@GetMapping("update")
	public String updateForm(@AuthenticationPrincipal UserDetails user, Model model) {
		Member member = service.selectMember(user.getUsername());
		model.addAttribute("member", member);
		model.addAttribute("thumb", member.getThumbnail());
		return "member/updateForm";
	}
	
	@PostMapping("update")
	public String updateMember(@AuthenticationPrincipal UserDetails user, MultipartFile upload, Member member) {
		member.setMemberid(user.getUsername());
		if (!upload.isEmpty()) {
			String savefile = FileService.saveFile(upload, uploadpath);
			member.setOriginalthumbnail(upload.getOriginalFilename());
			member.setThumbnail(savefile);
			FileService.deleteFile(uploadpath + "/" + member.getThumbnail());
		}
		
		int result = service.updateMember(member);
		log.debug("update: {}", result);
		
		return "redirect:/";
	}
	
	@GetMapping("delete")
	public String delete(@AuthenticationPrincipal UserDetails user) {
		service.deleteMember(user.getUsername());
		return "redirect:/member/logout";
	}
	
	@GetMapping("logoutin")
	public String logoutin(@AuthenticationPrincipal UserDetails user) {
		service.lastLogin(user.getUsername());
		return "redirect:/member/logout";
	}
	
	@GetMapping("findId")
	public String findId() {
		return "member/findIdForm";
	}
	
	@PostMapping("findId")
	public String findId(String email, String phone, Model model) {
		HashMap<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("phone", phone);
		String selectId = service.selectId(map);
		model.addAttribute("selectId", selectId);
		
		return "member/findId";
	}
	
	@GetMapping("getId")
	public String getId(@AuthenticationPrincipal UserDetails user) {
		int id = service.getId(user.getUsername());
		
		return "";
	}
	
	@PostMapping("memberidCheck")
	@ResponseBody
	public String memberidCheck(String memberid) {
		String msg = "";
		ArrayList<String> idSearchList = service.memberidSearchList(memberid);
		
		if(memberid.length() < 6) {
			msg = "6글자 이상으로 입력하세요";
		} else {
			if(idSearchList.size() == 0) {
				msg = "사용할 수 있는 아이디입니다";
			} else {
				msg = "중복된 아이디입니다";
			}
		}
		
		return msg;
	}
	
	@PostMapping("emailCheck")
	@ResponseBody
	public String emailCheck(String email) {
		String msg = "";
		ArrayList<String> emailSearchList = service.emailSearchList(email);
		
		if(email.length() < 1) {
			msg = "이메일을 입력하세요";
		} else {
			if(emailSearchList.size() == 0) {
				msg = "사용할 수 있는 이메일입니다";
			} else {
				msg = "중복된 이메일입니다";
			}
		}
		
		return msg;
	}
	
	@PostMapping("phoneCheck")
	@ResponseBody
	public String phoneCheck(String phone) {
		String msg = "";
		ArrayList<String> phoneSearchList = service.phoneSearchList(phone);
		
		if(phone.length() < 1) {
			msg = "전화번호를 입력하세요";
		} else {
			if(phoneSearchList.size() == 0) {
				msg = "사용할 수 있는 전화번호입니다";
			} else {
				msg = "중복된 전화번호입니다";
			}
		}
		
		return msg;
	}
	
} // controller



