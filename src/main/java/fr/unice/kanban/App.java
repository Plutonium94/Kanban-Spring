package fr.unice.kanban;

import fr.unice.kanban.entities.*;
import fr.unice.kanban.repositories.*;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class, args);
    }

    public void run(String... args) throws Exception {
    	System.out.println("run");

        KUser u1 = userRepository.save(new KUser("Wang","orange"));
        KUser u2 = userRepository.save(new KUser("Micky","framboise"));
        KUser u3 = userRepository.save(new KUser("Jaime","kiwi"));

    	Task t1 = taskRepository.save(new Task("Add feature1","create feature1 using PHP", u1, u3, 3));
    	Task t2 = taskRepository.save(new Task("Remove feature 16", "Remove feature 16", u3));
    	Task t3 = taskRepository.save(new Task("Add feature 3", "create feature 3 using Python", u2, 8));
    	Optional<Task> ot1 = taskRepository.findById(t1.getId());
    	System.out.println(ot1);

    }
}
