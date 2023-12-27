package com.project.ABCDEproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ABCDEproject.service.replyService;
import com.project.ABCDEproject.vo.Reply;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("reply")
public class replyController {

	@Autowired
	replyService service;

	@PostMapping("create")
	public String create(Model model, int post_id, String content, @AuthenticationPrincipal UserDetails user) 
	{
		Reply r = new Reply();
		r.setContent(content);
		r.setWriter_id(user.getUsername());
		r.setPost_id(post_id);
		
		log.debug("리플라이 {}", r);
		service.createreply(r);
		return "redirect:/recruitment/read?id="+post_id;
	}
	@GetMapping("delete")
	public String delete(int id) {
		log.debug("삭제 {}", id);
		Reply r = service.selectReplyid(id);
		int post_id = r.getPost_id();
		service.deleteReply(id);
		return "redirect:/recruitment/read?id="+post_id;
	}

}