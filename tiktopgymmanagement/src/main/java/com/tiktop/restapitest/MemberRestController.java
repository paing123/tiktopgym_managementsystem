package com.tiktop.restapitest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiktop.model.Member;
import com.tiktop.services.MemberService;

@RestController
public class MemberRestController {

	@Autowired
	private MemberService memberService;

//	@RequestMapping(value = "/memberlist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	public List<Member> getAllEmployees(Model model) {
//		List<Member> memberList = memberService.findMember(new Member());
//		return memberList;
//	}
//
//	@RequestMapping(value = "/memberlist/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//	public Member getMember(@ModelAttribute("id") Integer id) {
//		Member member = new Member();
//		member.setMemberId(id);
//		List<Member> members = memberService.findMember(member);
//		return members.get(0);
//	}
	
	
	@RequestMapping(value = "/memberlist", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public String getAllEmployees(Model model) throws Exception {
		List<Member> memberList = memberService.findMember(new Member());
		ObjectMapper mapper = new ObjectMapper();
		String memberListJSON = mapper.writeValueAsString(memberList);
		return memberListJSON;
	}

	@RequestMapping(value = "/memberlist/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public String getMember(@ModelAttribute("id") Integer id) throws Exception {
		Member member = new Member();
		member.setMemberId(id);
		List<Member> members = memberService.findMember(member);
		ObjectMapper mapper = new ObjectMapper();
		String memberListJSON = mapper.writeValueAsString(members);
		return memberListJSON;
	}
}
