package com.tiktop.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tiktop.model.Attendance;
import com.tiktop.model.Member;
import com.tiktop.services.AttendanceService;
import com.tiktop.services.MemberService;

@Controller
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@Autowired
	private MemberService memberService;

	// convert empty string to null when form is submit
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping(value = { "/admin/attendance" }, method = RequestMethod.GET)
	public String attendance(Model model) {
		Attendance attendance = new Attendance();
		List<Attendance> attendanceMemberIds = attendanceService.findAttendanceMemberId(attendance);
		model.addAttribute("attendanceMemberIds", attendanceMemberIds);
		model.addAttribute("attendance", attendance);
		return "admin/attendance";
	}

	@RequestMapping(value = { "/member/attendance" }, method = RequestMethod.GET)
	public ModelAndView memberAttendance(Model model, HttpSession session) {
		String login = (String) session.getAttribute("currentUser");
		Member member = memberService.findMemberByLogin(login);
		Attendance attendance = new Attendance();
		attendance.setMemberId(member.getMemberId());
		List<Attendance> attendances = attendanceService.findAttendance(attendance);
		ModelAndView mav = new ModelAndView("member/memberAttendance");
		mav.addObject("attendances", attendances);
		return mav;
	}

	@RequestMapping(value = { "/member/bodycheck" }, method = RequestMethod.GET)
	public String checkbody(Model model) {
		Attendance attendance = new Attendance();
		model.addAttribute("attendance", attendance);
		return "member/memberbodycheck";
	}

	@RequestMapping(value = { "/member/bodycheck" }, method = RequestMethod.POST)
	public ModelAndView checkBodyMeasurement(@ModelAttribute("attendance") Attendance attendance, HttpSession session)
			throws ParseException {
		String login = (String) session.getAttribute("currentUser");
		Member member = memberService.findMemberByLogin(login);
		ModelAndView mav = new ModelAndView("member/memberbodycheck");
		if (attendance.startDate == null | attendance.endDate == null) {
			mav.addObject("nulldate", "nulldate");
			return mav;
		}
		if (attendanceService.checkDate(attendance)) {
			attendance.setMemberId(member.getMemberId());
			try {
				List<Attendance> attendances = attendanceService.checkAttendance(attendance);
				attendance = attendanceService.bodyMeasurementCheck(attendances);
				mav = attendanceService.checkLossGain(attendance);
				return mav;
			} catch (Exception e) {
				mav.addObject("NoRecord","NoRecord");
				return mav;
			}
		}
		mav.addObject("invalidDate", "invalidDate");
		return mav;

	}

	@RequestMapping(value = { "/admin/addAttendance" }, method = RequestMethod.GET)
	public String showMemberAttendance(Model model) {
		Attendance attendance = new Attendance();
		List<Member> members = memberService.findMember(new Member());
		model.addAttribute("members", members);
		model.addAttribute("attendance", attendance);
		return "admin/addAttendance";
	}

	@RequestMapping(value = { "/admin/addAttendance" }, method = RequestMethod.POST)
	public String saveAttendance(@Valid @ModelAttribute("attendance") Attendance attendance,
			BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/addAttendance";
		}
		attendanceService.save(attendance);
		Attendance attendance1 = new Attendance();
		List<Attendance> attendances = attendanceService.findAttendance(attendance1);
		model.addAttribute("attendances", attendances);
		model.addAttribute("attendance", attendance1);
		return "admin/attendance";
	}

	@RequestMapping(value = { "/admin/searchAttendance" }, method = RequestMethod.POST)
	public String searchAttendance(@ModelAttribute("attendance") Attendance attendance, Model model) {
		List<Attendance> attendanceMemberIds = attendanceService.findAttendanceMemberId(attendance);
		List<Attendance> attendances = attendanceService.findAttendance(attendance);
		model.addAttribute("attendance", new Attendance());
		model.addAttribute("attendanceMemberIds", attendanceMemberIds);
		model.addAttribute("attendances", attendances);
		return "admin/attendance";
	}

	@GetMapping("/admin/deleteAttendance/{id}")
	public ModelAndView deleteAttendance(@ModelAttribute("id") Integer id) {
		attendanceService.delete(id);
		Attendance attendance = new Attendance();
		ModelAndView mav = new ModelAndView("admin/attendance");
		List<Attendance> attendances = attendanceService.findAttendance(attendance);
		mav.addObject("attendances", attendances);
		mav.addObject("attendance", attendance);
		return mav;
	}

	@GetMapping("/admin/updateAttendance/{id}")
	public ModelAndView showUpdateAttendanceForm(@ModelAttribute("id") int id) {
		Attendance attendance = new Attendance();
		attendance.setAttendanceId(id);
		List<Attendance> attentance1 = attendanceService.findAttendance(attendance);
		attendance = attentance1.get(0);
		List<Member> members = memberService.findMember(new Member());
		ModelAndView mav = new ModelAndView("admin/updateAttendance");
		mav.addObject("attendance", attendance);
		mav.addObject("members", members);
		return mav;
	}

	@RequestMapping(value = "/admin/updateAttendance", method = RequestMethod.POST)
	public ModelAndView updateAttendance(@ModelAttribute("attendance") Attendance attendance) {
		attendanceService.update(attendance);
		Attendance newAttendance = new Attendance();
		ModelAndView mav = new ModelAndView("admin/attendance");
		List<Attendance> attendances = attendanceService.findAttendance(newAttendance);
		mav.addObject("attendance", newAttendance);
		mav.addObject("attendances", attendances);
		return mav;
	}

}
