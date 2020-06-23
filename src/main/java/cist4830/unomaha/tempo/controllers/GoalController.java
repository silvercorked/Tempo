package cist4830.unomaha.tempo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cist4830.unomaha.tempo.model.Goal;
import cist4830.unomaha.tempo.repository.GoalRepository;
import cist4830.unomaha.tempo.repository.TagRepository;
import cist4830.unomaha.tempo.repository.UserRepository;

@RestController
@RequestMapping(value = "/goals")
public class GoalController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private GoalRepository goalRepository;

	@GetMapping
	public String index() {
		System.out.println("attempted to hit goals page");
		Optional<Goal> possibleGoal = goalRepository.findGoalById((long) 1);
		Goal goal = possibleGoal.orElseGet(() -> {
			Goal goalTemp = new Goal((long) 2, null, "Write a spring boot program",
				"Spring boot... ", (long) 10, (long) 100, userRepository.findUserById((long) 1).get(),
				"2019-10-20 15:59:20", "2019-10-20 15:59:20");
			return goalRepository.create(goalTemp);
		});
		return "hit home controller's index page: " + goal.toString();
	}
}