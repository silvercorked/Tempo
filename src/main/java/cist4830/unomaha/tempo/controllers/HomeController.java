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

@RestController
@RequestMapping(value = "/test")
public class HomeController {
	@Autowired
	private UserRepository userRepository;

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
		return "hit home controller's index page: " + user.toString();
	}
}