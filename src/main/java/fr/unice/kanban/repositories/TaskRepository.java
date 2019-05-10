package fr.unice.kanban.repositories;

import fr.unice.kanban.entities.Task;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface TaskRepository extends MongoRepository<Task, BigInteger> {

}