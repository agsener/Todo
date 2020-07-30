package com.ags.todov2.service;

import com.ags.todov2.dto.GenericResponse;
import com.ags.todov2.model.Task;
import com.ags.todov2.model.User;
import com.ags.todov2.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }


    public Task findById(String id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task create(Task task, User user) {
        Date date = new Date();
        //Var olan task duzenleniyorsa
        if (task.getId() != null && taskRepository.existsById(task.getId())) {
            Task dbTask = findById(task.getId());
            dbTask.setTitle(task.getTitle());
            dbTask.setDescription(task.getDescription());
            dbTask.setUpdatedDate(date);
            return taskRepository.save(dbTask);
            //Yeni bir task ekleniyorsa
        } else {
            task.setId(UUID.randomUUID().toString());
            task.setUser(user.getId());
            //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            //System.out.println("Date : " + formatter.format(date));
            task.setCreatedDate(date);
            task.setDone(false);
            return taskRepository.save(task);
        }
    }

    public void delete(Task job) {
        taskRepository.delete(job);
    }

    /**
     * Return:
     * 0 -> success
     * 100 -> Cannot find task with @id
     */
    public GenericResponse setStatus(String id, boolean newStatus) {
        Task t = findById(id);
        if (t != null) {
            t.setDone(newStatus);
            taskRepository.save(t);
            return new GenericResponse()
                    .setCode(0);
        } else {
            return new GenericResponse()
                    .setCode(100);
        }
    }

    public List<Task> findByUser(User user) {
        return taskRepository.findByUser(user.getId());
    }
}
