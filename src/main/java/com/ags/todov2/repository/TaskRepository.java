package com.ags.todov2.repository;

import com.ags.todov2.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task, String> {

    Optional<Task> findByTitle(String title);

    boolean existsById(Task task);

    List<Task> findByUser(String user);
}
