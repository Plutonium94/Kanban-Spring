package fr.unice.kanban.repositories;

import fr.unice.kanban.entities.*;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {

	List<Project> findByTeam_Name(String teamName);
}

