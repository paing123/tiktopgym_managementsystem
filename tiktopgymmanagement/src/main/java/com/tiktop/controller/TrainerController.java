package com.tiktop.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tiktop.model.Trainer;
import com.tiktop.services.TrainerService;

@Controller
public class TrainerController {

	@Autowired
	private TrainerService trainerService;

	@RequestMapping(value = { "/admin/trainer" }, method = RequestMethod.GET)
	public String trainer(Model model) {
		Trainer trainer = new Trainer();
		model.addAttribute("trainer", trainer);
		return "admin/trainer";
	}

	@RequestMapping(value = { "/admin/addTrainer" }, method = RequestMethod.GET)
	public String showTrainerPage(Model model) {
		Trainer trainer = new Trainer();
		model.addAttribute("trainer", trainer);
		return "admin/addTrainer";
	}

	@RequestMapping(value = { "/admin/addTrainer" }, method = RequestMethod.POST)
	public String saveTrainer(@ModelAttribute("trainer") Trainer trainer,Model model) {
		MultipartFile file = trainer.getTrainerImage();
		trainerService.store(file);
		trainer.setTrainerImageName(file.getOriginalFilename());
		trainerService.save(trainer);
		Trainer train = new Trainer();
		List<Trainer> trainers = trainerService.findTrainer(train);
		model.addAttribute("trainers", trainers);
		model.addAttribute("trainer", train);
		return "admin/trainer";
	}

	@RequestMapping(value = { "/admin/searchTrainer" }, method = RequestMethod.POST)
	public String SearchTrainer(@ModelAttribute("trainer") Trainer trainer, Model model) {
		List<Trainer> trainers = trainerService.findTrainer(trainer);
		model.addAttribute("trainer", new Trainer());
		model.addAttribute("trainers", trainers);
		return "admin/trainer";
	}
	
	@RequestMapping(value = { "/admin/searchTrainer" }, method = RequestMethod.GET)
	public String SearchAllTrainer(Model model) {
		Trainer trainer = new Trainer();
		List<Trainer> trainers = trainerService.findTrainer(trainer);
		model.addAttribute("trainer", trainer);
		model.addAttribute("trainers", trainers);
		return "admin/trainer";
	}

	@GetMapping("/admin/deleteTrainer/{id}")
	public ModelAndView deleteTrainer(@ModelAttribute("id") Integer id) throws Exception {
		Trainer trainer = new Trainer();
		trainer.setTrainerId(id);
		List<Trainer> trainers = trainerService.findTrainer(trainer);
		Path imagePath = Paths.get("src/main/resources/static/trainer-image/" + trainers.get(0).getTrainerImageName());
		try {
			Files.delete(imagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		trainerService.delete(id);
		Trainer train = new Trainer();
		ModelAndView mav = new ModelAndView("admin/trainer");
		trainers = trainerService.findTrainer(train);
		mav.addObject("trainers", trainers);
		mav.addObject("trainer", train);
		return mav;
	}

	@GetMapping("/admin/updateTrainer/{id}")
	public ModelAndView showUpdateTrainerForm(@ModelAttribute("id") int id) {
		Trainer trainer = new Trainer();
		trainer.setTrainerId(id);
		List<Trainer> trainers = trainerService.findTrainer(trainer);
		trainer = trainers.get(0);
		ModelAndView mav = new ModelAndView("admin/updateTrainer");
		mav.addObject("trainer", trainer);
		return mav;
	}
	
	@GetMapping("/admin/detailTrainer/{id}")
	public ModelAndView showDetailTrainerForm(@ModelAttribute("id") int id) {
		Trainer trainer = new Trainer();
		trainer.setTrainerId(id);
		List<Trainer> trainers = trainerService.findTrainer(trainer);
		trainer = trainers.get(0);
		ModelAndView mav = new ModelAndView("admin/detailTrainer");
		mav.addObject("trainer", trainer);
		return mav;
	}

	@RequestMapping(value = "/admin/updateTrainer", method = RequestMethod.POST)
	public ModelAndView UpdateTrainer(@ModelAttribute("trainer") Trainer trainer) {
		trainerService.update(trainer);
		ModelAndView mav = new ModelAndView("admin/trainer");
		Trainer train = new Trainer();
		List<Trainer> trainers = trainerService.findTrainer(train);
		mav.addObject("trainer", new Trainer());
		mav.addObject("trainers", trainers);
		return mav;
	}
}
