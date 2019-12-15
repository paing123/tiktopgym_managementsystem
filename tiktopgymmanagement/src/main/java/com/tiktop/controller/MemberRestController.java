package com.tiktop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiktop.model.Member;
import com.tiktop.services.MemberService;

@RestController
public class MemberRestController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/memberlist")
	public List<Member> getAllMembers() {
		List<Member> members = memberService.findMember(new Member());
		return members;
	}
}
