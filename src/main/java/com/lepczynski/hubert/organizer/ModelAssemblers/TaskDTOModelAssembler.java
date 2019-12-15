package com.lepczynski.hubert.organizer.ModelAssemblers;


import com.lepczynski.hubert.organizer.Controllers.TaskController;
import com.lepczynski.hubert.organizer.Models.DTOs.TaskDTO;
import com.lepczynski.hubert.organizer.Models.Task;
import com.lepczynski.hubert.organizer.Repositories.TaskRepository;
import com.lepczynski.hubert.organizer.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;



@Component
public class TaskDTOModelAssembler extends RepresentationModelAssemblerSupport<Task, TaskDTO>
{
    private final ModelMapper modelMapper;

    @Autowired
    TaskDTOModelAssembler(ModelMapper modelMapper)
    {
        super(TaskController.class, TaskDTO.class);
        this.modelMapper = modelMapper;

    }
    @Override
    public TaskDTO toModel(Task task)
    {
        return modelMapper.map(task, TaskDTO.class);
    }

}
