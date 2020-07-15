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

import cist4830.unomaha.tempo.model.Tag;
import cist4830.unomaha.tempo.repository.GoalRepository;
import cist4830.unomaha.tempo.repository.TagRepository;
import cist4830.unomaha.tempo.repository.UserRepository;

import cist4830.unomaha.tempo.controllers.errors.*;

@Controller
@RequestMapping(value = "/tags")
public class TagController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private GoalRepository goalRepository;

	@GetMapping
	public String index(Model model) {
		System.out.println("attempted to hit tags page");
		model.addAttribute("tags", tagRepository.findAll());
		return "tags/index";
	}

	@GetMapping(value = "/create")
	public String create() {
		return "tags/create";
	}

	@PostMapping()
	public String store(@RequestParam(name = "tag") String tagstr
		, @RequestParam(name = "description") String description) {
		java.util.Date utilDate = new java.util.Date();
		String now = new Date(utilDate.getTime()).toString();
		Tag tag = new Tag((long) 0, tagstr, description, (long) 1, now, now);
		tagRepository.create(tag);
		return "redirect:/tags";
	}
	
	@GetMapping(value = "{id}/edit")
	public String edit(Model model, @PathVariable Long id) {
		Tag tag = tagRepository.findTagById(id).orElseThrow(() -> { throw new ResourceNotFoundException(); });
		model.addAttribute("tag", tag);
		return "tags/edit";
	}

	@PostMapping(value = "{id}")
	public String update(@PathVariable Long id, @RequestParam(name = "tag") String tagstr
		, @RequestParam(name = "description") String description) {
		java.util.Date utilDate = new java.util.Date();
		String now = new Date(utilDate.getTime()).toString();
		Tag tag = tagRepository.findTagById(id).orElseThrow(() -> { throw new ResourceNotFoundException(); });
		tag.setTag(tagstr); tag.setDescription(description); tag.setModifiedAt(now);
		tagRepository.update(tag);
		return "redirect:/tags/" + id;
	}

	@GetMapping(value = "{id}")
	public String show(Model model, @PathVariable Long id) {
		Tag tag = tagRepository.findTagById(id).orElseThrow(() -> { throw new ResourceNotFoundException(); });
		model.addAttribute("tag", tag);
		model.addAttribute("goals", tagRepository.getGoals(tag));
		return "tags/show";
	}

	@PostMapping(value = "{id}/delete")
	public String delete(@PathVariable Long id) {
		Tag tag = tagRepository.findTagById(id).orElseThrow(() -> { throw new ResourceNotFoundException(); });
		// ^^ check if it exists first
		tagRepository.delete(id);
		return "redirect:/tags";
	}
}
