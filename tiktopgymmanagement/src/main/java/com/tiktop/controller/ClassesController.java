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
import com.tiktop.services.ClassesService;

@Controller
public class ClassesController {
	
	@Autowired
	private ClassesService classesService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping(value = {"/admin/classes" }, method = RequestMethod.GET)
	public String classes(Model model) {
		Classes classes = new Classes();
		model.addAttribute("classes", classes);
		return "admin/classes";
	}

	@RequestMapping(value = { "/admin/addClasses" }, method = RequestMethod.GET)
	public String showClassesPage(Model model) {
		Classes classes = new Classes();
		model.addAttribute("classes", classes);
		return "admin/addClasses";
	}

	@RequestMapping(value = { "/admin/addClasses" }, method = RequestMethod.POST)
	public String saveClasses(@Valid @ModelAttribute("classes") Classes classes, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/addClasses";
		}
		classesService.save(classes);
		Classes classes1 = new Classes();
		List<Classes> classess = classesService.findClasses(classes1);
		model.addAttribute("classess", classess);
		model.addAttribute("classes",classes1);
		model.addAttribute("success","success");
		return "admin/classes";
	}

	@RequestMapping(value = { "/admin/searchClasses" }, method = RequestMethod.POST)
	public String SearchClasses(@ModelAttribute("classes") Classes classes, Model model) {
		List<Classes> classess = classesService.findClasses(classes);
		model.addAttribute("classes",new Classes());
		model.addAttribute("classess", classess);
		return "admin/classes";
	}

	@GetMapping("/admin/deleteClasses/{id}")
	public ModelAndView deleteClasses(@ModelAttribute("id") Integer id) {
		Classes classes= new Classes();
		ModelAndView mav = new ModelAndView("admin/classes");
		List<Classes> classess = classesService.findClasses(classes);
		mav.addObject("classess",classess);
		mav.addObject("classes",classes);
		try {
			classesService.delete(id);
			return mav;
		} catch (Exception e) {
			mav.addObject("error","error");
			return mav;
		}
	}
	
	@GetMapping("/admin/updateClasses/{id}")
	public ModelAndView showUpdateClassesForm(@ModelAttribute("id") int id) {
		Classes classes = new Classes();
		classes.setClassesId(id);
		List<Classes> classes1 = classesService.findClasses(classes);
		classes.setClassesName(classes1.get(0).getClassesName());
		ModelAndView mav = new ModelAndView("admin/updateClasses");
		mav.addObject("classes", classes);
		return mav;
	}

	@RequestMapping(value = "/admin/updateClasses", method = RequestMethod.POST)
	public ModelAndView UpdateClasses(@ModelAttribute("classes") Classes classes) {
		classesService.update(classes);
		ModelAndView mav = new ModelAndView("admin/classes");
		List<Classes> classess = classesService.findClasses(new Classes());
		mav.addObject("classes",new Classes());
		mav.addObject("classess", classess);
		return mav;
	}
	
}
