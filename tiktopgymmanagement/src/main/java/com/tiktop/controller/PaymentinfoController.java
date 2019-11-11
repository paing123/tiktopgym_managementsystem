package com.tiktop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tiktop.model.Member;
import com.tiktop.model.Paymentinfo;
import com.tiktop.services.MemberService;
import com.tiktop.services.PaymentinfoService;

@Controller
public class PaymentinfoController {
	
	@Autowired
	private PaymentinfoService paymentinfoService;
	
	@Autowired
	private MemberService memberServcie;

	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping(value = {"/admin/paymentinfo" }, method = RequestMethod.GET)
	public String paymentinfo(Model model) {
		Paymentinfo paymentinfo = new Paymentinfo();
		model.addAttribute("paymentinfo", paymentinfo);
		return "admin/paymentinfo";
	}
	
	@RequestMapping(value = {"/member/paymentinfo" }, method = RequestMethod.GET)
	public String memberpaymentinfo(Model model,HttpSession session) {
		Paymentinfo paymentinfo = new Paymentinfo();
		List<Paymentinfo> paymentinfos = paymentinfoService.findPaymentinfo(paymentinfo);
		model.addAttribute("paymentinfos", paymentinfos);
		int bookingId = (int) session.getAttribute("bookingId");
		model.addAttribute("bookingId",bookingId);
		Member admin = new Member();
		admin.setLogin("admin");
		List<Member> members = memberServcie.findMember(admin);
		admin = members.get(0);
		model.addAttribute("admin",admin);
		return "member/memberPaymentinfo";
	}

	@RequestMapping(value = { "/admin/addPaymentinfo" }, method = RequestMethod.GET)
	public String showPaymentinfoPage(Model model) {
		Paymentinfo paymentinfo = new Paymentinfo();
		model.addAttribute("paymentinfo", paymentinfo);
		return "admin/addPaymentinfo";
	}

	@RequestMapping(value = { "/admin/addPaymentinfo" }, method = RequestMethod.POST)
	public String savePaymentinfo(@Valid @ModelAttribute("paymentinfo") Paymentinfo paymentinfo, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/addPaymentinfo";
		}
		paymentinfoService.save(paymentinfo);
		Paymentinfo paymentinfo1 = new Paymentinfo();
		List<Paymentinfo> paymentinfos= paymentinfoService.findPaymentinfo(paymentinfo1);
		model.addAttribute("paymentinfos", paymentinfos);
		model.addAttribute("paymentinfo",paymentinfo1);
		return "admin/paymentinfo";
	}

	@RequestMapping(value = { "/admin/searchPaymentinfo" }, method = RequestMethod.POST)
	public String SearchPaymentinfo(@ModelAttribute("Paymentinfos") Paymentinfo paymentinfo, Model model) {
		List<Paymentinfo> paymentinfos = paymentinfoService.findPaymentinfo(paymentinfo);
		model.addAttribute("paymentinfo",new Paymentinfo());
		model.addAttribute("paymentinfos", paymentinfos);
		return "admin/paymentinfo";
	}

	@GetMapping("/admin/deletePaymentinfo/{id}")
	public ModelAndView deletePaymentinfo(@ModelAttribute("id") Integer id) {
		paymentinfoService.delete(id);	
		Paymentinfo paymentinfo= new Paymentinfo();
		ModelAndView mav = new ModelAndView("admin/paymentinfo");
		List<Paymentinfo> paymentinfos = paymentinfoService.findPaymentinfo(paymentinfo);
		mav.addObject("paymentinfos",paymentinfos);
		mav.addObject("paymentinfo",paymentinfo);
		return mav;
	}
	
	@GetMapping("/admin/updatePaymentinfo/{id}")
	public ModelAndView showUpdatePaymentinfoForm(@ModelAttribute("id") int id) {
		Paymentinfo paymentinfo = new Paymentinfo();
		paymentinfo.setPaymentinfoId(id);
		List<Paymentinfo> paymentinfo1 = paymentinfoService.findPaymentinfo(paymentinfo);
		paymentinfo=paymentinfo1.get(0);
		ModelAndView mav = new ModelAndView("admin/updatePaymentinfo");
		mav.addObject("paymentinfo", paymentinfo);
		return mav;
	}

	@RequestMapping(value = "/admin/updatePaymentinfo", method = RequestMethod.POST)
	public ModelAndView UpdatePaymentinfo(@ModelAttribute("paymentinfo") Paymentinfo paymentinfo) {
		paymentinfoService.update(paymentinfo);
		ModelAndView mav = new ModelAndView("admin/paymentinfo");
		Paymentinfo payment = new Paymentinfo();
		List<Paymentinfo> paymentinfos = paymentinfoService.findPaymentinfo(payment);
		mav.addObject("paymentinfo",payment);
		mav.addObject("paymentinfos", paymentinfos);
		return mav;
	}
	
}
