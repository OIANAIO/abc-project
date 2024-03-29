package com.project.ABCDEproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.ABCDEproject.service.MailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MailController {
	
	private final MailService mailService;
	
	@ResponseBody
    @PostMapping("mail")
    public String MailSend(String mail){
       log.debug("mail:{}", mail);

       int number = mailService.sendMail(mail);

       String num = "" + number;

       return num;
    }
	
} // controller
