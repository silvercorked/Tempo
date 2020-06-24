package cist4830.unomaha.tempo.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cist4830.unomaha.tempo.model.User;
import cist4830.unomaha.tempo.repository.UserRepository;

@RequestMapping(value = "/")
public class HomeController {

	@GetMapping
	public String index() {
		return "index";
	}
}