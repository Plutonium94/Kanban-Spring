package fr.unice.kanban.controllers;

import fr.unice.kanban.entities.*;
import fr.unice.kanban.repositories.*;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

@Controller
public class TeamController {

	@Autowired
	private TeamRepository teamRepo;

	@Autowired
	private ProjectRepository projectRepo;

	public TeamController() {

	}

	@GetMapping("/teams")
	public String viewTeams(Model model) {
		model.addAttribute("teams", teamRepo.findAll());
		return "teams";
	}

	@GetMapping("/{teamName}/project")
	public String viewProjects(Model model, @PathVariable("teamName") String teamName) {
		model.addAttribute("projects", projectRepo.findByTeam_Name(teamName));
		model.addAttribute("teamName", teamName);
		return "project";
	}

}