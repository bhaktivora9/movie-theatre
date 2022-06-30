package com.jpmc.theater.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Add Security layer, Only admin or users with admin access can add or delete shows.

@RestController
@RequestMapping("/admin")
public class AdminController {

	@GetMapping(value = { "","/"})
	public String index() {
		return "This is the Admin Page,Coming soon!";
	}

	@PostMapping("/add/show")
	public boolean add() {
		return false;

	}

}
