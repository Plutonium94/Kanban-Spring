package fr.unice.kanban.controllers;

import fr.unice.kanban.entities.*;
import fr.unice.kanban.repositories.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

	@Autowired
	private UserRepository urep;

	public UserController() {

	}

	@GetMapping("/users")
	public String viewAll(Model model) {
		model.addAttribute("users", urep.findAll());
		return "users/users";
	}

	@GetMapping("/users/{username}")
	public String view(Model model, @PathVariable("username") String username) {
		List<KUser> users = urep.findByUsername(username);
		if(users.isEmpty()) {
			return "redirect:/users";
		} else {
			model.addAttribute("user", users.get(0));
			return "user/view";
		}
	}

	@GetMapping("/users/{id}")
	public String viewId(Model model, @PathVariable("id") String userId) {
		Optional<KUser> userOpt = urep.findById(userId);
		if(userOpt.isPresent()) {
			model.addAttribute("user", userOpt.get());
			return "users/view";
		} else {
			return "redirect:/users/users";
		}
	}
}