package cist4830.unomaha.tempo.controllers;

import cist4830.unomaha.tempo.controllers.errors.ResourceNotFoundException;
import cist4830.unomaha.tempo.model.Goal;
import cist4830.unomaha.tempo.model.Tag;
import cist4830.unomaha.tempo.repository.GoalRepository;
import cist4830.unomaha.tempo.repository.TagRepository;
import cist4830.unomaha.tempo.repository.UserRepository;
import cist4830.unomaha.tempo.controllers.utility.GetLoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import cist4830.unomaha.tempo.model.User;

import org.springframework.security.core.context.SecurityContextHolder;
import cist4830.unomaha.tempo.services.CustomUserPrincipal;

import java.sql.Date;
import java.util.stream.Collectors;

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
        Tag tag = new Tag((long) 0, tagstr, description, GetLoggedInUser.getLoggedInUser().getId(), now, now);
        tagRepository.create(tag);
        return "redirect:/tags";
    }

    @GetMapping(value = "{id}/edit")
    public String edit(Model model, @PathVariable Long id) {
        Tag tag = tagRepository.findTagById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        model.addAttribute("tag", tag);
        return "tags/edit";
    }

    @PostMapping(value = "{id}")
    public String update(@PathVariable Long id, @RequestParam(name = "tag") String tagstr
            , @RequestParam(name = "description") String description) {
        java.util.Date utilDate = new java.util.Date();
        String now = new Date(utilDate.getTime()).toString();
        Tag tag = tagRepository.findTagById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        tag.setTag(tagstr);
        tag.setDescription(description);
        tag.setModifiedAt(now);
        tagRepository.update(tag);
        return "redirect:/tags/" + id;
    }

    @GetMapping(value = "{id}")
    public String show(Model model, @PathVariable Long id) {
        Tag tag = tagRepository.findTagById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        User user = GetLoggedInUser.getLoggedInUser();
        model.addAttribute("tag", tag);
        model.addAttribute("goals", tagRepository.getGoals(tag).stream()
            .filter((goal) -> goal.getUserId().equals(user.getId()))
            .collect(Collectors.toList()));
        return "tags/show";
    }

    @PostMapping(value = "{id}/delete")
    public String delete(@PathVariable Long id) {
        Tag tag = tagRepository.findTagById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        // ^^ check if it exists first
        // check if user owns
        User user = GetLoggedInUser.getLoggedInUser();
        if (user.getId() == tag.getUserId())
            tagRepository.delete(id);
        return "redirect:/tags";
    }
}
