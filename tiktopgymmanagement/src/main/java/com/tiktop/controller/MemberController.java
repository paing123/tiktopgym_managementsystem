package com.tiktop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tiktop.model.Member;
import com.tiktop.services.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@RequestMapping(value = { "/admin/member" }, method = RequestMethod.GET)
	public String member(Model model) {
		Member member = new Member();
		model.addAttribute("member", member);
		return "admin/member";
	}
	
	@RequestMapping(value = { "/registerMember" }, method = RequestMethod.GET)
	public String showMemberPage(Model model) {
		Member member = new Member();
		model.addAttribute("member", member);
		return "registerMember";
	}

	@RequestMapping(value = { "/registerMember" }, method = RequestMethod.POST)
	public String saveMember(@Valid @ModelAttribute("member") Member member, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "registerMember";
		}
		if (member!=null) {
			Member memberLogin = new Member();
			memberLogin.setLogin(member.getLogin());
			List<Member> members=memberService.findMember(memberLogin);
			if (members.size()==0) {
				memberService.save(member);
				return "login";
			}else {
				model.addAttribute("error","Login name is already existed! Try another name");
				return "registerMember";
			}
		}else {
			return "registerMember";
		}
		
	}

	@RequestMapping(value = { "/admin/searchMember" }, method = RequestMethod.POST)
	public String SearchMember(@ModelAttribute("member") Member member, Model model) {
		List<Member> members = memberService.findMember(member);
		model.addAttribute("members", members);
		return "admin/member";
	}

	@GetMapping("/admin/deleteMember/{id}")
	public ModelAndView deleteMember(@ModelAttribute("id") Integer id) {
		memberService.delete(id);	
		Member mem = new Member();
		ModelAndView mav = new ModelAndView("admin/member");
		List<Member> members = memberService.findMember(mem);
		mav.addObject("members",members);
		mav.addObject("member",mem);
		return mav;
	}
	
	@GetMapping("/admin/updateMember/{id}")
	public ModelAndView showUpdateMemberForm(@ModelAttribute("id") int id) {
		Member member = new Member();
		member.setMemberId(id);
		List<Member> member1 = memberService.findMember(member);
		member.setMemberName(member1.get(0).getMemberName());
		member.setMemberPhone(member1.get(0).getMemberPhone());
		member.setMemberAddress(member1.get(0).getMemberAddress());
		member.setMemberEmail(member1.get(0).getMemberEmail());
		member.setUpdateDate(member1.get(0).getUpdateDate());
		ModelAndView mav = new ModelAndView("admin/updateMember");
		mav.addObject("member", member);
		return mav;
	}

	@RequestMapping(value = "/admin/updateMember", method = RequestMethod.POST)
	public ModelAndView UpdateMember(@ModelAttribute("member") Member member) {
		memberService.update(member);
		ModelAndView mav = new ModelAndView("admin/member");
		Member mem = new Member();
		List<Member> members = memberService.findMember(mem);
		mav.addObject("member",mem);
		mav.addObject("members", members);
		return mav;
	}
}
