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
import org.springframework.web.bind.annotation.PathVariable;

import cist4830.unomaha.tempo.model.Goal;
import cist4830.unomaha.tempo.repository.GoalRepository;
import cist4830.unomaha.tempo.repository.TagRepository;
import cist4830.unomaha.tempo.repository.UserRepository;

import cist4830.unomaha.tempo.controllers.errors.*;

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
	
	@GetMapping(value = "{id}/edit")
	public String edit(Model model, @PathVariable Long id) {
		Goal goal = goalRepository.findGoalById(id).orElseThrow(() -> { throw new ResourceNotFoundException(); });
		model.addAttribute("goal", goal);
		return "goals/edit";
	}

	@PostMapping(value = "{id}")
	public String update(@PathVariable Long id, @RequestParam(name = "goal") String goalstr
		, @RequestParam(name = "description") String description
		, @RequestParam(name = "progress") Long progress
		, @RequestParam(name = "target") Long target) {
		java.util.Date utilDate = new java.util.Date();
		String now = new Date(utilDate.getTime()).toString();
		Goal goal = goalRepository.findGoalById(id).orElseThrow(() -> { throw new ResourceNotFoundException(); });
		goal.setGoal(goalstr); goal.setDescription(description);
		goal.setProgress(progress); goal.setTarget(target); goal.setModifiedAt(now);
		goalRepository.update(goal);
		return "redirect:/goals/" + id;
	}

	@GetMapping(value = "{id}")
	public String show(Model model, @PathVariable Long id) {
		Goal goal = goalRepository.findGoalById(id).orElseThrow(() -> { throw new ResourceNotFoundException(); });
		model.addAttribute("goal", goal);
		return "goals/show";
	}

	@PostMapping(value = "{id}/delete")
	public String delete(@PathVariable Long id) {
		Goal goal = goalRepository.findGoalById(id).orElseThrow(() -> { throw new ResourceNotFoundException(); });
		// ^^ check if it exists first
		goalRepository.delete(id);
		return "redirect:/goals";
	}
}