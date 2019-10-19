package com.tiktop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiktop.model.Member;

@Controller
public class LoginController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String index(Model model,HttpSession session) {
		String login = (String) session.getAttribute("currentUser");
		model.addAttribute("login",login);
		return "index";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("member", new Member());
		return "login";
	}
}
