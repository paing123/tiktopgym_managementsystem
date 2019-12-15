package com.tiktop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@RequestMapping(value = "/memberlist/{id}")
	public Member getMember(@ModelAttribute("id") Integer id) {
		Member member = new Member();
		member.setMemberId(id);
		List<Member> members = memberService.findMember(member);
		return members.get(0);
	}
}
