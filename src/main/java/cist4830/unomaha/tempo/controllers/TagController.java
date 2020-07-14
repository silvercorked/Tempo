package cist4830.unomaha.tempo.controllers;

import java.util.Optional;

import cist4830.unomaha.tempo.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cist4830.unomaha.tempo.repository.GoalRepository;
import cist4830.unomaha.tempo.repository.TagRepository;
import cist4830.unomaha.tempo.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "tags")
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
        Optional<Tag> possibleTag = tagRepository.findById((long) 1);
        Tag tag = possibleTag.orElseGet(() -> {
           Tag tempTag = new Tag((long) 2, "Test Tag", "Designed to test initial tags",
                   (long) 1, "2020-10-10 12:12:12", "2020-10-10 12:12:12" );
           return tagRepository.create(tempTag);
        });
        model.addAttribute("tags", tagRepository.findAll());
        return "tags/index";
    }

    @GetMapping(value = "/create")
    public String create() { return "goals/add"; }



}
