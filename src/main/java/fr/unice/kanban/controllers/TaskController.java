package fr.unice.kanban.controllers;

import fr.unice.kanban.entities.*;
import fr.unice.kanban.repositories.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.math.BigInteger;

@Controller
public class TaskController {

	@Autowired
	private TaskRepository trep;

	@GetMapping("/tasks")
	public String viewAll(Model model) {
		model.addAttribute("tasks", trep.findAll());
		return "tasks/tasks";
	}

	@GetMapping("/tasks/{id}")
	public String view(Model model, @PathVariable("id") BigInteger taskId) {
		Optional<Task> ot = trep.findById(taskId);
		if(ot.isPresent()) {
			Task t = ot.get();
			model.addAttribute("task", t);
			return "tasks/view";
		} else {
			return "tasks/tasks";
		}
	}
}
