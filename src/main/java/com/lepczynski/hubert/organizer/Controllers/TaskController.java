package com.lepczynski.hubert.organizer.Controllers;

import com.lepczynski.hubert.organizer.Models.DTOs.TaskDTO;
import com.lepczynski.hubert.organizer.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*", allowCredentials = "")
@Controller
public class TaskController
{
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService)
    {
        this.taskService = taskService;
    }

    @CrossOrigin
    @GetMapping("/tasks/{id}")
    public Resource<TaskDTO> getTaskById(@PathVariable Long id)
    {
        //TODO check if current user has right to this - how to authentication?
        return taskService.getTaskById(id);
    }

    @CrossOrigin
    @GetMapping("/tasks")
    public Resources<Resource<TaskDTO>> getAllTasks()
    {
        return taskService.getAllTasks();
    }

    @CrossOrigin
    @GetMapping("/tasksForUser")
    public Resources<Resource<TaskDTO>> getAllTasksForUserById(@PathVariable Long userId)
    {
        return taskService.getTasksForUserByUserId(userId);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/tasksForUser")
    public void updateTask(@RequestBody TaskDTO task)
    {
        taskService.update(task);
    }

    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/tasksForUser")
    public void newTask(@RequestBody TaskDTO task)
    {
        taskService.save(task);
    }
}
