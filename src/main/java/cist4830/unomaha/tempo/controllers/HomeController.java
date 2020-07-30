package cist4830.unomaha.tempo.controllers;

import cist4830.unomaha.tempo.model.User;
import cist4830.unomaha.tempo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @GetMapping()
    public String index() {
        return "home";
    }

    @GetMapping(value = "login")
    public String login() {
        return "login";
    }
}