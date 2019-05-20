package fr.unice.kanban.controllers;

import fr.unice.kanban.entities.*;
import fr.unice.kanban.repositories.*;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private TeamRepository teamRepo;

	@Autowired
	private UserRepository userRepo;

	public ProjectController() {

	}

	@GetMapping("/projects/new")
	public String makeNew(Model model) {
		model.addAttribute("project", new Project());
		model.addAttribute("teams", teamRepo.findAll());
		return "/projects/new";
	}

	@PostMapping("/projects/create") 
	public String createNew(@ModelAttribute Project project, Model model) {
		if(projectRepo.findByName(project.getName()).size() == 0) {
			project = projectRepo.save(project);
			model.addAttribute("project",project);
			return "redirect:/projects/" + project.getName();
		} else {
			return "redirect:/projects/new";
		}
	}

	@GetMapping("/projects") 
	public String list(Model model) {
		model.addAttribute("projects", projectRepo.findAll());
		return "/projects";
	}


	@GetMapping("/projects/{title}")
	public String viewWelcome(@PathVariable("title") String title, Model model) {
		List<Project> projects = projectRepo.findByName(title);
		if(projects.isEmpty()) {
			return "redirect:/tasks";
		} else {
			model.addAttribute("project", projects.get(0));
			return "projects/welcome";
		}
 	}

 	@GetMapping("/projects/{title}/tasks/new") 
 	public String newTaskForProject(@PathVariable("title") String name, Model model) {
 		List<Project> projects = projectRepo.findByName(name);
 		if(projects.isEmpty()) {
 			return "redirect:/projects";
 		} else {
 			Project project = projects.get(0);
 			model.addAttribute("project", project);
 			model.addAttribute("task", new Task());
 			model.addAttribute("users", userRepo.findAll());
 			return "tasks/new";
 		}
 	}

 	@PostMapping("/projects/{name}/tasks/add") 
 	public String newTaskForProject(@PathVariable("name") String name, @ModelAttribute("task") Task task, @ModelAttribute("project") Project project, Model model) {
 		System.out.println(project);
 		System.out.println(task);
 		if(project.addTask(task)) {
 			System.out.println(project);
 			project = projectRepo.save(project);
 			System.out.println(project);
 			return "redirect:/projects/"+name;
 		} else {
 			return "redirect:/projects/" + name + "/tasks/new";
 		}
 	}

}