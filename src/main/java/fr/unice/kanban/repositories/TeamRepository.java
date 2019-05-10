package fr.unice.kanban.repositories;

import fr.unice.kanban.entities.*;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String> {

}