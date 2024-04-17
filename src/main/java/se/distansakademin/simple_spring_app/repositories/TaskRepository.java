package se.distansakademin.simple_spring_app.repositories;

import org.springframework.data.repository.CrudRepository;
import se.distansakademin.simple_spring_app.models.Task;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByDone(boolean done);

}
