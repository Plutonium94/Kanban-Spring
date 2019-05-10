package fr.unice.kanban.entities;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.annotation.Id;

public class Team {

	@Id
	@Indexed(unique = true)
	private String name;

	public Team() {

	}

	public Team(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean equals(Object o) {
		if(o == null) {return false;}
		if(o instanceof Team) {
			Team t = (Team)o;
			return name.equals(t.name);
		}
		return true;
	}

	public String toString() {
		return "Team[name: " + name + "]";
	}

	public int hashCode() {
		return name.hashCode();
	}

}