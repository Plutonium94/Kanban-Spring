package fr.unice.kanban.controllers;

import fr.unice.kanban.entities.*;
import fr.unice.kanban.repositories.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;

@Controller
public class TaskController {

	@Autowired
	private TaskRepository trep;

	@Autowired
	private ProjectRepository prep;

	@Autowired
	private UserRepository urep;

	@GetMapping("/tasks")
	public String viewAll(Model model) {
		model.addAttribute("tasks", trep.findAll());
		return "tasks/tasks";
	}

	@GetMapping("/tasks/{id}")
	public String view(Model model, @PathVariable("id") BigInteger taskId) {
		Optional<Task> ot = trep.findById(taskId);
		List<Project> projects = prep.findByTasks_Id(taskId);
		if(projects.size() > 0) {
			model.addAttribute("projectName", projects.get(0).getName());
		}
		System.out.println(projects);
		if(ot.isPresent()) {
			Task t = ot.get();
			model.addAttribute("t", t);
			return "tasks/view";
		} else {
			return "tasks/tasks";
		}
	}

	@GetMapping("/projects/{name}/tasks/{id}")
	public String view(Model model, @PathVariable("name") String projectName, @PathVariable("id") BigInteger taskId) {
		addProjectNameToModel(model, projectName);
		Optional<Task> ot = trep.findById(taskId);
		if(ot.isPresent()) {
			Task t = ot.get();
			model.addAttribute("t", t);
			return "tasks/view";
		} else {
			return "tasks/tasks";
		}
	}

	@GetMapping("/projects/{name}/tasks/{id}/view/edit") 
	public String viewEdit(Model model, @PathVariable("name") String projectName, @PathVariable("id") BigInteger taskId) {
		addProjectNameToModel(model, projectName);
		Optional<Task> ot = trep.findById(taskId);
		if(ot.isPresent()) {
			Task t = ot.get();
			model.addAttribute("t", t);
			model.addAttribute("users", urep.findAll());
			KUser assignee = t.getAssignee();
			model.addAttribute("assigneeUsername", (assignee==null)?"null":assignee.getUsername());
			model.addAttribute("statuses", TaskStatus.values());
			return "tasks/edit";
		} else {
			return "tasks/tasks";
		}
	}

	/*@PostMapping("/projects/{name}/tasks/{id}/edit") 
	public String edit(Model model, @PathVariable("name") String projectName, @PathVariable("id") BigInteger taskId, @ModelAttribute Task task, BindingResult br) {
		System.out.println("========================================");
		addProjectNameToModel(model, projectName);
		System.out.println(model);
		System.out.println(task);
		if(br.hasErrors()) {
			System.err.println("\nedit of task " + taskId + " has binding errors");
		} else {
			task = trep.save(task);
		}
		System.out.println("====================");
		return "redirect:/tasks/"+taskId;
	}*/

	@PostMapping("/projects/{name}/tasks/{id}/edit") 
	public String edit(Model model, @PathVariable("name") String projectName, @PathVariable("id") BigInteger taskId, @RequestParam Map<String,String> bodyParams) {
		System.out.println("========================================");
		addProjectNameToModel(model, projectName);
		System.out.println(bodyParams);
		Optional<Task> ot = trep.findById(taskId);
		if(ot.isPresent()) {
			Task task = ot.get();

			int expectedDuration = -1;
			try {
				expectedDuration = Integer.parseInt(bodyParams.get("expectedDuration"));
			} catch(NumberFormatException nfe) {

			}

			task.setExpectedDuration(expectedDuration);
			task.setTitle(bodyParams.get("title"));
			task.setDetails(bodyParams.get("details"));
			task.setStatus(TaskStatus.valueOf(bodyParams.get("status")));

			task = trep.save(task);
		} else {
			return String.format("redirect:/projects/%s/tasks/%l/edit", projectName, taskId);
		}

		System.out.println("====================");
		return "redirect:/tasks/"+taskId;
	}

	@PutMapping("/projects/{name}/tasks/{id}/edit/assignee/{username}")
	public @ResponseBody boolean changeAssignee(@PathVariable("name") String projectName, @PathVariable("id") BigInteger taskId, @PathVariable("username") String assigneeUsername ) {
		Optional<Task> ot = trep.findById(taskId);
		List<KUser> users = urep.findByUsername(assigneeUsername);
		System.out.println(ot + "\n" + users);
		if(ot.isPresent() && users.size() > 0) {
			Task task = ot.get();
			KUser assignee = users.get(0);
			task.setAssignee(assignee);
			task = trep.save(task);
			return true;
		} 
		return false;
	}



	private void addProjectNameToModel(Model model, String projectName) {
		Optional<Project> op = prep.findById(projectName);
		if(op.isPresent()) {
			model.addAttribute("projectName", op.get().getName());
		}
	}




	/*@GetMapping("/tasks/{id}/edit")
	public String viewEdit(Model model, @PathVariable("id") BigInteger taskId) {
		Optional<Task> ot = trep.findById(taskId);
		if(ot.isPresent()) {

		} else {
			return "tasks/tasks";
		}
	}*/

	@PutMapping("/tasks/{id}/expectedDuration/{numDays}")
	public @ResponseBody boolean changeExpectedDuration(@PathVariable("id") BigInteger taskId, @PathVariable("numDays") int numDays ) {
		Optional<Task> ot = trep.findById(taskId);
		if(ot.isPresent()) {
			Task t = ot.get();
			t.setExpectedDuration(numDays);
			t = trep.save(t);
			return true;
		} else {
			return false;
		}
	}
}
