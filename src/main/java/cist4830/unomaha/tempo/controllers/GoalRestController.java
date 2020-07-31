package cist4830.unomaha.tempo.controllers;

import cist4830.unomaha.tempo.model.Goal;
import cist4830.unomaha.tempo.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class GoalRestController {

    private final static Logger LOG = Logger.getLogger(GoalController.class.getSimpleName());


    @Autowired
    GoalRepository goalRepository;

    @GetMapping(value = "/api")
    public Collection<Goal> getGoals() {
        return new ArrayList<>(goalRepository.findAll());
    }
}
