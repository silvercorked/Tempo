package cist4830.unomaha.tempo.controllers;

import cist4830.unomaha.tempo.controllers.errors.ResourceNotFoundException;
import cist4830.unomaha.tempo.model.Goal;
import cist4830.unomaha.tempo.model.Tag;
import cist4830.unomaha.tempo.repository.GoalRepository;
import cist4830.unomaha.tempo.repository.TagRepository;
import cist4830.unomaha.tempo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/")
public class GoalController {

    private final static Logger LOG = Logger.getLogger(GoalController.class.getSimpleName());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private GoalRepository goalRepository;

    @GetMapping
    public String index(Model model) {
        LOG.info("Tempo index page requested");
        model.addAttribute("goals", goalRepository.findAll());
        return "goals/index";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        model.addAttribute("tags", tagRepository.findAll());
        return "goals/create";
    }

    @PostMapping()
    public String store(@RequestParam(name = "goal") String goalstr
            , @RequestParam(name = "description") String description
            , @RequestParam(name = "progress") Long progress
            , @RequestParam(name = "target") Long target
            , @RequestParam(name = "due_date") Date due_date
            , @RequestParam(name = "tags") Optional<List<Long>> tag_ids) {
        java.util.Date utilDate = new java.util.Date();
        String now = new Date(utilDate.getTime()).toString();
        List<Tag> tags = ((List<Long>) tag_ids.orElse(new ArrayList())).stream()
                .map(id -> tagRepository.findTagById(id).orElseThrow(() -> {
                    throw new ResourceNotFoundException();
                }))
                .collect(Collectors.toList()); // do this to make sure these tags exists first before creating goal.
        Goal goal = new Goal((long) 0, null, goalstr, description, progress, target, due_date.toString(), (long) 1, now, now);
        goalRepository.create(goal);
        tags.stream().forEach(tag -> goalRepository.associateTag(goal, tag));
        return "redirect:/";
    }

    @GetMapping(value = "{id}/edit")
    public String edit(Model model, @PathVariable Long id) {
        Goal goal = goalRepository.findGoalById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        model.addAttribute("goal", goal);
        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute("selectedTags", goalRepository.getTags(goal));
        return "goals/edit";
    }

    @PostMapping(value = "{id}")
    public String update(@PathVariable Long id, @RequestParam(name = "goal") String goalstr
            , @RequestParam(name = "description") String description
            , @RequestParam(name = "progress") Long progress
            , @RequestParam(name = "target") Long target
            , @RequestParam(name = "due_date") Date due_date
            , @RequestParam(name = "tags") Optional<List<Long>> tag_ids) {
        java.util.Date utilDate = new java.util.Date();
        String now = new Date(utilDate.getTime()).toString();
        List<Tag> tags = ((List<Long>) tag_ids.orElse(new ArrayList())).stream()
                .map(tag_id -> tagRepository.findTagById(tag_id).orElseThrow(() -> {
                    throw new ResourceNotFoundException();
                }))
                .collect(Collectors.toList()); // do this to make sure these tags exists first before creating goal.
        Goal goal = goalRepository.findGoalById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        goal.setGoal(goalstr);
        goal.setDescription(description);
        goal.setProgress(progress);
        goal.setTarget(target);
        goal.setDueDate(due_date.toString());
        goal.setModifiedAt(now);
        goalRepository.update(goal);
        List<Tag> assocTags = goalRepository.getTags(goal);
        System.out.println(tags.toString());
        System.out.println(assocTags.toString());
        tags.stream() // take the new set of tags and if they aren't in the currently selected, we associate them
                .forEach(item -> {
                    if (assocTags.stream().allMatch(aItem -> item.getId() != aItem.getId()))
                        goalRepository.associateTag(goal, item);
                });
        assocTags.stream() // take associated tags and if they arent in the new set of tags, we disassociate them
                .forEach(aItem -> {
                    if (tags.stream().allMatch(item -> item.getId() != aItem.getId()))
                        goalRepository.disassociateTag(goal, aItem);
                });
        return "redirect:/" + id;
    }

    @GetMapping(value = "{id}")
    public String show(Model model, @PathVariable Long id) {
        Goal goal = goalRepository.findGoalById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        List<Tag> tags = goalRepository.getTags(goal);
        model.addAttribute("goal", goal);
        model.addAttribute("tags", tags);
        return "goals/show";
    }

    @PostMapping(value = "{id}/delete")
    public String delete(@PathVariable Long id) {
        Goal goal = goalRepository.findGoalById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        // ^^ check if it exists first
        goalRepository.delete(id);
        return "redirect:/";
    }
}