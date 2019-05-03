package fr.unice.kanban.repositories;

import fr.unice.kanban.entities.*;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<KUser, String> {
	
}