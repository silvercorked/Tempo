package cist4830.unomaha.tempo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/")
public class HomeController {

	@GetMapping
	public String index() {
		return "index";
	}
}