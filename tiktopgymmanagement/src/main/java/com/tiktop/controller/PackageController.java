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

import com.tiktop.model.Package;
import com.tiktop.services.PackageService;

@Controller
public class PackageController {
	
	@Autowired
	private PackageService packageService;

	@RequestMapping(value = {"/admin/package" }, method = RequestMethod.GET)
	public String pack(Model model) {
		Package pack = new Package();
		model.addAttribute("pack", pack);
		return "admin/package";
	}

	@RequestMapping(value = { "/admin/addPackage" }, method = RequestMethod.GET)
	public String showPackagePage(Model model) {
		Package pack = new Package();
		model.addAttribute("pack", pack);
		return "admin/addPackage";
	}

	@RequestMapping(value = { "/admin/addPackage" }, method = RequestMethod.POST)
	public String savePackage(@Valid @ModelAttribute("pack") Package pack, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "admin/addPackage";
		}
		packageService.save(pack);
		Package pk = new Package();
		List<Package> packs = packageService.findPackage(pk);
		model.addAttribute("packs", packs);
		model.addAttribute("pack",pk);
		return "admin/package";
	}

	@RequestMapping(value = { "/admin/searchPackage" }, method = RequestMethod.POST)
	public String SearchPackage(@ModelAttribute("pack") Package pack, Model model) {
		List<Package> packs = packageService.findPackage(pack);
		model.addAttribute("packs", packs);
		return "admin/package";
	}

	@GetMapping("/admin/deletePackage/{id}")
	public ModelAndView deletePackage(@ModelAttribute("id") Integer id) {
		packageService.delete(id);	
		Package pack = new Package();
		ModelAndView mav = new ModelAndView("admin/package");
		List<Package> packs = packageService.findPackage(pack);
		mav.addObject("packs",packs);
		mav.addObject("pack",pack);
		return mav;
	}
	
	@GetMapping("/admin/updatePackage/{id}")
	public ModelAndView showUpdatePackageForm(@ModelAttribute("id") int id) {
		Package pack = new Package();
		pack.setPackageId(id);
		List<Package> pack1 = packageService.findPackage(pack);
		pack.setPackageName(pack1.get(0).getPackageName());
		pack.setPackageDuration(pack1.get(0).getPackageDuration());
		pack.setPackageFees(pack1.get(0).getPackageFees());
		ModelAndView mav = new ModelAndView("admin/updatePackage");
		mav.addObject("pack", pack);
		return mav;
	}

	@RequestMapping(value = "/admin/updatePackage", method = RequestMethod.POST)
	public ModelAndView UpdatePackage(@ModelAttribute("pack") Package pack) {
		packageService.update(pack);
		ModelAndView mav = new ModelAndView("admin/package");
		Package pk = new Package();
		List<Package> packs = packageService.findPackage(pk);
		mav.addObject("packs", packs);
		mav.addObject("pack",pk);
		return mav;
	}
}
