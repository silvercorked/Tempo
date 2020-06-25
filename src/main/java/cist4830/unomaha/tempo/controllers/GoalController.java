package cist4830.unomaha.tempo.controllers;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cist4830.unomaha.tempo.model.Goal;
import cist4830.unomaha.tempo.repository.GoalRepository;
import cist4830.unomaha.tempo.repository.TagRepository;
import cist4830.unomaha.tempo.repository.UserRepository;

@Controller
@RequestMapping(value = "/goals")
public class GoalController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private GoalRepository goalRepository;

	@GetMapping
	public String index(Model model) {
		System.out.println("attempted to hit goals page");
		Optional<Goal> possibleGoal = goalRepository.findGoalById((long) 1);
		Goal goal = possibleGoal.orElseGet(() -> {
			Goal goalTemp = new Goal((long) 2, null, "Write a spring boot program", "Spring boot... ", (long) 10,
					(long) 100, (long) 1, "2019-10-20 15:59:20", "2019-10-20 15:59:20");
			return goalRepository.create(goalTemp);
		});
		model.addAttribute("goals", goalRepository.findAll());
		return "goals/index";
	}

	@GetMapping(value = "/create")
	public String create() {
		return "goals/add";
	}

	@PostMapping()
	public String store(@RequestParam(name = "goal") String goalstr
		, @RequestParam(name = "description") String description
		, @RequestParam(name = "progress") Long progress
		, @RequestParam(name = "target") Long target) {
		java.util.Date utilDate = new java.util.Date();
		String now = new Date(utilDate.getTime()).toString();
		Goal goal = new Goal((long) 0, null, goalstr, description, progress, target, (long) 1, now, now);
		goalRepository.create(goal);
		return "redirect:/goals";
	}
}