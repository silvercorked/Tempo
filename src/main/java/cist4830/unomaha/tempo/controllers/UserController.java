package cist4830.unomaha.tempo.controllers;

import cist4830.unomaha.tempo.model.User;
import cist4830.unomaha.tempo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.sql.Date;

@Controller
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String index() {
        System.out.println("attempted to hit home page");
        List<String> names = Arrays.asList("Bill Billington", "Kathy Geraud", "Opel Rocker");
        List<String> usernames = Arrays.asList("bigBill", "KathG", "Opal");
        List<String> passwords = Arrays.asList("billy123", "drowssap", "rOpeL");
        List<String> dates = Arrays.asList("1909-02-13 06:15:24", "2019-10-20 15:59:20", "2012-06-12 19:12:58");
        Optional<User> possibleUser = userRepository.findUserById((long) 1);
        User user = possibleUser.orElseGet(() -> {
            User userTemp = new User((long) 2, usernames.get(1), names.get(1), passwords.get(1), dates.get(1), dates.get(1));
            return userRepository.create(userTemp);
        });
        return "users/index";
    }

    @GetMapping(value = "register")
    public String create() {
        return "users/create";
    }

    @GetMapping(value = "{id}")
    public String show(@PathVariable Long id) {
        return "users/show";
    }

    @GetMapping(value = "{id}/edit")
    public String edit(@PathVariable Long id) {
        return "users/edit";
    }

    @PostMapping(value="register")
    public String store(@RequestParam(name = "username") String usernamestr
        , @RequestParam(name = "name") String namestr
        , @RequestParam(name = "password") String passwordstr) {
        String now = new Date(new java.util.Date().getTime()).toString();
        userRepository.create(new User((long) 0, namestr, usernamestr, passwordEncoder.encode(passwordstr), now, now));
        return "redirect:/";
    }

    @PostMapping(value = "{id}")
    public String update(@PathVariable Long id) {
        return "redirect:/users" + id;
    }

    @PostMapping(value = "{id}/delete")
    public String delete(@PathVariable Long id) {
        return "redirect:/users";
    }



}