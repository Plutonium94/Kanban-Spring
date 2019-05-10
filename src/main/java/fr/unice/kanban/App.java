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

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        SpringApplication.run(App.class, args);
    }

    public void run(String... args) throws Exception {
    	System.out.println("run");

        if(isDatabaseEmpty()) {

            Team tm1 = teamRepository.save(new Team("Team A"));
            Team tm2 = teamRepository.save(new Team("Team B"));
            Team tm3 = teamRepository.save(new Team("TeamC"));

            System.out.println(teamRepository.findById("Team A"));


            KUser u1 = userRepository.save(new KUser("Wang","orange", tm1));
            KUser u2 = userRepository.save(new KUser("Micky","framboise", tm2));
            KUser u3 = userRepository.save(new KUser("Jaime","kiwi", tm1));

            Task t1 = taskRepository.save(new Task("Add feature1","create feature1 using PHP", u1, u3, 3));
            Task t2 = taskRepository.save(new Task("Remove feature 16", "Remove feature 16", u3));
            Task t3 = taskRepository.save(new Task("Add feature 3", "create feature 3 using Python", u2, 8));
            Task t4 = taskRepository.save(new Task("Modify feature 15","Modify feature 15", u2, u3, 8));
            Task t5 = taskRepository.save(new Task("Remove feature 9", u3, u3, 1));
            Task t6 = taskRepository.save(new Task("Modify feature 15","Modify feature 15", u2, u3, 8));
            Task t7 = taskRepository.save(new Task("Remove feature 19", u3, u3, 5));
            Task t8 = taskRepository.save(new Task("Add feature 34", u1, u3, 8));
            Task t9 = taskRepository.save(new Task("Remove feature 99", u3, u2, 5));
            Task t10 = taskRepository.save(new Task("Remove feature 32", u2, u1, 3));

            Optional<Task> ot1 = taskRepository.findById(t1.getId());
            // System.out.println(ot1);
            Project p1 = projectRepository.save(new Project("Accounts application", tm2, t1,t2, t3));
            Project p2 = projectRepository.save(new Project("Medical Cabinet Software", tm1, t4, t5));
            Project p3 = projectRepository.save(new Project("Hybrid mobile app development", tm1, t6, t7, t8));
            Project p4 = projectRepository.save(new Project("Creation of calculator application", tm3, t9, t10));
        }

    }

    public boolean isDatabaseEmpty() {
        return taskRepository.count() == 0 && userRepository.count() == 0;
    }
}
