package com.ags.todov2.controller;

import com.ags.todov2.dto.GenericResponse;
import com.ags.todov2.dto.SetStatusDto;
import com.ags.todov2.model.Task;
import com.ags.todov2.model.User;
import com.ags.todov2.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("task")
public class TaskController {

    public static final String LOGGEDIN_USER = "LOGGEDIN_USER";

    @Autowired
    private TaskService taskService;

    @GetMapping("list")
    private List<Task> findAll(HttpSession session) {
        if(session.getAttribute(LOGGEDIN_USER) != null) {
            User denemeUser = (User) session.getAttribute(LOGGEDIN_USER);
            return taskService.findByUser((User) session.getAttribute(LOGGEDIN_USER));
        }else{
            return Arrays.asList();
        }
    }

    @GetMapping("{id}")
    private Task findById(@PathVariable("id") String id) {
        return taskService.findById(id);
    }

    @PostMapping
    private Task create(@RequestBody Task task, HttpSession session) {
        return taskService.create(task, (User)session.getAttribute(LOGGEDIN_USER));
    }

    @DeleteMapping("{id}")
    private void delete(@PathVariable("id") String id) {
        taskService.delete(taskService.findById(id));
    }

    @PostMapping("set-status")
    private GenericResponse setStatus(@RequestBody SetStatusDto request){
        return taskService.setStatus(request.getId(), request.isDone());
    }

    /*@DeleteMapping("{id}")
    private void delete(@PathVariable("id") Job job){
        jobService.delete(job);
    }*/
}
