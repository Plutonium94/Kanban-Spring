package fr.unice.kanban.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Project {

	@Id
	private BigInteger id;

	 // Duplicates are accepted 
	@Indexed(unique = true)

	private String name;

	private Team team;

	private List<Task> tasks = new ArrayList<>();

	public Project() {

	}

	public Project(String name) {
		this.name = name;
	}

	public Project(String name, Team team) {
		this(name);
		this.team = team;
	}

	public Project(String name, Team team, Task... tasks) {
		this(name, team);
		this.tasks = new ArrayList<Task>(Arrays.asList(tasks));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team t) {
		team  = t;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getId() {
		return id;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public boolean addTask(Task t) {
 		return tasks.add(t);
	}

	public String toString() {
		return "Project[name= " + name + ",team : " + team.getName() + ", tasks" + tasks + "]";
	}

	public int hashCode() {
		return id.intValue() + name.hashCode() + team.hashCode() + tasks.hashCode();
	}

	public boolean equals(Object o) {
		if(o == null) { return false; }
		if(o instanceof Project) {
			Project p = (Project)o;
			return id.equals(p.id) && name.equals(p.name) && team.equals(p.team)
				&& tasks.equals(p.tasks);
		}	
		return false;
	}

}