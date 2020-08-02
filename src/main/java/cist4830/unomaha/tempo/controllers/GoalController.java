package cist4830.unomaha.tempo.controllers;

import cist4830.unomaha.tempo.controllers.errors.ResourceNotFoundException;
import cist4830.unomaha.tempo.model.Goal;
import cist4830.unomaha.tempo.model.Tag;
import cist4830.unomaha.tempo.model.User;
import cist4830.unomaha.tempo.repository.GoalRepository;
import cist4830.unomaha.tempo.repository.TagRepository;
import cist4830.unomaha.tempo.repository.UserRepository;
import cist4830.unomaha.tempo.services.CustomUserDetailsService;
import cist4830.unomaha.tempo.controllers.utility.GetLoggedInUser;
import cist4830.unomaha.tempo.recurrence.RecurrenceCalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/goals")
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
        User user = GetLoggedInUser.getLoggedInUser();
        List<Goal> goals = goalRepository.findAllByUserId(user.getId());
        model.addAttribute("goals", goals);
        return "goals/index";
    }

    @GetMapping(value = "/create")
    public String create(Model model) {
        User user = GetLoggedInUser.getLoggedInUser();
        List<Goal> goals = goalRepository.findAllByUserId(user.getId());
        model.addAttribute("goals", goals); // only user's goals
        model.addAttribute("tags", tagRepository.findAll()); // all tags from every user
        return "goals/create";
    }

    @PostMapping()
    public String store(@RequestParam(name = "goal") String goalstr
            , @RequestParam(name = "description", required = true) String description
            , @RequestParam(name = "progress", required = true) Long progress
            , @RequestParam(name = "target", required = true) Long target
            , @RequestParam(name = "due_date", required = false) Date due_date
            , @RequestParam(name = "recurrence_num", required = false) Integer recurrence_num
            , @RequestParam(name = "recurrence_freq", required = false) String recurrence_freq
            , @RequestParam(name = "tags") Optional<List<Long>> tag_ids) {
        User user = GetLoggedInUser.getLoggedInUser();
        java.util.Date utilDate = new java.util.Date();
        String now = new Date(utilDate.getTime()).toString();
        List<Tag> tags = ((List<Long>) tag_ids.orElse(new ArrayList())).stream()
                .map(id -> tagRepository.findTagById(id).orElseThrow(() -> {
                    throw new ResourceNotFoundException();
                }))
                .collect(Collectors.toList()); // do this to make sure these tags exists first before creating goal.
        String recur_date_str = null;
        if (recurrence_num == null || recurrence_freq == null) {
            recurrence_num = 0;
            recurrence_freq = "NEVER";
        }
        else
            recur_date_str = RecurrenceCalculator.getRecurrenceDate(now.toString(), recurrence_num, recurrence_freq);
        Goal goal = new Goal((long) 0, null, goalstr, description, progress, target, due_date != null ? due_date.toString() : null
                , recurrence_num, recurrence_freq, recur_date_str, user.getId(), now, now);
        LOG.info("Creating new goal with name: " + goalstr + " description: " + description
        + " recurring every " + recurrence_num + " " + recurrence_freq);
        goalRepository.create(goal);
        tags.stream().forEach(tag -> goalRepository.associateTag(goal, tag));
        return "redirect:/goals";
    }

    @GetMapping(value = "{id}/edit")
    public String edit(Model model, @PathVariable Long id) {
        User user = GetLoggedInUser.getLoggedInUser();
        List<Goal> goals = goalRepository.findAllByUserId(user.getId());
        Goal goal = goalRepository.findGoalByIdAndUserId(id, user.getId()).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        model.addAttribute("goals", goal);
        model.addAttribute("goal", goal);
        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute("selectedTags", goalRepository.getTags(goal));
        return "goals/edit";
    }

    @PostMapping(value = "{id}")
    public String update(@PathVariable Long id, @RequestParam(name = "goal") String goalstr
        , @RequestParam(name = "description", required = true) String description
        , @RequestParam(name = "progress", required = true) Long progress
        , @RequestParam(name = "target", required = true) Long target
        , @RequestParam(name = "due_date", required = false) Date due_date
        , @RequestParam(name = "recurrence_num", required = false) Integer recurrence_num
        , @RequestParam(name = "recurrence_freq", required = false) String recurrence_freq
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
        String recur_date_str = null;
        if (recurrence_num == null || recurrence_freq == null) {
            recurrence_num = 0;
            recurrence_freq = "NEVER";
        }
        else
            recur_date_str = RecurrenceCalculator.getRecurrenceDate(now.toString(), recurrence_num, recurrence_freq);
        goal.setGoal(goalstr);
        goal.setDescription(description);
        goal.setProgress(progress);
        goal.setTarget(target);
        goal.setDueDate(due_date != null ? due_date.toString() : null);
        goal.setRecurrenceNum(recurrence_num);
        goal.setRecurrenceFreq(recurrence_freq);
        goal.setRecurrenceDate(recur_date_str);
        goal.setModifiedAt(now);
        goalRepository.update(goal);
        List<Tag> assocTags = goalRepository.getTags(goal);
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
        return "redirect:/goals/" + id;
    }

    @GetMapping(value = "{id}")
    public String show(Model model, @PathVariable Long id) {
        User user = GetLoggedInUser.getLoggedInUser();
        Goal goal = goalRepository.findGoalByIdAndUserId(id, user.getId()).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        List<Tag> tags = goalRepository.getTags(goal);
        model.addAttribute("goal", goal);
        model.addAttribute("tags", tags);
        return "goals/show";
    }

    @PostMapping(value = "{id}/delete")
    public String delete(@PathVariable Long id) {
        User user = GetLoggedInUser.getLoggedInUser();
        Goal goal = goalRepository.findGoalByIdAndUserId(id, user.getId()).orElseThrow(() -> {
            throw new ResourceNotFoundException();
        });
        // ^^ check if it exists first
        if (user.getId().equals(goal.getUserId())) {
            goalRepository.getTags(goal).stream().forEach((tag) -> goalRepository.disassociateTag(goal, tag));
        }
        goalRepository.delete(id);
        return "redirect:/goals/";
    }
}