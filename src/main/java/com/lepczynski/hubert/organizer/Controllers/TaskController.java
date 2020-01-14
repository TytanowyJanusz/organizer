package com.lepczynski.hubert.organizer.Controllers;

import com.lepczynski.hubert.organizer.Models.DTOs.TaskDTO;
import com.lepczynski.hubert.organizer.Models.Task;
import com.lepczynski.hubert.organizer.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ExposesResourceFor(Task.class)
@RequestMapping("/tasks")
public class TaskController
{
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService)
    {
        this.taskService = taskService;
    }


    @GetMapping("{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id)
    {
        //TODO check if current user has right to this - how to authentication?
        return ResponseEntity.ok(taskService.getTaskById(id));
    }


    @GetMapping
    public ResponseEntity<CollectionModel<TaskDTO>> getAllTasks()
    {
        return ResponseEntity.ok(taskService.getAllTasks());
    }


    @GetMapping("/forUser/{userId}")
    public ResponseEntity<CollectionModel<TaskDTO>> getAllTasksForUserById(@PathVariable Long userId)
    {
        return ResponseEntity.ok(taskService.getTasksForUserByUserId(userId));
    }


}
