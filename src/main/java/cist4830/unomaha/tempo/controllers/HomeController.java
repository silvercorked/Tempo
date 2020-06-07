package cist4830.unomaha.tempo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping(value = "/test")
public class HomeController {
	
	@GetMapping
	public String index() {
		System.out.println("attempted to hit home page");
		return "index";
	}
}