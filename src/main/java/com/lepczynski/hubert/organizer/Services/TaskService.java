package com.lepczynski.hubert.organizer.Services;

import com.lepczynski.hubert.organizer.Controllers.ExceptionHandling.EntityNotFoundException;
import com.lepczynski.hubert.organizer.Controllers.ExceptionHandling.InvalidParametersForEntityCreationException;
import com.lepczynski.hubert.organizer.Controllers.TaskController;
import com.lepczynski.hubert.organizer.Models.Category;
import com.lepczynski.hubert.organizer.Models.DTOs.TaskDTO;
import com.lepczynski.hubert.organizer.Models.Task;
import com.lepczynski.hubert.organizer.Repositories.TaskRepository;
import com.lepczynski.hubert.organizer.ModelAssemblers.TaskDTOModelAssembler;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("taskService")
public class TaskService
{

    private final TaskRepository repository;
    private final TaskDTOModelAssembler taskDTOModelAssembler;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskDTOModelAssembler taskDTOModelAssembler, ModelMapper modelMapper)
    {
        this.repository = taskRepository;
        this.taskDTOModelAssembler = taskDTOModelAssembler;
        this.modelMapper = modelMapper;
        configureModelMapperForMappingTasks();
    }

    public EntityModel<TaskDTO> getTaskById(Long id)
    {
        Task task = repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException(id, "Task"));
        TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
        return taskDTOModelAssembler.toModel(taskDTO);
    }

    public CollectionModel<EntityModel<TaskDTO>> getTasksForUserByUserId(Long userId)
    {
        ArrayList<Task> tasks = new ArrayList<>(repository.findByUserId(userId));


        List<EntityModel<TaskDTO>> taskDTOs = createListOfEntityModelsOfTaskDTOsFromListOfTasks(tasks);
        return new CollectionModel<>(taskDTOs);
    }

    public CollectionModel<EntityModel<TaskDTO>> getAllTasks()
    {
        ArrayList<Task> tasks = new ArrayList<>(repository.findAll());

        List<EntityModel<TaskDTO>> taskDTOs =  createListOfEntityModelsOfTaskDTOsFromListOfTasks(tasks);
        return new CollectionModel<>(taskDTOs);
    }

    public void save(TaskDTO task)
    {
        if(task.getUserId()!=null)
        {
            Task newTask = createTaskFromTaskDTO(task);
            repository.save(newTask);
        }else
            throw new InvalidParametersForEntityCreationException("Task");

    }

    public boolean update(TaskDTO task)
    {
        if(!repository.existsById(task.getId()))
            return false;
        {
            Task newTask = createTaskFromTaskDTO(task);
            repository.save(newTask);
            return true;
        }
    }
    //helper functions
    private void configureModelMapperForMappingTasks()
    {
        PropertyMap<Task, TaskDTO> taskMap = new PropertyMap<Task, TaskDTO>() {
            protected void configure() {
                map().setUserId(source.getUser().getId());

            }
        };
        modelMapper.addMappings(taskMap);
    }

    private List<EntityModel<TaskDTO>> createListOfEntityModelsOfTaskDTOsFromListOfTasks(List<Task> tasks)
    {
        List<EntityModel<TaskDTO>> taskDTOs = new ArrayList<>();
        TaskDTO taskDTO;
        for(int i = 0; i < tasks.size(); i++)
        {
            taskDTO = modelMapper.map(tasks.get(i), TaskDTO.class);
            taskDTOs.add(taskDTOModelAssembler.toModel(taskDTO));
        }

        return taskDTOs;
    }

    private Task createTaskFromTaskDTO(TaskDTO taskDTO)
    {
        return new Task(taskDTO.getContent(), taskDTO.getImportance(), taskDTO.getUrgency(), Category.values()[taskDTO.getCategory()]);
    }

}
