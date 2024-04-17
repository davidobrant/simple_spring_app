package se.distansakademin.simple_spring_app.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.distansakademin.simple_spring_app.models.Task;
import se.distansakademin.simple_spring_app.repositories.TaskRepository;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public void run(String... args) throws Exception {

        if (taskRepository.count() == 0) {

            Task task1 = new Task();
            task1.setDescription("Clean up");
            task1.setDone(false);

            Task task2 = new Task();
            task2.setDescription("Walk the dog");
            task2.setDone(true);

            // Spara tasks i databasen
            taskRepository.save(task1);
            taskRepository.save(task2);

            System.out.println("Seeded database with some tasks.");
        }
    }
}
