package com.lepczynski.hubert.organizer.Services;

import com.lepczynski.hubert.organizer.Controllers.ExceptionHandling.EntityNotFoundException;
import com.lepczynski.hubert.organizer.Controllers.ExceptionHandling.InvalidParametersForEntityCreationException;
import com.lepczynski.hubert.organizer.Models.DTOs.TaskDTO;
import com.lepczynski.hubert.organizer.Models.Task;
import com.lepczynski.hubert.organizer.Repositories.TaskRepository;
import com.lepczynski.hubert.organizer.ModelAssemblers.TaskDTOModelAssembler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("taskService")
public class TaskService
{

    private final TaskRepository taskRepository;
    private final TaskDTOModelAssembler taskDTOModelAssembler;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskDTOModelAssembler taskDTOModelAssembler, ModelMapper modelMapper)
    {
        this.taskRepository = taskRepository;
        this.taskDTOModelAssembler = taskDTOModelAssembler;
        this.modelMapper = modelMapper;
    }

    public TaskDTO getTaskById(Long id)
    {
        return taskDTOModelAssembler.toModel(taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, "Task")));
    }

    public CollectionModel<TaskDTO> getTasksForUserByUserId(Long userId)
    {
        return taskDTOModelAssembler.toCollectionModel(new ArrayList<>(taskRepository.findByUserId(userId)));
    }

    public CollectionModel<TaskDTO> getAllTasks()
    {
        ArrayList<Task> tasks = new ArrayList<>(taskRepository.findAll());
        return taskDTOModelAssembler.toCollectionModel(tasks);
    }
/* TODO after initial success
    public void save(TaskDTO task)
    {
        if(task.getUserId()!=null)
        {
            Task newTask = createTaskFromTaskDTO(task);
            taskRepository.save(newTask);
        }else
            throw new InvalidParametersForEntityCreationException("Task");

    }

    public boolean update(TaskDTO task)
    {
        if(!taskRepository.existsById(task.getId()))
            return false;
        {
            Task newTask = createTaskFromTaskDTO(task);
            taskRepository.save(newTask);
            return true;
        }
    }

 */

}
