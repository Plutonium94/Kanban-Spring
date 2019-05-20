package fr.unice.kanban.repositories;

import fr.unice.kanban.entities.*;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<KUser, String> {

	List<KUser>	findByUsername(String username);
}