package com.tiktop.controller;

import java.util.List;

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

import com.tiktop.model.Classes;
import com.tiktop.model.Schedule;
import com.tiktop.model.Trainer;
import com.tiktop.services.ClassesService;
import com.tiktop.services.ScheduleService;
import com.tiktop.services.TrainerService;

@Controller
public class ScheduleController { 
	
	
	@Autowired
	private TrainerService trainerService;
	
	@Autowired
	private ClassesService classesService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping(value = {"/admin/schedule" }, method = RequestMethod.GET)
	public String schedule(Model model) {
		Schedule schedule = new Schedule();
		List<Classes> classess = classesService.findClasses(new Classes());
		List<Trainer> trainers=trainerService.findTrainer(new Trainer());
		model.addAttribute("classess",classess);
		model.addAttribute("trainers",trainers);
		model.addAttribute("schedule", schedule);
		return "admin/schedule";
	}

	@RequestMapping(value = { "/admin/addSchedule" }, method = RequestMethod.GET)
	public String showSchedulePage(Model model) {
		Schedule schedule = new Schedule();
		List<Classes> classess = classesService.findClasses(new Classes());
		List<Trainer> trainers=trainerService.findTrainer(new Trainer());
		model.addAttribute("classess",classess);
		model.addAttribute("trainers",trainers);
		model.addAttribute("schedule", schedule);
		return "admin/addSchedule";
	}

	@RequestMapping(value = { "/admin/addSchedule" }, method = RequestMethod.POST)
	public String saveSchedule(@Valid @ModelAttribute("schedule") Schedule schedule, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/addSchedule";
		}
		scheduleService.save(schedule);
		Schedule sche = new Schedule();
		List<Schedule> schedules = scheduleService.findSchedule(sche);
		model.addAttribute("schedules", schedules);
		model.addAttribute("schedule",sche);
		model.addAttribute("success","success");
		return "admin/schedule";
	}

	@RequestMapping(value = { "/admin/searchSchedule" }, method = RequestMethod.POST)
	public String SearchSchedule(@ModelAttribute("schedule") Schedule schedule, Model model) {
		List<Schedule> schedules = scheduleService.findSchedule(schedule);
		List<Classes> classess = classesService.findClasses(new Classes());
		List<Trainer> trainers=trainerService.findTrainer(new Trainer());
		model.addAttribute("classess",classess);
		model.addAttribute("trainers",trainers);
		model.addAttribute("schedule",new Schedule());
		model.addAttribute("schedules", schedules);
		return "admin/schedule";
	}

	@GetMapping("/admin/deleteSchedule/{id}")
	public ModelAndView deleteSchedule(@ModelAttribute("id") Integer id) {
		ModelAndView mav = new ModelAndView("admin/schedule");
		Schedule schedule=new Schedule();
		schedule.setTrainerId(0);
		schedule.setClassesId(0);
		List<Schedule> schedules = scheduleService.findSchedule(schedule);
		List<Classes> classess = classesService.findClasses(new Classes());
		List<Trainer> trainers=trainerService.findTrainer(new Trainer());
		mav.addObject("classess",classess);
		mav.addObject("trainers",trainers);
		mav.addObject("schedules",schedules);
		mav.addObject("schedule",new Schedule());
		try {
			scheduleService.delete(id);
			mav.addObject("delete","delete");
			return mav;
		} catch (Exception e) {
			mav.addObject("error","error");
			return mav;
		}
	}
	
	@GetMapping("/admin/updateSchedule/{id}")
	public ModelAndView showUpdateScheduleForm(@ModelAttribute("id") int id) {
		Schedule schedule = new Schedule();
		schedule.setScheduleId(id);
		List<Schedule> schedule1 = scheduleService.findSchedule(schedule);
		schedule = schedule1.get(0);
		ModelAndView mav = new ModelAndView("admin/updateSchedule");
		List<Classes> classess = classesService.findClasses(new Classes());
		List<Trainer> trainers=trainerService.findTrainer(new Trainer());
		mav.addObject("classess",classess);
		mav.addObject("trainers",trainers);
		mav.addObject("schedule", schedule);
		return mav;
	}

	@RequestMapping(value = "/admin/updateSchedule", method = RequestMethod.POST)
	public ModelAndView UpdateSchedule(@ModelAttribute("schedule") Schedule schedule) {
		scheduleService.update(schedule);
		ModelAndView mav = new ModelAndView("admin/schedule");
		Schedule sche = new Schedule();
		List<Schedule> schedules = scheduleService.findSchedule(sche);
		mav.addObject("schedule",new Schedule());
		mav.addObject("schedules", schedules);
		return mav;
	}
}
