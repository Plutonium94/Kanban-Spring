package fr.unice.kanban.controllers;

import fr.unice.kanban.entities.*;
import fr.unice.kanban.repositories.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class UserController {

	@Autowired
	private UserRepository urep;

	public UserController() {

	}

	@GetMapping("/users")
	public String viewUsers(Model model) {
		model.addAttribute("users", urep.findAll());
		return "users";
	}
}