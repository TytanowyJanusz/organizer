package com.lepczynski.hubert.organizer.Services;

import com.lepczynski.hubert.organizer.Controllers.ExceptionHandling.EntityNotFoundException;
import com.lepczynski.hubert.organizer.Controllers.ExceptionHandling.InvalidParametersForEntityCreationException;
import com.lepczynski.hubert.organizer.Controllers.TaskController;
import com.lepczynski.hubert.organizer.Models.Category;
import com.lepczynski.hubert.organizer.Models.DTOs.TaskDTO;
import com.lepczynski.hubert.organizer.Models.Task;
import com.lepczynski.hubert.organizer.Repositories.TaskRepository;
import com.lepczynski.hubert.organizer.ResourceAssemblers.TaskDTOResourceAssembler;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
@Service("taskService")
public class TaskService
{

    private final TaskRepository repository;
    private final TaskDTOResourceAssembler assembler;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskDTOResourceAssembler taskResourceAssembler, ModelMapper modelMapper)
    {
        this.repository = taskRepository;
        this.assembler = taskResourceAssembler;
        this.modelMapper = modelMapper;
        configureModelMapperForMappingTasks();
    }

    public Resource<TaskDTO> getTaskById(Long id)
    {
        Task task = repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException(id, "Task"));
        TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
        return assembler.toResource(taskDTO);
    }

    public Resources<Resource<TaskDTO>> getTasksForUserByUserId(Long userId)
    {
        ArrayList<Task> tasks = new ArrayList<>(repository.findByUserId(userId));


        List<Resource<TaskDTO>> taskDTOs =  createListOfResourcesOfTaskDTOsFromListOfTasks(tasks);
        return new Resources<>(taskDTOs,
                linkTo(methodOn(TaskController.class).getAllTasksForUserById(tasks.get(0).getUser().getId())).withSelfRel());    }

    public Resources<Resource<TaskDTO>> getAllTasks()
    {
        ArrayList<Task> tasks = new ArrayList<>(repository.findAll());

        List<Resource<TaskDTO>> taskDTOs =  createListOfResourcesOfTaskDTOsFromListOfTasks(tasks);
        return new Resources<>(taskDTOs,
                linkTo(methodOn(TaskController.class).getAllTasks()).withSelfRel());
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

    private List<Resource<TaskDTO>> createListOfResourcesOfTaskDTOsFromListOfTasks(List<Task> tasks)
    {
        List<Resource<TaskDTO>> taskDTOs = new ArrayList<>();
        TaskDTO taskDTO;
        for(int i = 0; i < tasks.size(); i++)
        {
            taskDTO = modelMapper.map(tasks.get(i), TaskDTO.class);
            taskDTOs.add(assembler.toResource(taskDTO));
        }

        return taskDTOs;
    }

    private Task createTaskFromTaskDTO(TaskDTO taskDTO)
    {
        return new Task(taskDTO.getContent(), taskDTO.getImportance(), taskDTO.getUrgency(), Category.values()[taskDTO.getCategory()]);
    }

}
