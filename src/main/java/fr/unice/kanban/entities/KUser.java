package fr.unice.kanban.entities;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.data.annotation.Id;

import java.util.Arrays;
import java.util.ArrayList;

public class KUser extends User {

	@Id
	private String id;

	public KUser(String username, String password) {
		super(username, password, new ArrayList<SimpleGrantedAuthority>(Arrays.asList(new SimpleGrantedAuthority[]{new SimpleGrantedAuthority("ROLE_USER")})));
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if(o == null) { return false; }
		if(o instanceof KUser) {
			KUser u = (KUser)o;
			return super.equals(u) && id.equals(u.id);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + id.hashCode();
	}

	@Override
	public String toString() {
		return "KUser[id: " + id + ", user: " + super.toString() + "]" ;
	}


}